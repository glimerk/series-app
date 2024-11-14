package com.example.seriesapp.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length=500)
    private String caratula;
    private String nombre;
    private String plataformaStreaming;
    @Column(length=500)
    private String sinopsis;
    private float valoracionMedia;
    @OneToMany
    private List<Valoracion> valoraciones;

    public Serie(Integer id, String caratula, String nombre, String plataformaStreaming, String sinopsis, float valoracionMedia, List<Valoracion> valoraciones) {
        this.id = id;
        this.caratula = caratula;
        this.nombre = nombre;
        this.plataformaStreaming = plataformaStreaming;
        this.sinopsis = sinopsis;
        this.valoracionMedia = valoracionMedia;
        this.valoraciones = valoraciones;
    }

    public Serie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaratula() {
        return caratula;
    }

    public void setCaratula(String caratula) {
        this.caratula = caratula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPlataformaStreaming() {
        return plataformaStreaming;
    }

    public void setPlataformaStreaming(String plataformaStreaming) {
        this.plataformaStreaming = plataformaStreaming;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public float getValoracionMedia() {
        return valoracionMedia;
    }

    public void setValoracionMedia(float valoracionMedia) {
        this.valoracionMedia = valoracionMedia;
    }

    public List<Valoracion> getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(List<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }
    
}
