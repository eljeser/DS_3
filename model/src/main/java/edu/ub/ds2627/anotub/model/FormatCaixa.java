package edu.ub.ds2627.anotub.model;

public interface FormatCaixa {
    String nom();
    int decimals();
    double[] convertir(CaixaEmbolcallant caixa, int ampladaImatge, int alcadaImatge);
}

