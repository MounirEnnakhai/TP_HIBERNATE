package ma.projet.service;

import ma.projet.dao.IDao;
import ma.projet.beans.Femme;
import ma.projet.beans.Mariage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FemmeService implements IDao<Femme> {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Femme femme) {
        getCurrentSession().save(femme);
    }

    @Override
    public void update(Femme femme) {
        getCurrentSession().update(femme);
    }

    @Override
    public void delete(Femme femme) {
        getCurrentSession().delete(femme);
    }

    @Override
    public Femme findById(int id) {
        return getCurrentSession().get(Femme.class, id);
    }

    @Override
    public List<Femme> findAll() {
        return getCurrentSession().createQuery("from Femme", Femme.class).list();
    }

    // Method to count the number of children between two dates for a specific woman
    public int getChildrenCountBetweenDates(Femme femme, Date startDate, Date endDate) {
        // Using scalar query for native SQL to avoid entity mapping issues
        Query query = getCurrentSession().createNativeQuery(
            "SELECT SUM(m.nbrEnfant) FROM Mariage m WHERE m.femme_id = :femmeId AND m.dateDebut BETWEEN :startDate AND :endDate");
        query.setParameter("femmeId", femme.getId());
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        
        // Cast the result to Number to handle potential null values
        Number result = (Number) query.getSingleResult();
        
        return result != null ? result.intValue() : 0;
    }


    // Method to get women who have been married multiple times
    public List<Femme> getWomenMarriedMultipleTimes() {
        Query<Femme> query = getCurrentSession().createQuery(
            "SELECT f FROM Femme f JOIN Mariage m ON f.id = m.femme.id GROUP BY f.id HAVING COUNT(m.id) >= 2", Femme.class);
        return query.list();
    }
}
