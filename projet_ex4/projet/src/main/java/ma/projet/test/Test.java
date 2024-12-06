package ma.projet.test;

import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.service.FemmeService;
import ma.projet.service.HommeService;
import ma.projet.service.MariageService;
import ma.projet.util.HibernateUtil;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HibernateUtil.class);
        FemmeService femmeService = context.getBean(FemmeService.class);
        HommeService hommeService = context.getBean(HommeService.class);
        MariageService mariageService = context.getBean(MariageService.class);

        // 1. Create 10 women and 5 men
        for (int i = 0; i < 10; i++) {
            Femme femme = new Femme();
            femme.setNom("NomFemme" + i);
            femme.setPrenom("PrenomFemme" + i);
            femme.setDateNaissance(new Date(80, 1, i + 1)); // Setting birth dates
            femmeService.save(femme);
        }

        for (int i = 0; i < 5; i++) {
            Homme homme = new Homme();
            homme.setNom("NomHomme" + i);
            homme.setPrenom("PrenomHomme" + i);
            hommeService.save(homme);
        }

        // 2. Display all women
        System.out.println("All Women:");
        List<Femme> allFemmes = femmeService.findAll();
        for (Femme femme : allFemmes) {
            System.out.println(femme.getNom() + " " + femme.getPrenom());
        }

        // 3. Find and display the oldest woman
        Femme oldestFemme = null;
        for (Femme femme : allFemmes) {
            if (oldestFemme == null || (femme.getDateNaissance() != null &&
                    femme.getDateNaissance().before(oldestFemme.getDateNaissance()))) {
                oldestFemme = femme;
            }
        }
        if (oldestFemme != null) {
            System.out.println("Oldest Woman: " + oldestFemme.getNom() + " " + oldestFemme.getPrenom());
        }

        // 4. Create marriages
        Homme homme1 = hommeService.findById(1);
        Femme femme1 = femmeService.findById(1);
        Femme femme2 = femmeService.findById(2);
        Femme femme3 = femmeService.findById(3);
        Femme femme4 = femmeService.findById(4);

        Calendar cal = Calendar.getInstance();

        // Marriage 1 (Ongoing)
        Mariage mariage1 = new Mariage();
        mariage1.setHomme(homme1);
        mariage1.setFemme(femme1);
        cal.set(1990, Calendar.SEPTEMBER, 3);
        mariage1.setDateDebut(cal.getTime());
        mariage1.setNbrEnfant(4);
        mariageService.save(mariage1);

        // Marriage 2 (Ongoing)
        Mariage mariage2 = new Mariage();
        mariage2.setHomme(homme1);
        mariage2.setFemme(femme2);
        cal.set(1995, Calendar.SEPTEMBER, 3);
        mariage2.setDateDebut(cal.getTime());
        mariage2.setNbrEnfant(2);
        mariageService.save(mariage2);

        // Marriage 3 (Ended)
        Mariage mariage3 = new Mariage();
        mariage3.setHomme(homme1);
        mariage3.setFemme(femme3);
        cal.set(1989, Calendar.SEPTEMBER, 3);
        mariage3.setDateDebut(cal.getTime());
        cal.set(1990, Calendar.SEPTEMBER, 3);
        mariage3.setDateFin(cal.getTime());
        mariage3.setNbrEnfant(0);
        mariageService.save(mariage3);

        // Marriage 4 (Ongoing)
        Mariage mariage4 = new Mariage();
        mariage4.setHomme(homme1);
        mariage4.setFemme(femme4);
        cal.set(2000, Calendar.APRIL, 11);
        mariage4.setDateDebut(cal.getTime());
        mariage4.setNbrEnfant(3);
        mariageService.save(mariage4);

        // 5. Display wives of Homme1 between two dates
        Date startDate = new Date(90, 0, 1); // January 1, 1990
        Date endDate = new Date(121, 0, 1); // January 1, 2021
        List<Mariage> marriages = hommeService.getWivesBetweenDates(homme1, startDate, endDate);
        System.out.println("Wives of Homme1 between " + startDate + " and " + endDate + ":");
        for (Mariage mariage : marriages) {
            System.out.println("Wife: " + mariage.getFemme().getNom() + ", Date Debut: " + mariage.getDateDebut());
        }

        // 6. Count children of Femme1 between two dates
        Femme femmeForChildrenCount = femmeService.findById(1);
        int childrenCount = femmeService.getChildrenCountBetweenDates(femmeForChildrenCount, startDate, endDate);
        System.out.println("Children Count of Femme1 between " + startDate + " and " + endDate + ": " + childrenCount);

        // 7. Display women married multiple times
        System.out.println("Women married multiple times:");
        List<Femme> marriedMultipleTimes = femmeService.getWomenMarriedMultipleTimes();
        for (Femme f : marriedMultipleTimes) {
            System.out.println(f.getNom() + " " + f.getPrenom());
        }

        // 8. Display men married to four women between two dates
        System.out.println("Men married to four women between " + startDate + " and " + endDate + ":");
        List<Homme> menMarriedToFourWomen = hommeService.getMenMarriedToFourWomenBetweenDates(startDate, endDate);
        for (Homme h : menMarriedToFourWomen) {
            System.out.println(h.getNom() + " " + h.getPrenom());
        }

        // 9. Display marriage details for Homme1
        System.out.println("Marriage details for Homme1:");
        String marriagesInfo = hommeService.displayMarriages(homme1);
        System.out.println(marriagesInfo);
    }
}
