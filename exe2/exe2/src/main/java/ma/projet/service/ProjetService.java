package ma.projet.service;


import ma.project.classes.exe2.Projet;
import ma.project.classes.exe2.Tache;
import ma.projet.dao.IDao;
import ma.project.classes.exe2.EmployeTache;
import ma.project.classes.exe2.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.text.SimpleDateFormat;
import java.util.List;

public class ProjetService implements IDao<Projet> {
    @Override
    public void create(Projet projet) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(projet);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Projet findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Projet.class, id);
        }
    }

    @Override
    public List<Projet> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Projet", Projet.class).list();
        }
    }

    @Override
    public void update(Projet projet) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(projet);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Projet projet) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(projet);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Méthode pour afficher la liste des tâches planifiées pour un projet
    public List<Tache> getTachesByProjet(int projetId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from Tache t where t.projet.id = :projetId";
            return session.createQuery(hql, Tache.class)
                          .setParameter("projetId", projetId)
                          .list();
        }
    }
///////////////////////////////////////////////////////////////////////////
    
    
    public void afficherTachesRealiseesDansProjet(int projetId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Projet projet = session.get(Projet.class, projetId);

            if (projet != null) {
                System.out.println("Projet : " + projet.getId());
                System.out.println("Nom : " + projet.getNom());
                System.out.println("Date début : " + new SimpleDateFormat("dd MMMM yyyy").format(projet.getDateDebut()));
                System.out.println("Liste des tâches:");

                String hql = "select et from EmployeTache et where et.tache.projet.id = :projetId";
                List<EmployeTache> tachesRealisees = session.createQuery(hql, EmployeTache.class)
                                                            .setParameter("projetId", projetId)
                                                            .list();

                System.out.printf("%-10s %-20s %-15s %-15s%n", "Num", "Nom", "Date Début Réelle", "Date Fin Réelle");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                for (EmployeTache et : tachesRealisees) {
                    System.out.printf("%-10d %-20s %-15s %-15s%n",
                            et.getTache().getId(),
                            et.getTache().getNom(),
                            sdf.format(et.getDateDebutReelle()),
                            sdf.format(et.getDateFinReelle())
                    );
                }
            } else {
                System.out.println("Projet introuvable avec ID : " + projetId);
            }
        }
    }

	public void afficherTachesRealiseesDansProjet1(int projetId) {
		// TODO Auto-generated method stub
		
	}
}
