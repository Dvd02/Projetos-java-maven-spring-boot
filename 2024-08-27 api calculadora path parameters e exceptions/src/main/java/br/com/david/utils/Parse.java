package br.com.david.utils;

import br.com.david.exceptions.UnsupportedMathOperationException;

public class Parse {

    public static Double parseToDouble(String strValue) {
        if (strValue == null) {
            return 0.;
        }

        strValue = strValue.replace(",", ".");

        if (strValue.matches("[-+]?[0-9]*\\.?[0-9]+")) {
            return Double.parseDouble(strValue);
        } else {
            throw new UnsupportedMathOperationException("Valor inválido: " + strValue);
        }
    }
}
