package edu.ub.ds2627.anotub.model;

import edu.ub.ds2627.anotub.model.excepcions.FormatDesconegutException;

import java.lang.reflect.Constructor;

public class CercadorFormats {

    public FormatCaixa createFormatCaixa(String format) throws FormatDesconegutException {
        try {
            String paqueteActual = this.getClass().getPackage().getName();
            Class<?> formatCaixaClass = Class.forName(paqueteActual + ".FormatCaixa" + format.toUpperCase());

            Constructor<?> constructor = formatCaixaClass.getConstructor();

            return (FormatCaixa) constructor.newInstance();

        } catch (Exception e) {
            throw new FormatDesconegutException();
        }
    }
}
