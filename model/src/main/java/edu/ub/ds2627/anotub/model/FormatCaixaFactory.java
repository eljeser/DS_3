package edu.ub.ds2627.anotub.model;

import edu.ub.ds2627.anotub.model.excepcions.FormatDesconegutException;

import java.util.HashMap;
import java.util.Map;

public class FormatCaixaFactory {
    private final Map<String, FormatCaixa> formats = new HashMap<>();

    public void registrar(FormatCaixa format) {
        formats.put(normalitzar(format.nom()), format);
    }

    public FormatCaixa obtenir(String nom) throws FormatDesconegutException {
        FormatCaixa format = (nom == null) ? null : formats.get(normalitzar(nom));

        if(format == null) {
            throw new FormatDesconegutException();
        }
        return format;
    }

    public Map<String, FormatCaixa> obtenirFormats() {
        return new HashMap<>(formats);
    }

    private static String normalitzar(String nom) {
        return nom.trim().toUpperCase();
    }
}
