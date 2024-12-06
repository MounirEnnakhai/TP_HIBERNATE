package ma.projet.service;

import ma.projet.classes.LigneCommandeProduit;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class LigneCommandeService implements IDao<LigneCommandeProduit> {
    @Override
    public boolean create(LigneCommandeProduit ligneCommandeProduit) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(ligneCommandeProduit);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public LigneCommandeProduit getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(LigneCommandeProduit.class, id);
        }
    }

    @Override
    public List<LigneCommandeProduit> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from LigneCommandeProduit", LigneCommandeProduit.class).list();
        }
    }
    
    // Method to get products within a specific command
    public List<LigneCommandeProduit> getProduitsInCommande(int commandeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<LigneCommandeProduit> query = session.createQuery("FROM LigneCommandeProduit WHERE commande.id = :commandeId", LigneCommandeProduit.class);
            query.setParameter("commandeId", commandeId);
            return query.list();
        }
    }
}
