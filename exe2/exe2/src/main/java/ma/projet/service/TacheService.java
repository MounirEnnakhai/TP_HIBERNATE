package ma.projet.service;

import ma.project.classes.exe2.Tache;
import ma.project.classes.exe2.*;
import org.hibernate.Session;

import java.util.List;

public class TacheService {


    public List<Tache> getTachesPrixSuperieurA1000() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createNamedQuery("Tache.findExpensiveTasks", Tache.class).list();
        }
    }

    public void afficherTachesPrixSuperieurA1000() {
        List<Tache> taches = getTachesPrixSuperieurA1000();
        System.out.println("Liste des tâches avec un prix supérieur à 1000 DH:");
        for (Tache tache : taches) {
            System.out.println("ID: " + tache.getId() + ", Nom: " + tache.getNom() + ", Prix: " + tache.getPrix());
        }
    }
    //////////////////////////////////////////////
    public List<Tache> getTachesEntreDates(java.util.Date dateDebut, java.util.Date dateFin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select et.tache from EmployeTache et where et.dateDebutReelle >= :dateDebut and et.dateFinReelle <= :dateFin";
            return session.createQuery(hql, Tache.class)
                          .setParameter("dateDebut", dateDebut)
                          .setParameter("dateFin", dateFin)
                          .list();
        }
    }

    public void afficherTachesEntreDates(java.util.Date dateDebut, java.util.Date dateFin) {
        List<Tache> taches = getTachesEntreDates(dateDebut, dateFin);
        System.out.println("Liste des tâches réalisées entre " + dateDebut + " et " + dateFin + ":");
        for (Tache tache : taches) {
            System.out.println("ID: " + tache.getId() + ", Nom: " + tache.getNom());
        }
    }
}
