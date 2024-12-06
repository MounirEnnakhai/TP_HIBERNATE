package ma.projet.test;

import ma.projet.classes.Categorie;
import ma.projet.classes.Commande;
import ma.projet.classes.LigneCommandeProduit;
import ma.projet.classes.Produit;
import ma.projet.service.CategorieService;
import ma.projet.service.CommandeService;
import ma.projet.service.LigneCommandeService;
import ma.projet.service.ProduitService;

import java.util.Date;
import java.util.List;

public class TestApp {
    public static void main(String[] args) {
        // Service instances
        CategorieService categorieService = new CategorieService();
        ProduitService produitService = new ProduitService();
        CommandeService commandeService = new CommandeService();
        LigneCommandeService ligneCommandeService = new LigneCommandeService();

        // Create and save category
        Categorie categorie = new Categorie("ELEC", "Electronics");
        categorieService.create(categorie);

        // Create and save products
        Produit produit1 = new Produit("ES12", 120.0f, categorie);
        Produit produit2 = new Produit("ZR85", 100.0f, categorie);
        Produit produit3 = new Produit("EE85", 200.0f, categorie);

        produitService.create(produit1);
        produitService.create(produit2);
        produitService.create(produit3);

        // Create and save command
        Commande commande = new Commande(new Date());
        commandeService.create(commande);

        // Create and save ligne commandes
        LigneCommandeProduit ligne1 = new LigneCommandeProduit(7, produit1, commande);
        LigneCommandeProduit ligne2 = new LigneCommandeProduit(14, produit2, commande);
        LigneCommandeProduit ligne3 = new LigneCommandeProduit(5, produit3, commande);

        ligneCommandeService.create(ligne1);
        ligneCommandeService.create(ligne2);
        ligneCommandeService.create(ligne3);

        // Display command details
        System.out.println("Commande Details:");
        commandeService.displayCommandeDetails(commande.getId());
        
        // Test finding expensive products
        List<Produit> expensiveProducts = produitService.findExpensiveProducts();
        System.out.println("Produits avec prix supérieur à 100 DH:");
        for (Produit produit : expensiveProducts) {
            System.out.println("Référence: " + produit.getReference() + ", Prix: " + produit.getPrix());
        }
    }
}
