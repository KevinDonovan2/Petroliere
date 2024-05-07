package com.hei.petroliere.controller;
import com.hei.petroliere.model.Station;
import com.hei.petroliere.repository.StationDAO;
import com.hei.petroliere.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/station")
public class StationController {
    private final StationDAO stationDAO;

    @Autowired
    public StationController(StationDAO stationDAO, StationService stationService) {
        this.stationDAO = stationDAO;
    }
    @GetMapping
    public List<Station> getAllStation() {
        return stationDAO.findAll();
    }
    @GetMapping("/{id}")
    public Station getStationById(@PathVariable Integer id) {
        return stationDAO.findById(id);
    }
    @PostMapping
    public void createStation(@RequestBody Station newStation) {
        stationDAO.save(newStation);
    }
    @DeleteMapping("/{id}")
    public void deleteStation(@PathVariable Integer id) {
        stationDAO.delete(id);
    }

}


