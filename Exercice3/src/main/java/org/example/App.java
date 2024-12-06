package org.example;

/**
 * Hello world!
 *
 */
import org.example.entities.Femme;
import org.example.entities.Homme;
import org.example.service.FemmeService;
import org.example.service.HommeService;
import org.example.service.MariageService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class App{

    private static App SpringApplication;
    @Autowired
    private HommeService hommeService;

    @Autowired
    private FemmeService femmeService;

    @Autowired
    private MariageService mariageService;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }

    public void run(Class<App> appClass, String... args) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Créer 10 femmes et 5 hommes
        for (int i = 1; i <= 10; i++) {
            Femme femme = new Femme();
            femme.setNom("Femme" + i);
            femme.setPrenom("Prenom" + i);
            femme.setDateNaissance(dateFormat.parse("01/01/198" + i));
            femmeService.save(femme);
        }

        for (int i = 1; i <= 5; i++) {
            Homme homme = new Homme();
            homme.setNom("Homme" + i);
            homme.setPrenom("Prenom" + i);
            homme.setDateNaissance(dateFormat.parse("01/01/197" + i));
            hommeService.save(homme);
        }

        // Afficher la liste des femmes
        List<Femme> femmes = femmeService.findAll();
        System.out.println("Liste des femmes : " + femmes);


        // Afficher les épouses d'un homme passé en paramètre
        Homme hommeTest = hommeService.findById(1); // Exemple avec l'homme ayant l'ID 1
        List<Femme> epouses = hommeService.findEpousesByHommeAndDates(hommeTest, dateFormat.parse("01/01/1980"), new Date());
        System.out.println("Épouses de " + hommeTest.getNom() + " entre deux dates : " + epouses);

        // Afficher le nombre d'enfants d'une femme passée en paramètre entre deux dates
        Femme femmeTest = femmeService.findById(1); // Exemple avec la femme ayant l'ID 1
        int nombreEnfants = femmeService.getNombreEnfantsBetweenDates(femmeTest, dateFormat.parse("01/01/1980"), new Date());
        System.out.println("Nombre d'enfants de " + femmeTest.getNom() + " entre deux dates : " + nombreEnfants);

        // Afficher la liste des femmes mariées deux fois ou plus
        List<Femme> femmesMarieesDeuxFois = femmeService.findFemmesMarieesDeuxFoisOuPlus();
        System.out.println("Femmes mariées deux fois ou plus : " + femmesMarieesDeuxFois);

        // Afficher les hommes mariés à quatre femmes entre deux dates
        int nombreHommesMarieesQuatreFemmes = mariageService.getNombreHommesMarieesQuatreFemmes(dateFormat.parse("01/01/1980"), new Date());
        System.out.println("Nombre d'hommes mariés à quatre femmes entre deux dates : " + nombreHommesMarieesQuatreFemmes);

    }
}
