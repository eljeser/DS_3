package edu.ub.ds2627.anotub.model;


import java.util.ArrayList;
import java.util.List;

public class Regio {
    private final int id;
    private final CaixaEmbolcallant caixa;
    private List<Categoria> categories;


    public Regio(int id, Punt2D origen, Punt2D fi) {
        this.id = id;
        this.caixa = new CaixaEmbolcallant(origen, fi);
        categories = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public CaixaEmbolcallant getCaixa() {
        return caixa;
    }

    public void afegirEtiqueta(Categoria categoria) {
        categories.add(categoria);
    }

    public List<Categoria> getCategories() {
        return categories;
    }

}
