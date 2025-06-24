package src.aula;

import java.util.ArrayList;
import java.util.Arrays;
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

        System.out.println("Complexidade Média:  2n -> O(n) ");
    }

    public static class Subconjuntos {

        static long iteracoes = 0;
        static long contador = 0;
        static boolean solucaoEncontrada = false;

        public static ArrayList<ArrayList<Integer>> resolucao(ArrayList<Integer> entrada) {

            contador++;
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
            contador++;


            if (soma == 0 && !subconjunto.isEmpty()) {
                resposta.add(new ArrayList<>(subconjunto));
                solucaoEncontrada = true;
                contador++;
                return;
            }


            if (posicao == tamanho)  {
                contador++;
                return;
            }

            backtracking(posicao + 1, entrada, tamanho, subconjunto, resposta, soma);
            contador++;


            int atual = entrada.get(posicao);

                subconjunto.add(atual);
                backtracking(posicao + 1, entrada, tamanho, subconjunto, resposta, soma - atual);
                subconjunto.removeLast();
            contador++;
            contador++;
        }

        public static void main(String[] args) {
            ArrayList<ArrayList<Integer>> testes = new ArrayList<>();


            testes.add(new ArrayList<>(Arrays.asList(-1, -2, -3, 1, 2, 3, 4, -4, 0)));

            testes.add(generateArray(new ArrayList<>(), 10));
            testes.add(generateArray(new ArrayList<>(), 20));
            testes.add(generateArray(new ArrayList<>(), 30));


            for (ArrayList<Integer> entrada : testes) {
                iteracoes = 0;
                contador = 0;
                solucaoEncontrada = false;

                System.out.println(entrada);
                System.out.println(entrada.size());


                long inicio = System.nanoTime();

                ArrayList<ArrayList<Integer>> resultado = resolucao(entrada);

                long fim = System.nanoTime();
                long tempoMicro = (fim - inicio) / 1_000;

                if (resultado.isEmpty()) {
                    System.out.println("Nenhum subconjunto encontrado.");
                } else {
                    System.out.println("Subconjuntos encontrados:");
                    for (ArrayList<Integer> subconjunto : resultado) {
                        System.out.println(subconjunto);
                    }
                      }



                System.out.println("Total de iterações: " + iteracoes);
                System.out.println("Total de instruções: " + contador);
                System.out.println("Tempo micro: " + tempoMicro);
            }
        }
    }
}
