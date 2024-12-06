package org.example.dao;

import org.example.entities.Femme;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public interface FemmeDao extends IDao<Femme> {

    int getNombreEnfantsBetweenDates(Femme femme, Date dateDebut, Date dateFin);

    List<Femme> findFemmesMarieesDeuxFoisOuPlus();
}
