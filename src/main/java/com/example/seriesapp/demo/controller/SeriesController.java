package com.example.seriesapp.demo.controller;

import com.example.seriesapp.demo.model.Serie;
import com.example.seriesapp.demo.model.Valoracion;
import com.example.seriesapp.demo.services.SeriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SeriesController {

    private SeriesService seriesService;

    // inyectar vía constructor
    public SeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Serie>> getAllSeries() {
        List<Serie> series = seriesService.getSeries();
        return ResponseEntity.ok().body(series);
    }

    @GetMapping("/top")
    public ResponseEntity<List<Serie>> getTopSeries() {
        List<Serie> series = seriesService.getTopSeries();
        return ResponseEntity.ok().body(series);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addSerie(@RequestBody Serie serie) {
        try {
            seriesService.addSerie(serie);
            return ResponseEntity.ok().build();
        }
        catch (Exception ex) {
            System.out.println("Excepcion al añadir serie: " + ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/valoracion")
    public ResponseEntity<Void> addValoracion(@RequestParam int idSerie, @RequestBody Valoracion valoracion) {
        try {
            if(seriesService.addValoracion(idSerie, valoracion)) {
            	return ResponseEntity.ok().build();
            } else {
            	return ResponseEntity.internalServerError().build();
            }
            
        }
        catch (Exception ex) {
            System.out.println("Excepcion al añadir valoracion a la serie: " + ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

}
