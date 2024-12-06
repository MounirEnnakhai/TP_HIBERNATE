package ma.project.TestApp;

import java.text.SimpleDateFormat;
import java.util.Date;

import ma.projet.service.ProjetService;
import ma.projet.service.TacheService;

public class TestApp {
    public static void main(String[] args) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            ProjetService projetService = new ProjetService();
            TacheService tacheService = new TacheService();

            // Test de l'affichage des tâches réalisées dans un projet
            projetService.afficherTachesRealiseesDansProjet(4);

            // Test de l'affichage des tâches avec un prix supérieur à 1000 DH
            tacheService.afficherTachesPrixSuperieurA1000();

            // Test de l'affichage des tâches réalisées entre deux dates
            Date dateDebut = sdf.parse("01/01/2013");
            Date dateFin = sdf.parse("31/12/2013");
            tacheService.afficherTachesEntreDates(dateDebut, dateFin);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
