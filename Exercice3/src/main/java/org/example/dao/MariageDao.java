package org.example.dao;

import org.example.entities.Mariage;

import java.util.Date;

public interface MariageDao extends IDao<Mariage> {

    int getNombreHommesMarieesQuatreFemmes(Date dateDebut, Date dateFin);
}
