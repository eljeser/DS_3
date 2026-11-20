package edu.ub.ds2627.anotub.model;

public class FormatYOLO implements FormatCaixa{
    private final String nom;
    private final Regio caixa;

    public FormatYOLO(String nom, Regio caixa) {
        this.nom = nom;
        this.caixa = caixa;
    }

    @Override
    public String nom() {
        return this.nom;
    }

    @Override
    public int decimals() {
        return 0;
    }

    @Override
    public double[] convertir(Regio caixa, int ampladaImatge, int alcadaImatge) {
        return new double[0];
    }
}
