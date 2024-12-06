package ma.projet.service;

import ma.project.classes.exe2.Employe;
import ma.projet.dao.IDao;
import ma.project.classes.exe2.HibernateUtil;
import ma.project.classes.exe2.Projet;
import ma.project.classes.exe2.Tache;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeService implements IDao<Employe> {
    @Override
    public void create(Employe employe) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(employe);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Employe findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Employe.class, id);
        }
    }

    @Override
    public List<Employe> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Employe", Employe.class).list();
        }
    }

    @Override
    public void update(Employe employe) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(employe);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Employe employe) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(employe);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

 // Méthode pour afficher la liste des tâches réalisées par un employé
    public List<Tache> getTachesByEmploye(int employeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select et.tache from EmployeTache et where et.employe.id = :employeId";
            return session.createQuery(hql, Tache.class)
                          .setParameter("employeId", employeId)
                          .list();
        }
    }

    // Méthode pour afficher la liste des projets gérés par un employé
    public List<Projet> getProjetsByEmploye(int employeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select distinct p from Projet p join p.taches t join t.employeTaches et where et.employe.id = :employeId";
            return session.createQuery(hql, Projet.class)
                          .setParameter("employeId", employeId)
                          .list();
        }
    }

}
