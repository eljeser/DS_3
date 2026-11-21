package edu.ub.ds2627.anotub.model;

public class FormatYOLO implements FormatCaixa{
    private final String nom;
    private final Regio caixa;
    private final Imatge imatge;

    public FormatYOLO(String nom, Imatge imatge, Regio caixa) {
        this.nom = nom;
        this.imatge = imatge;
        this.caixa = caixa;
    }

    @Override
    public String nom() {
        return this.nom;
    }

    @Override
    public int decimals() {
        return 6;
    }

    @Override
    public double[] convertir(Regio caixa, int ampladaImatge, int alcadaImatge) {
        // YOLO retorna les coordenades com: [centreX, centreY, amplada, alçada]

        double[] coordenades = new double[4];
        double w = caixa.getWidth();
        double h = caixa.getHeight();

        // Calculem el centre de la caixa
        double cx = (caixa.getOrigen().getX() + w / 2.0) / imatge.getAmplada();
        double cy = (caixa.getOrigen().getY() + h / 2.0) / imatge.getAlcada();

        coordenades[0] = cx;
        coordenades[1] = cy;
        coordenades[2] = w / imatge.getAmplada();
        coordenades[3] = h / imatge.getAlcada();

        return coordenades;
    }
}
