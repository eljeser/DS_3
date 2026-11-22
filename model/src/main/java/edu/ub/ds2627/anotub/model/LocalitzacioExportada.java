package edu.ub.ds2627.anotub.model;

import java.util.List;

public final class LocalitzacioExportada {
    private final int idLocalitzacio;
    private final List<String> categories;
    private final double[] coordenades;

    // constructor amb els tres camps
    public LocalitzacioExportada(int idLocalitzacio, List<String> categories, double[] coordenades) {
        this.idLocalitzacio = idLocalitzacio;
        this.categories = categories;
        this.coordenades = coordenades;
    }

    public int getIdLocalitzacio() { return idLocalitzacio; }
    public List<String> getCategories() { return categories; }
    public double[] getCoordenades() { return coordenades; }
}

