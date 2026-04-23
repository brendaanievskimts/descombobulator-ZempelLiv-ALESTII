package com.app;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Descombobulator de Zempel-Liv
 * Calcula o tamanho do arquivo final após expansão recursiva de letras.
 */
public class Descombobulator {
    private Scanner entrada = new Scanner(System.in);
    private PrintStream saidaPadrao = System.out;
    private final String nomeArquivoEntrada = "inputs/caso15.txt";
    private final String nomeArquivoSaida = "outputs/dadosout.txt";

    private static Map<Character, String> tabela = new HashMap<>();
    private static Map<Character, BigInteger> cache = new HashMap<>();

    public Descombobulator() {
        redirecionaES();
    }

    public void executar() throws IOException {
            long inicio = System.currentTimeMillis();
    
            lerArquivo(nomeArquivoEntrada);
    
            char letraInicial = encontrarLetraInicial();
            BigInteger tamanho = calcularTamanho(letraInicial);
    
            long fim = System.currentTimeMillis();
    
            System.out.println("Arquivo: " + nomeArquivoEntrada);
            System.out.println("Letra inicial: " + letraInicial);
            System.out.println("Tamanho do arquivo final: " + tamanho);
            System.out.println("Tempo: " + (fim - inicio) + " ms");
    
            restauraES();
    }

    private static void lerArquivo(String nomeArquivo) throws IOException {
        tabela.clear();
        cache.clear();

        BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
        String linha;

        while ((linha = br.readLine()) != null) {
            if (linha.isEmpty()) continue;

            char letra = linha.charAt(0);
            String substituicao = (linha.length() > 2) ? linha.substring(2) : "";
            tabela.put(letra, substituicao);
        }

        br.close();
    }

    private static char encontrarLetraInicial() {
        Set<Character> apareceEmSubstituicao = new HashSet<>();
        for (String subst : tabela.values()) {
            for (char c : subst.toCharArray()) {
                if (tabela.containsKey(c)) {
                    apareceEmSubstituicao.add(c);
                }
            }
        }

        List<Character> candidatas = new ArrayList<>();
        for (char letra : tabela.keySet()) {
            if (!apareceEmSubstituicao.contains(letra)) {
                candidatas.add(letra);
            }
        }

        if (candidatas.size() == 1) {
            return candidatas.get(0);
        }

        for (char c : candidatas) {
            if (!tabela.get(c).isEmpty()) {
                return c;
            }
        }

        return candidatas.get(0);
    }

    private static BigInteger calcularTamanho(char letra) {
        if (cache.containsKey(letra)) {
            return cache.get(letra);
        }

        String substituicao = tabela.get(letra);

        if (substituicao == null || substituicao.isEmpty()) {
            cache.put(letra, BigInteger.ONE);
            return BigInteger.ONE;
        }

        BigInteger total = BigInteger.ZERO;
        for (char c : substituicao.toCharArray()) {
            if (tabela.containsKey(c)) {
                total = total.add(calcularTamanho(c));
            } else {
                total = total.add(BigInteger.ONE);
            }
        }

        cache.put(letra, total);
        return total;
    }

    private void redirecionaES() {
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader(nomeArquivoEntrada));
            entrada = new Scanner(streamEntrada);
            PrintStream streamSaida = new PrintStream(new File(nomeArquivoSaida), Charset.forName("UTF-8"));
            System.setOut(streamSaida);
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);
        entrada.useLocale(Locale.ENGLISH);
    }

    private void restauraES() {
        System.setOut(saidaPadrao);
        entrada = new Scanner(System.in);
    }

}