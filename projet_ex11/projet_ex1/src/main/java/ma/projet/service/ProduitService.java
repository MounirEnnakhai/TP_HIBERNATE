package ma.projet.service;

import ma.projet.classes.Produit;
import ma.projet.classes.Categorie;
import ma.projet.classes.Commande;
import ma.projet.classes.LigneCommandeProduit;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class ProduitService implements IDao<Produit> {
    @Override
    public boolean create(Produit produit) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(produit);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Produit getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Produit.class, id);
        }
    }

    @Override
    public List<Produit> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Produit", Produit.class).list();
        }
    }

    // Method to display products by category
    public List<Produit> getProduitsByCategorie(Categorie categorie) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Produit> query = session.createQuery("FROM Produit WHERE categorie = :categorie", Produit.class);
            query.setParameter("categorie", categorie);
            return query.list();
        }
    }

    // Method to display products ordered between two dates
    public List<Produit> getProduitsOrderedBetweenDates(Date startDate, Date endDate) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Produit> query = session.createQuery(
                "SELECT p FROM Produit p JOIN LigneCommandeProduit lcp ON p.id = lcp.produit.id " +
                "JOIN Commande c ON lcp.commande.id = c.id WHERE c.date BETWEEN :startDate AND :endDate", 
                Produit.class);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            return query.list();
        }
    }

    // Method to display products in a specific order
    public List<LigneCommandeProduit> getProduitsInCommande(Commande commande) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<LigneCommandeProduit> query = session.createQuery(
                "FROM LigneCommandeProduit WHERE commande = :commande", 
                LigneCommandeProduit.class);
            query.setParameter("commande", commande);
            return query.list();
        }
    }
    
 // Method to find products with price greater than 100 DH
    public List<Produit> findExpensiveProducts() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createNamedQuery("Produit.findExpensiveProducts", Produit.class).getResultList();
        }
    }
}
