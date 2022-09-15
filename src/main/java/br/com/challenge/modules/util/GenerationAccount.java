package br.com.challenge.modules.util;

import java.util.LinkedList;
import java.util.List;

public class GenerationAccount {

    List<Integer> listadistintos = new LinkedList<>();
    public String accountGeneration() {
        int max = 9;
        int min = 0;
        int intervalo = max - min + 1;
        for (int i = 0; i < 6; i++) {
            int numRandom = (int)(Math.random() * intervalo) + min;
            listadistintos.add(numRandom);
        }
        return listadistintos.toString().replaceAll("[^\\d]", "");
    }
}
