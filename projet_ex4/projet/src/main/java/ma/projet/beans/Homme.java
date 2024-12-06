package ma.projet.beans;

import java.util.List;

import javax.persistence.*;

@Entity
public class Homme extends Personne {
	
	@OneToMany(mappedBy = "homme", cascade = CascadeType.ALL)
    private List<Mariage> mariages;

	public Homme() {}

	public List<Mariage> getMariages() {
		return mariages;
	}

	public void setMariages(List<Mariage> mariages) {
		this.mariages = mariages;
	}
}
