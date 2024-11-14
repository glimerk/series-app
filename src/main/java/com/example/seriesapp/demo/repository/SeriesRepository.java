package com.example.seriesapp.demo.repository;

import com.example.seriesapp.demo.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepository extends JpaRepository<Serie, Integer> {
}
