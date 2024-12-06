package org.example.service;

import org.example.dao.FemmeDao;
import org.example.dao.IDao;
import org.example.entities.Femme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FemmeService implements IDao<Femme> {

    @Autowired
    private FemmeDao femmeDao;

    @Override
    public void save(Femme femme) {
        femmeDao.save(femme);
    }

    @Override
    public void update(Femme femme) {
        femmeDao.save(femme);
    }

    @Override
    public void delete(Femme femme) {
        femmeDao.delete(femme);
    }

    @Override
    public Femme findById(int id) {
        return femmeDao.findById(id);
    }

    @Override
    public List<Femme> findAll() {
        return femmeDao.findAll();
    }

    public int getNombreEnfantsBetweenDates(Femme femme, Date dateDebut, Date dateFin) {
        return femmeDao.getNombreEnfantsBetweenDates(femme, dateDebut, dateFin);
    }

    public List<Femme> findFemmesMarieesDeuxFoisOuPlus() {
        return femmeDao.findFemmesMarieesDeuxFoisOuPlus();
    }
}
