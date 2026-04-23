package com.app;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Descombobulator descombobulator = new Descombobulator();
            descombobulator.executar();
        } catch (IOException e) {
            System.err.println("Erro ao executar: " + e.getMessage());
        }
    }
}