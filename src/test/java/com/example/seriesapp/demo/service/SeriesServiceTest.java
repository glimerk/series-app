package com.example.seriesapp.demo.service;

import com.example.seriesapp.demo.model.Serie;
import com.example.seriesapp.demo.model.Valoracion;
import com.example.seriesapp.demo.repository.SeriesRepository;
import com.example.seriesapp.demo.repository.ValoracionesRepository;
import com.example.seriesapp.demo.services.SeriesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class SeriesServiceTest {

    @Mock
    private SeriesRepository seriesRepository;

    @Mock
    private ValoracionesRepository valoracionesRepository;

    @InjectMocks
    private SeriesService seriesService;

    private Serie serie1;
    private Serie serie2;

    @BeforeEach
    void setUp() {

        serie1 = new Serie(
                1, // id
                "cover-image.jpg",
                "Breaking Bad",
                "Netflix",
                "A high school chemistry teacher turned methamphetamine producer.",
                4.75f,
                new ArrayList());

        serie2 = new Serie(
                2, // id
                "cover-image2.jpg",
                "The Wire",
                "HBO",
                "The wire sinopsis",
                6.65f,
                new ArrayList());
    }

    @Test
    void testGetSeries() {

        List<Serie> mockSeriesList = Arrays.asList(serie1, serie2);
        when(seriesRepository.findAll()).thenReturn(mockSeriesList);

        List<Serie> series = seriesService.getSeries();

        assertNotNull(series);
        assertEquals(2, series.size());
        verify(seriesRepository, times(1)).findAll();
    }

    @Test
    void testGetTopSeries() {
        serie1.setValoracionMedia(4.5f);
        serie2.setValoracionMedia(7.0f);

        List<Serie> mockSeriesList = new ArrayList<>(Arrays.asList(serie1, serie2));
        when(seriesRepository.findAll()).thenReturn(mockSeriesList);

        List<Serie> topSeries = seriesService.getTopSeries();

        assertNotNull(topSeries);
        assertEquals(2, topSeries.size());
        assertTrue(topSeries.get(0).getValoracionMedia() >= topSeries.get(1).getValoracionMedia());
        verify(seriesRepository, times(1)).findAll();
    }

    @Test
    void testAddSerie() {
        Serie newSerie = new Serie(
                2, // id
                "cover-image3.jpg",
                "Mad Men",
                "Amazon Prime",
                "Mad Men sinopsis",
                5.4f,
                new ArrayList());

        seriesService.addSerie(newSerie);
        verify(seriesRepository, times(1)).save(newSerie);
    }

    @Test
    void testAddValoracion() {
        int idSerie = 1;
        serie1.setValoraciones(new ArrayList<>());
        Valoracion valoracion = new Valoracion(1,"Pedro", 5.4f);

        when(seriesRepository.findById(idSerie)).thenReturn(Optional.of(serie1));
        when(valoracionesRepository.save(valoracion)).thenReturn(valoracion);

        seriesService.addValoracion(idSerie, valoracion);

        assertEquals(1, serie1.getValoraciones().size());
        assertEquals(5.4f, serie1.getValoracionMedia());
        verify(valoracionesRepository, times(1)).save(valoracion);
        verify(seriesRepository, times(1)).save(serie1);
    }

    @Test
    void testAddValoracionWithInvalidSerieId() {
        int invalidId = 99;
        Valoracion valoracion = new Valoracion(1,"Pedro", 5.4f);

        when(seriesRepository.findById(invalidId)).thenReturn(Optional.empty());

        seriesService.addValoracion(invalidId, valoracion);

        verify(valoracionesRepository, never()).save(valoracion);
        verify(seriesRepository, never()).save(any(Serie.class));
    }
}
