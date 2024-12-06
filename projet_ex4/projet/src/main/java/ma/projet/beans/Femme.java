package ma.projet.beans;

import java.util.List;

import javax.persistence.*;

@Entity
public class Femme extends Personne {
	
	@OneToMany(mappedBy = "femme", cascade = CascadeType.ALL)
    private List<Mariage> mariages;
	
	public Femme() {}

	public List<Mariage> getMariages() {
		return mariages;
	}

	public void setMariages(List<Mariage> mariages) {
		this.mariages = mariages;
	}
}
