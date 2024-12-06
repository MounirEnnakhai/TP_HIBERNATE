package ma.projet.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();

            // Manually set Hibernate properties
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            settings.put(Environment.URL, "jdbc:mysql://localhost:3306/projet_exercice1");
            settings.put(Environment.USER, "root");
            settings.put(Environment.PASS, "");
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.FORMAT_SQL, "true");
            settings.put(Environment.HBM2DDL_AUTO, "update");

            // Apply properties to configuration
            configuration.setProperties(settings);

            // Add annotated classes
            configuration.addAnnotatedClass(ma.projet.classes.Categorie.class);
            configuration.addAnnotatedClass(ma.projet.classes.Produit.class);
            configuration.addAnnotatedClass(ma.projet.classes.Commande.class);
            configuration.addAnnotatedClass(ma.projet.classes.LigneCommandeProduit.class);

            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
