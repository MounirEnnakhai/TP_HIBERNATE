package org.example.service;

import org.example.dao.HommeDao;
import org.example.dao.IDao;
import org.example.entities.Femme;
import org.example.entities.Homme;
import org.example.entities.Mariage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HommeService implements IDao<Homme> {

    @Autowired
    private HommeDao hommeDao;

    @Override
    public void save(Homme homme) {
        hommeDao.save(homme);
    }

    @Override
    public void update(Homme homme) {
        hommeDao.save(homme);
    }

    @Override
    public void delete(Homme homme) {
        hommeDao.delete(homme);
    }

    @Override
    public Homme findById(int id) {
        return hommeDao.findById(id);
    }

    @Override
    public List<Homme> findAll() {
        return hommeDao.findAll();
    }

    public List<Femme> findEpousesByHommeAndDates(Homme homme, Date dateDebut, Date dateFin) {
        return hommeDao.findEpousesByHommeAndDates(homme, dateDebut, dateFin);
    }

    public List<Mariage> getMariagesByHomme(Homme homme) {
        return hommeDao.getMariagesByHomme(homme);
    }
}

