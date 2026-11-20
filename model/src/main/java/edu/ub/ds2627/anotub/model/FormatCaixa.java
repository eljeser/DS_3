package edu.ub.ds2627.anotub.model;

public interface FormatCaixa {
    String nom();
    int decimals();
    double[] convertir(Regio caixa, int ampladaImatge, int alcadaImatge);

    /*
  public String exportarImatge(String idTasca, String idDada, String format)
        throws AnotUBException {

    Imatge imatge = (Imatge) tasques.obtenir(idTasca).obtenirDada(idDada);
    StringBuilder text = new StringBuilder();

    for (LocalitzacioObjecte loc : imatge.getLocalitzacions()) {

        int w = loc.getX2() - loc.getX1() + 1;
        int h = loc.getY2() - loc.getY1() + 1;

        switch (format) {
            case "COCO":
                text.append(loc.getX1() + " " + loc.getY1() + " " + w + " " + h);
                break;
            case "YOLO":
                double cx = (loc.getX1() + w / 2.0) / imatge.getAmplada();
                double cy = (loc.getY1() + h / 2.0) / imatge.getAlcada();
                text.append(cx + " " + cy + " " + (double) w / imatge.getAmplada()
                          + " " + (double) h / imatge.getAlcada());
                break;
            case "VOC":
                text.append((loc.getX1() + 1) + " " + (loc.getY1() + 1) + " "
                          + (loc.getX2() + 1) + " " + (loc.getY2() + 1));
                break;
            default:
                throw new FormatDesconegutException(format);
        }
        text.append("\n");
    }
    return text.toString();
}

     */
}

