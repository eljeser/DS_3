package edu.ub.ds2627.anotub.model;

public class CaixaEmbolcallant {
    private final Punt2D origen;
    private final Punt2D fi;

    public CaixaEmbolcallant(Punt2D origen, Punt2D fi) {
        this.origen = origen;
        this.fi = fi;
    }

    public Punt2D getOrigen() {
        return origen;
    }

    public Punt2D getFi() {
        return fi;
    }

    public double getHeight() {
        return fi.getY() - origen.getY() + 1;
    }

    public double getWidth() {
        return fi.getX() - origen.getX() + 1;
    }
}
