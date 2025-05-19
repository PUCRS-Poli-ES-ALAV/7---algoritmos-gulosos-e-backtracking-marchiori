package src;

import java.util.ArrayList;
import java.util.Arrays;

public class Subconjuntos {

    static long iteracoes = 0;
    static boolean solucaoEncontrada = false;

    public static ArrayList<ArrayList<Integer>> resolucao(ArrayList<Integer> entrada) {

        ArrayList<ArrayList<Integer>> resposta = new ArrayList<>();
        ArrayList<Integer> subconjunto = new ArrayList<>();
        backtracking(0, entrada, entrada.size(), subconjunto, resposta, 0);
        return resposta;
    }

    public static ArrayList<Integer> generateArray(ArrayList<Integer> array, int num) {
        for (int i = 1; i <= num; i++) {
            array.add(i);
        }
        for (int i = -1; i > -3; i--) {
            array.add(i);
        }
        return array;
    }

    public static void backtracking(int posicao, ArrayList<Integer> entrada, int tamanho,
                                    ArrayList<Integer> subconjunto,
                                    ArrayList<ArrayList<Integer>> resposta, int soma) {
        iteracoes++;


        if (soma == 0 && !subconjunto.isEmpty()) {
            resposta.add(new ArrayList<>(subconjunto));
            solucaoEncontrada = true;
            return;
        }


        if (posicao == tamanho) return;

        backtracking(posicao + 1, entrada, tamanho, subconjunto, resposta, soma);


        int atual = entrada.get(posicao);

            subconjunto.add(atual);
            backtracking(posicao + 1, entrada, tamanho, subconjunto, resposta, soma - atual);
            subconjunto.removeLast();
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> testes = new ArrayList<>();
        testes.add(new ArrayList<>(Arrays.asList(1, 10, -2, 1, 3, 4, -2, -4, 1, 3)));
        testes.add(new ArrayList<>(Arrays.asList(1, -1, -7, 5, 6, -4, 9, -8, 3, -5)));
        testes.add(new ArrayList<>(Arrays.asList(5, -2, -3, 1, 4, -6, 9, -7, 1, 3, -6, 5)));
        testes.add(new ArrayList<>(Arrays.asList(1, 2, -3, 4, 2, 5, 8, 3, -4, -5, -6)));

        testes.add(generateArray(new ArrayList<>(), 30));
        testes.add(generateArray(new ArrayList<>(), 50));
        testes.add(generateArray(new ArrayList<>(), 100));


        for (ArrayList<Integer> entrada : testes) {
            iteracoes = 0;
            solucaoEncontrada = false;

            System.out.println(entrada);
            System.out.println(entrada.size());


            long inicio = System.nanoTime();

            ArrayList<ArrayList<Integer>> resultado = resolucao(entrada);

            long fim = System.nanoTime();
            long tempoMicro = (fim - inicio) / 1_000;
            /*
            if (resultado.isEmpty()) {
                System.out.println("Nenhum subconjunto encontrado.");
            } else {
                System.out.println("Subconjuntos encontrados:");
                for (ArrayList<Integer> subconjunto : resultado) {
                    System.out.println(subconjunto);
                }
                  }
                 */


            System.out.println("Total de iterações: " + iteracoes);
            System.out.println("Tempo micro: " + tempoMicro);
        }
    }
}