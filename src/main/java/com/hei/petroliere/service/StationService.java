package com.hei.petroliere.service;

import com.hei.petroliere.model.Station;
import com.hei.petroliere.repository.StationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {

    private final StationDAO stationDAO;

    @Autowired
    public StationService(StationDAO stationDAO) {
        this.stationDAO = stationDAO;
    }

    public List<Station> getAllStation() {
        return stationDAO.findAll();
    }

    public Station getStation(Integer stationId) {
        return stationDAO.findById(stationId);
    }

    public void createStation(Station newStation) {
        stationDAO.save(newStation);
    }

    public void deleteStation(Integer stationId) {
        stationDAO.delete(stationId);
    }
}
