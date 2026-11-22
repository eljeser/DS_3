package edu.ub.ds2627.anotub.model;

public class FormatYOLO implements FormatCaixa{

    public FormatYOLO() {
    }

    @Override
    public String nom() {
        return "YOLO";
    }

    @Override
    public int decimals() {
        return 6;
    }

    @Override
    public double[] convertir(CaixaEmbolcallant caixa, int ampladaImatge, int alcadaImatge) {
        // YOLO retorna les coordenades com: [centreX, centreY, amplada, alçada]

        double[] coordenades = new double[4];

        double w = caixa.getWidth();
        double h = caixa.getHeight();

        // Calculem el centre de la caixa
        double cx = (caixa.getOrigen().getX() + w / 2.0) / ampladaImatge;
        double cy = (caixa.getOrigen().getY() + h / 2.0) / alcadaImatge;

        coordenades[0] = cx;
        coordenades[1] = cy;
        coordenades[2] = w / ampladaImatge;
        coordenades[3] = h / alcadaImatge;

        return coordenades;
    }
}
