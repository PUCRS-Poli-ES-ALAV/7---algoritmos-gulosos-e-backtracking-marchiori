package src;

import java.util.ArrayList;
import java.util.List;

public class ProblemaTroco {

    static int iteracoes = 0;

    public static List<Integer> problemadoTroco(List<Integer> moedas, int valor) {
        iteracoes = 0;
        iteracoes++;
        List<Integer> troco = new ArrayList<>();


        // Adicionar condições de parada
        if (moedas == null || moedas.isEmpty() || valor <= 0) {
            iteracoes++;
            return new ArrayList<>();
        }

        // Ordenar as moedas em ordem crescente
        moedas.sort(Integer::compareTo);
        iteracoes++;

        for (int i = moedas.size() - 1; i >= 0; i--) {
            while (valor >= moedas.get(i)) {
                valor -= moedas.get(i);
                iteracoes++;
                troco.add(moedas.get(i));
                iteracoes++;
            }
        }
        System.out.println("Iterações: " + iteracoes);
        return troco;
    }
    public static void main(String[] args) {
        List<Integer> moedas = new ArrayList<>();


        // Problema 1
        moedas.add(1);
        moedas.add(2);
        moedas.add(5);
        int n1 = 11;
        System.out.println("Problema do troco: " + problemadoTroco(moedas, n1));

        // Problema 2
        moedas.add(3);
        moedas.add(4);
        int n2 = 27;
        System.out.println("Problema do troco: " + problemadoTroco(moedas, n2));

        // Problema 3
        moedas.add(10);
        moedas.add(25);
        int n3 = 89;
        System.out.println("Problema do troco: " + problemadoTroco(moedas, n3));

        // Problema 4
        moedas.add(50);
        moedas.add(80);
        int n4 = 2294;
        System.out.println("Problema do troco: " + problemadoTroco(moedas, n4));
    }
}
