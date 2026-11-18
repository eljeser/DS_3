package edu.ub.ds2627.anotub.model;

import edu.ub.ds2627.anotub.model.excepcions.DimensionsImatgeNoValidesException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Imatge extends DadaCompleta {
    private final int amplada;
    private final int alcada;
    private final List<Pixel> pixels;
    private final List<Regio> regions;

    public Imatge(String titol, int amplada, int alcada) throws DimensionsImatgeNoValidesException {
        super(titol);
        if (amplada <= 0 || alcada <= 0) {
            throw new DimensionsImatgeNoValidesException();
        }
        this.amplada = amplada;
        this.alcada = alcada;
        this.pixels = new ArrayList<>();
        this.regions = new ArrayList<>();
        for (int y = 0; y < alcada; y++) {
            for (int x = 0; x < amplada; x++) {
                pixels.add(new Pixel(new Punt2D(x, y), 0, 0, 0));
            }
        }
    }

    public int getAmplada() {
        return amplada;
    }

    public int getAlcada() {
        return alcada;
    }

    public List<Pixel> getPixels() {
        return Collections.unmodifiableList(pixels);
    }

    public List<Regio> getRegions() {
        return Collections.unmodifiableList(regions);
    }

    // US8
    public int getNombreRegions() {
        return regions.size();
    }

    // Afegir una regió a la imtage
    public Regio afegirRegio(Punt2D origen, Punt2D fi) {
        // Comprovem que la regió estigui dins de les dimensions de la imatge
        if (origen.getX() < 0 || origen.getY() < 0 || fi.getX() < 0 || fi.getY() < 0 ||
                origen.getX() >= amplada || origen.getY() >= alcada|| fi.getX() >= amplada || fi.getY() >= alcada
                || origen.getX() > fi.getX() || origen.getY() > fi.getY()) {
            throw new IllegalArgumentException();
        }
        // Si es correcte, afegim la regió a la imatge
        int id = regions.size() + 1;
        Regio regio = new Regio(id, origen, fi);
        regions.add(regio);
        return regio;
    }

}
