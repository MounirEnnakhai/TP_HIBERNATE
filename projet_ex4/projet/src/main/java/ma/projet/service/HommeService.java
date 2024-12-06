package ma.projet.service;

import ma.projet.dao.IDao;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class HommeService implements IDao<Homme> {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Homme homme) {
        getCurrentSession().save(homme);
    }

    @Override
    public void update(Homme homme) {
        getCurrentSession().update(homme);
    }

    @Override
    public void delete(Homme homme) {
        getCurrentSession().delete(homme);
    }

    @Override
    public Homme findById(int id) {
        return getCurrentSession().get(Homme.class, id);
    }

    @Override
    public List<Homme> findAll() {
        return getCurrentSession().createQuery("from Homme", Homme.class).list();
    }


    public List<Mariage> getWivesBetweenDates(Homme homme, Date startDate, Date endDate) {
        Query<Mariage> query = getCurrentSession().createQuery(
            "FROM Mariage WHERE homme = :homme AND dateDebut BETWEEN :startDate AND :endDate", Mariage.class);
        query.setParameter("homme", homme);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.list();
    }


    public List<Homme> getMenMarriedToFourWomenBetweenDates(Date startDate, Date endDate) {
        Session session = getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Homme> query = cb.createQuery(Homme.class);

        Root<Homme> hommeRoot = query.from(Homme.class);
        Join<Homme, Mariage> mariageJoin = hommeRoot.join("mariages");

        query.select(hommeRoot)
            .where(cb.between(mariageJoin.<Date>get("dateDebut"), startDate, endDate))
            .groupBy(hommeRoot.get("id"))
            .having(cb.greaterThanOrEqualTo(cb.count(mariageJoin), 4L)); // Convert 4 to 4L for a Long comparison

        return session.createQuery(query).getResultList();
    }




    public String displayMarriages(Homme homme) {
        if (homme == null) {
            return "No information available: Homme is null.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Nom: ").append(homme.getNom()).append(" ").append(homme.getPrenom()).append("\n");

        // Current (Ongoing) Marriages
        sb.append("Mariages En Cours:\n");

        Query<Mariage> currentQuery = getCurrentSession().createQuery(
            "FROM Mariage WHERE homme = :homme AND dateFin IS NULL", Mariage.class);
        currentQuery.setParameter("homme", homme);
        List<Mariage> currentMarriages = currentQuery.list();

        int index = 1;
        for (Mariage mariage : currentMarriages) {
            sb.append(index++).append(". Femme: ").append(mariage.getFemme().getNom()).append(" ")
              .append(mariage.getFemme().getPrenom()).append("  ")
              .append("Date Début: ").append(mariage.getDateDebut()).append(" ")
              .append("Nbr Enfants: ").append(mariage.getNbrEnfant()).append("\n");
        }

        // Past (Ended) Marriages
        sb.append("\nMariages échoués:\n");

        Query<Mariage> pastQuery = getCurrentSession().createQuery(
            "FROM Mariage WHERE homme = :homme AND dateFin IS NOT NULL", Mariage.class);
        pastQuery.setParameter("homme", homme);
        List<Mariage> pastMarriages = pastQuery.list();

        index = 1;
        for (Mariage mariage : pastMarriages) {
            sb.append(index++).append(". Femme: ").append(mariage.getFemme().getNom()).append(" ")
              .append(mariage.getFemme().getPrenom()).append("  ")
              .append("Date Début: ").append(mariage.getDateDebut()).append(" ")
              .append("Date Fin: ").append(mariage.getDateFin()).append(" ")
              .append("Nbr Enfants: ").append(mariage.getNbrEnfant()).append("\n");
        }

        return sb.toString();
    }


}
