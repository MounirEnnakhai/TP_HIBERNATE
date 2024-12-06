package org.example.service;

import org.example.dao.IDao;
import org.example.dao.MariageDao;
import org.example.entities.Mariage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MariageService implements IDao<Mariage> {

    @Autowired
    private MariageDao mariageDao;

    @Override
    public void save(Mariage mariage) {
        mariageDao.save(mariage);
    }

    @Override
    public void update(Mariage mariage) {
        mariageDao.save(mariage);
    }

    @Override
    public void delete(Mariage mariage) {
        mariageDao.delete(mariage);
    }

    @Override
    public Mariage findById(int id) {
        return mariageDao.findById(id);
    }

    @Override
    public List<Mariage> findAll() {
        return mariageDao.findAll();
    }

    public int getNombreHommesMarieesQuatreFemmes(Date dateDebut, Date dateFin) {
        return mariageDao.getNombreHommesMarieesQuatreFemmes(dateDebut, dateFin);
    }
}
