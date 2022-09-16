package br.com.challenge.modules.util;

import java.util.LinkedList;
import java.util.List;

public class Generation {
    private static final List<Integer> listaAgency = new LinkedList<>();
    private static final List<Integer> listaAccount = new LinkedList<>();

    public String agencyGeneration() {
        int max = 9;
        int min = 0;
        int intervalo = max - min + 1;
        for (int i = 0; i < 5; i++) {
            int numRandom = (int)(Math.random() * intervalo) + min;
            listaAgency.add(numRandom);
        }
        return listaAgency.toString().replaceAll("[^\\d]", "");
    }
    public String accountGeneration() {
        int max = 9;
        int min = 0;
        int intervalo = max - min + 1;
        for (int i = 0; i < 6; i++) {
            int numRandom = (int)(Math.random() * intervalo) + min;
            listaAccount.add(numRandom);
        }
        return listaAccount.toString().replaceAll("[^\\d]", "");
    }
}
