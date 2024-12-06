package ma.projet.classes;

import javax.persistence.*;

@Entity
@Table(name = "categorie")
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code")
    private String code;

    
	@Column(name = "libelle")
    private String libelle;

    // Constructors, Getters, and Setters
    public Categorie() {}

    public Categorie(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    // Getters and Setters
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}
