package edu.ub.ds2627.anotub.model;

import edu.ub.ds2627.anotub.model.excepcions.CategoriaNoPermesaException;
import edu.ub.ds2627.anotub.model.excepcions.DadaNoExistentException;
import edu.ub.ds2627.anotub.model.excepcions.DimensionsImatgeNoValidesException;
import edu.ub.ds2627.anotub.model.excepcions.TipusEtiquetaNoPermesaException;
import edu.ub.ds2627.anotub.model.excepcions.MassesCategoriesNoPermesesException;

import java.util.List;

import java.time.LocalDate;

public class TascaImatges extends Tasca {

    public TascaImatges(String id, LocalDate dataLimit, EspecificacioTasca especificacio, Empresa empresa) {
        super(id, dataLimit, especificacio, empresa);
    }

    @Override
    public void afegirImatge(String titol, int amplada, int alcada) throws DimensionsImatgeNoValidesException {
        afegirDada(new Imatge(titol, amplada, alcada));
    }

    // US8
    public void afegirLocalitzacio(String titol, Punt2D origen, Punt2D fi, List<Categoria> categories) throws TipusEtiquetaNoPermesaException, MassesCategoriesNoPermesesException, DadaNoExistentException {
        DadaCompleta dada = obtenirDada(titol);

        if (!(dada instanceof Imatge imatge)) {
            throw new TipusEtiquetaNoPermesaException();
        }

        for (Categoria categoria : categories) {
            if(!getEspecificacio().imposa(categoria)){
                throw new MassesCategoriesNoPermesesException();
            }
        }

        Regio regio = imatge.afegirRegio(origen, fi);

        // Afegim les categories a la regio
        for (Categoria categoria : categories) {
            regio.afegirEtiqueta(categoria);
        }
    }

    public void anotarLocalitzacio(String titolDada, Punt2D origen, Punt2D fi, List<Categoria> categories) throws TipusEtiquetaNoPermesaException, MassesCategoriesNoPermesesException, DadaNoExistentException {
        afegirLocalitzacio(titolDada, origen, fi, categories);
    }

}
