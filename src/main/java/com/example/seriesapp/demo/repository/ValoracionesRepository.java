package com.example.seriesapp.demo.repository;

import com.example.seriesapp.demo.model.Valoracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValoracionesRepository extends JpaRepository<Valoracion, Integer> {
}
