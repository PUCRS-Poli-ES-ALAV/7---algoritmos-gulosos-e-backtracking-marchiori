package src.slide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrocoSlide {

    static int iteracoes = 0;

    public static List<Integer> gulosoTroco(List<Integer> moedas, int valor) {
        iteracoes = 0;

        List<Integer> C = new ArrayList<>(moedas); // Conjunto de candidatos
        List<Integer> S = new ArrayList<>();       // Solução parcial

        // Condições de parada (slide: "quando o algoritmo do troco funciona")
        if (C.isEmpty() || valor <= 0) {
            return S;
        }

        // Ordena candidatos em ordem decrescente (para facilitar seleção gulosa)
        C.sort(Collections.reverseOrder());

        while (!C.isEmpty() && !solucao(S, valor)) {
            int x = seleciona(C);       // Seleciona o candidato mais promissor
            C.remove(Integer.valueOf(x)); // Remove x de C

            while (viavel(S, x, valor)) {
                S.add(x);               // Adiciona à solução
                valor -= x;
                iteracoes++;
            }
        }

        System.out.println("Iterações: " + iteracoes);
        return solucao(S, valor) ? S : new ArrayList<>();
    }

    // Seleciona o próximo candidato (maior moeda)
    public static int seleciona(List<Integer> C) {
        return C.getFirst();
    }

    // Verifica se ainda podemos adicionar essa moeda
    public static boolean viavel(List<Integer> S, int x, int valorRestante) {
        return x <= valorRestante;
    }

    // Verifica se o valor foi completamente trocado
    public static boolean solucao(List<Integer> S, int valorRestante) {
        return valorRestante == 0;
    }

    public static void main(String[] args) {
        List<Integer> moedas = List.of(1, 2, 5, 3, 4, 10, 25, 50, 80);

        int[] valores = {11, 27, 89, 2294};

        for (int valor : valores) {
            System.out.println("Problema do troco para: " + valor);
            System.out.println("Solução gulosa: " + gulosoTroco(new ArrayList<>(moedas), valor));
            System.out.println("--------------");
        }
    }
}