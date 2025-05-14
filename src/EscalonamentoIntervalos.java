package src;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Intervalo {
    int inicio;
    int fim;
    int indiceOriginal; // pra sabermos qual era o índice no input original

    public Intervalo(int inicio, int fim, int indiceOriginal) {
        this.inicio = inicio;
        this.fim = fim;
        this.indiceOriginal = indiceOriginal;
    }
}

public class EscalonamentoIntervalos {

    static int iteracoes;

    public static List<Integer> sdmGuloso(int[] s, int[] f) {
        iteracoes++;
        int n = s.length;
        iteracoes++;
        List<Intervalo> intervalos = new ArrayList<>();


        for (int i = 0; i < n; i++) {
            iteracoes++;
            intervalos.add(new Intervalo(s[i], f[i], i));
        }


        iteracoes++;
        intervalos.sort(Comparator.comparingInt(i -> i.fim));

        iteracoes++;
        iteracoes++;
        List<Integer> resultado = new ArrayList<>();
        int ultimoFim = Integer.MIN_VALUE;

        for (Intervalo intervalo : intervalos) {
            if (intervalo.inicio > ultimoFim) {
                iteracoes++;
                resultado.add(intervalo.indiceOriginal);
                iteracoes++;
                ultimoFim = intervalo.fim;
            }
        }

        return resultado;
    }

    public static void main(String[] args) {

        iteracoes = 0;
        int[] s = {4, 6, 13, 4, 2, 6, 7, 9, 1, 3, 9};
        int[] f = {8, 7, 14, 5, 4, 9, 10, 11, 6, 13, 12};

        List<Integer> selecionados = sdmGuloso(s, f);

        System.out.println("Iterações: " + iteracoes);

        System.out.println("Índices selecionados para a SDM:");
        for (int i : selecionados) {

            System.out.println("Intervalo " + i + ": (" + s[i] + ", " + f[i] + ")");
        }

        System.out.println("Total de compromissos sem conflito: " + selecionados.size());
    }
}
