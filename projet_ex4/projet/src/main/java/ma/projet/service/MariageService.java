package ma.projet.service;

import ma.projet.dao.IDao;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service 
@Transactional
public class MariageService implements IDao<Mariage> {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Mariage mariage) {
        getCurrentSession().save(mariage);
    }

    @Override
    public void update(Mariage mariage) {
        getCurrentSession().update(mariage);
    }

    @Override
    public void delete(Mariage mariage) {
        getCurrentSession().delete(mariage);
    }

    @Override
    public Mariage findById(int id) {
        return getCurrentSession().get(Mariage.class, id);
    }

    @Override
    public List<Mariage> findAll() {
        return getCurrentSession().createQuery("from Mariage", Mariage.class).list();
    }
    
}
