package ma.project.classes.exe2;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private String prenom;
    private String telephone;

    @OneToMany(mappedBy = "employe")
    private Set<EmployeTache> employeTaches;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<EmployeTache> getEmployeTaches() {
        return employeTaches;
    }

    public void setEmployeTaches(Set<EmployeTache> employeTaches) {
        this.employeTaches = employeTaches;
    }
}
