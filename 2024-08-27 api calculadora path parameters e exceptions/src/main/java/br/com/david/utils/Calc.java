package br.com.david.utils;

import br.com.david.exceptions.UnsupportedMathOperationException;

public class Calc {
    public static Double calc(String operation,Double firstValue,Double secondValue){
        Double resultado = null;

        switch (operation) {
        case "+":
            resultado = firstValue + secondValue;
            break;
        case "-":
            resultado = firstValue - secondValue;
            break;
        case ":": 
            resultado = firstValue / secondValue;
            break;
        case "*":
            resultado = firstValue * secondValue;
            break;
        default:
            throw new UnsupportedMathOperationException("Operacao invalida: " + operation);
        }

        return resultado;
    }
}
