package org.example.entities;
import javax.persistence.*;
import java.util.List;

@Entity
public class Homme extends Personne {
    @OneToMany(mappedBy = "homme")
    private List<Mariage> mariages;
    // Getters et setters

    public List<Mariage> getMariages() {
        return mariages;
    }

    public void setMariages(List<Mariage> mariages) {
        this.mariages = mariages;
    }
}
