package com.example.seriesapp.demo.util;

import com.example.seriesapp.demo.model.Serie;

import java.util.Comparator;

public class SerieComparator implements Comparator<Serie> {

    @Override
    public int compare(Serie s1, Serie s2) {
        return Float.compare(s2.getValoracionMedia(), s1.getValoracionMedia());
    }
}
