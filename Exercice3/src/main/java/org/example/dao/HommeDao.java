package org.example.dao;

import org.example.entities.Femme;
import org.example.entities.Homme;
import org.example.entities.Mariage;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public interface HommeDao extends IDao<Homme> {

    List<Femme> findEpousesByHommeAndDates(Homme homme, Date dateDebut, Date dateFin);

    List<Mariage> getMariagesByHomme(Homme homme);
}
