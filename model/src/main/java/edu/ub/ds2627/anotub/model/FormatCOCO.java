package edu.ub.ds2627.anotub.model;

public class FormatCOCO implements FormatCaixa {
    private final String nom;
    private final Regio caixa;
    private final Imatge imatge;

    public FormatCOCO(String nom, Imatge imatge, Regio caixa) {
        this.nom = nom;
        this.caixa = caixa;
        this.imatge = imatge;
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
        // COCO retorna les coordenades com: [x, y, amplada, alçada]
        double[] coordenades = new double[4];
        coordenades[0] = caixa.getOrigen().getX();
        coordenades[1] = caixa.getOrigen().getY();
        coordenades[2] = caixa.getHeight();
        coordenades[3] = caixa.getWidth();

        return coordenades;
    }
}
