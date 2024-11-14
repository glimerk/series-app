package com.example.seriesapp.demo.services;

import com.example.seriesapp.demo.model.Serie;
import com.example.seriesapp.demo.model.Valoracion;
import com.example.seriesapp.demo.repository.SeriesRepository;
import com.example.seriesapp.demo.repository.ValoracionesRepository;
import com.example.seriesapp.demo.util.SerieComparator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesService {

    private SeriesRepository seriesRepository;
    private ValoracionesRepository valoracionesRepository;

    public SeriesService(SeriesRepository seriesRepository, ValoracionesRepository valoracionesRepository) {
        this.seriesRepository = seriesRepository;
        this.valoracionesRepository = valoracionesRepository;
    }

    public List<Serie> getSeries() {
        return seriesRepository.findAll();
    }

    public List<Serie> getTopSeries() {
        List<Serie> topSeries = seriesRepository.findAll();
        topSeries.sort(new SerieComparator());
        return topSeries;
    }

    public void addSerie(Serie serie) {
        seriesRepository.save(serie);
    }

    public boolean addValoracion(int idSerie, Valoracion valoracion) {
        Serie serie = seriesRepository.findById(idSerie).orElse(null);
        if (serie!=null) {
            Valoracion val = valoracionesRepository.save(valoracion);
            serie.getValoraciones().add(val);
            actualizaValoracionMedia(serie);
            seriesRepository.save(serie);
            return true;
        }
        return false;
    }

    private void actualizaValoracionMedia(Serie serie) {
        int numValoraciones = serie.getValoraciones().size();
        float sumaValoraciones = 0;
        for (Valoracion valoracion : serie.getValoraciones()) {
            sumaValoraciones += valoracion.getValoracion();
        }
        //calculamos la media y la actualizamos
        serie.setValoracionMedia(sumaValoraciones/numValoraciones);
    }

}
