package ma.projet.service;

import ma.projet.classes.Commande;
import ma.projet.classes.LigneCommandeProduit;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class CommandeService implements IDao<Commande> {
    @Override
    public boolean create(Commande commande) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(commande);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Commande getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Commande.class, id);
        }
    }

    @Override
    public List<Commande> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Commande", Commande.class).list();
        }
    }
    
    // Method to get commandes between two dates
    public List<Commande> getCommandesBetweenDates(Date startDate, Date endDate) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Commande> query = session.createQuery("FROM Commande WHERE date BETWEEN :startDate AND :endDate", Commande.class);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            return query.list();
        }
    }
    
    public void displayCommandeDetails(int commandeId) {
        CommandeService commandeService = new CommandeService();
        LigneCommandeService ligneCommandeService = new LigneCommandeService();
        
        Commande commande = commandeService.getById(commandeId);
        List<LigneCommandeProduit> lignes = ligneCommandeService.getProduitsInCommande(commandeId);
        
        System.out.println("Commande : " + commande.getId() + "\tDate : " + commande.getDate());
        System.out.println("Liste des produits : ");
        System.out.println("Référence\tPrix\tQuantité");

        for (LigneCommandeProduit ligne : lignes) {
            System.out.println(ligne.getProduit().getReference() + "\t" + ligne.getProduit().getPrix() + " DH\t" + ligne.getQuantite());
        }
    }
}
