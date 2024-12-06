package ma.projet.classes;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "commande")
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date")
    private Date date;

    // Constructors, Getters, and Setters
    public Commande() {}

    public Commande(Date date) {
        this.date = date;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

    // Getters and Setters
    
}

