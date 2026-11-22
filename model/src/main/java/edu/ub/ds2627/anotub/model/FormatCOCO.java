package edu.ub.ds2627.anotub.model;

public class FormatCOCO implements FormatCaixa {

    public FormatCOCO() {
    }

    @Override
    public String nom() {
        return "COCO";
    }

    @Override
    public int decimals() {
        return 0;
    }

    @Override
    public double[] convertir(CaixaEmbolcallant caixa, int ampladaImatge, int alcadaImatge) {
        // COCO retorna les coordenades com: [x, y, amplada, alçada]
        double[] coordenades = new double[4];

        coordenades[0] = caixa.getOrigen().getX();
        coordenades[1] = caixa.getOrigen().getY();
        coordenades[2] = caixa.getWidth();
        coordenades[3] = caixa.getHeight();

        return coordenades;
    }
}
