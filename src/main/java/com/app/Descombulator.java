package com.app;

import java.math.BigInteger;
import java.util.HashMap;

public class Descombulator {
    
    private HashMap<Character, String> substituicoes;
    private char letraInicial;

    public void executar() {
        EntradaReader reader = new EntradaReader();
        reader.ler();
        substituicoes = reader.getSubstituicoes();
        letraInicial = reader.getLetraInicial();

        String resultado = descombular(letraInicial);
        System.out.println(resultado);
    }

    private String descombular(char letra) {
        if (!substituicoes.containsKey(letra)) {
            return Character.toString(letra);
        }

        String substituicao = substituicoes.get(letra);
        StringBuilder resultado = new StringBuilder();

        for (char c : substituicao.toCharArray()) {
            resultado.append(descombular(c));
        }

        return resultado.toString();
    }
}