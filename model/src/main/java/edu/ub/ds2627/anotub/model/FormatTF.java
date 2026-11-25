package edu.ub.ds2627.anotub.model;

public class FormatTF implements FormatCaixa {

    public FormatTF() {
    }

    @Override
    public String nom() {
        return "TF";
    }

    @Override
    public int decimals() {
        return 6;
    }

    @Override
    public double[] convertir(CaixaEmbolcallant caixa, int ampladaImatge, int alcadaImatge) {
        // COCO retorna les coordenades com: [ymin, xmin, ymax ,xmax]
        double[] coordenades = new double[4];

        double ymin = (double) caixa.getOrigen().getY()/alcadaImatge;
        double xmin = (double) caixa.getOrigen().getX()/ampladaImatge;
        double ymax = (double) (caixa.getOrigen().getY() + caixa.getHeight())/alcadaImatge;
        double xmax = (double) (caixa.getOrigen().getX() + caixa.getWidth())/ampladaImatge;

        coordenades[0] = ymin;
        coordenades[1] = xmin;
        coordenades[2] = ymax;
        coordenades[3] = xmax;

        return coordenades;
    }
}
