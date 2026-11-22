package edu.ub.ds2627.anotub.model;

public class FormatVOC implements FormatCaixa{

    public FormatVOC() {
    }

    @Override
    public String nom() {
        return "VOC";
    }

    @Override
    public int decimals() {
        return 0;
    }

    @Override
    public double[] convertir(CaixaEmbolcallant caixa, int ampladaImatge, int alcadaImatge) {
        // VOC retorna les coordenades com: [x1 + 1, y1 + 1, x2 + 1, y2 + 1]

        double[] coordenades = new double[4];

        coordenades[0] = caixa.getOrigen().getX() + 1;
        coordenades[1] = caixa.getOrigen().getY() + 1;
        coordenades[2] = caixa.getFi().getX() + 1;
        coordenades[3] = caixa.getFi().getY() + 1;

        return coordenades;
    }
}
