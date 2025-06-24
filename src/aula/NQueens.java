package src.aula;

import java.util.ArrayList;


public class NQueens {

    static int contador;
    static int instrucoes;

    // Primeiramente, criaremos um metodo principal para criação da matriz resposta.
    // Após isso, criação de estrutura de dados para representar o tabuleiro. Iniciar da primeira posição.

    public static ArrayList<ArrayList<Integer>> resolucao(int n) {

        // Primeira condição de parada: o tamanho do tabuleiro precisa ser maior ou igual a dois.
        if (n < 2) return null;

        // Instância de estrutura de dados
        ArrayList<ArrayList<Integer>> resposta = new ArrayList<>();
        ArrayList<Integer> tabuleiro = new ArrayList<>();

        contador = 0;
        instrucoes = 0;

        // iniciar a resolução do ponto inicial do tabuleiro.
        backtracking(0, n, tabuleiro, resposta, 0, 0, 0);

        return resposta;
    }

    public static boolean posicaoValida(int col, int n, int row, int linhas, int diagEsq, int diagDir) {

        instrucoes++;

        // linha ocupada
        if (((linhas >> row) & 1) != 0) return false;

        // diagonal esquerda (\)
        if (((diagEsq >> (row + col)) & 1) != 0) return false;

        // diagonal direita (/)
        if (((diagDir >> (row - col + (n - 1))) & 1) != 0) return false;

        return true;
    }

    public static void backtracking(int col, int n, ArrayList<Integer> tabuleiro, ArrayList<ArrayList<Integer>> resposta,
                             int linhas, int diagEsq, int diagDir) {

        contador++;
        instrucoes++;

        // Condição de parada: todas as rainhas estão posicionadas
        if (col == n) {
            resposta.add(new ArrayList<>(tabuleiro));
            instrucoes++;
            return;
        }

        // PASSO 1: Tentar posicionar uma rainha em todos os elementos da coluna atual
        for (int linha = 0; linha < n; linha++) {
            instrucoes++;

            // PASSO 1A: Verificar se a posição é válida
            if (posicaoValida(col, n, linha, linhas, diagEsq, diagDir)) {

                // PASSO 1B: Adicionar a posição da rainha na lista
                tabuleiro.add(linha);
                instrucoes++;

                // PASSO 1C: Chamar novamente o metodo para inserir a rainha na proxima coluna
                // Neste caso, usaremos o conceito de Bit Masking para representar as linhas ocupadas

                // EXPLICAÇÃO: No comando das linhas, colocamos um bit 1 na posição ocupada, em que
                // o uso de (1 << linha) deslocará o bit para a posição. Neste caso, | será responsável pela ativaçào do bit.

                // COLUNAS: Considerar a próxima
                // LINHAS: Parâmetro linhas vira <linhas | (1 << linha)>
                // DIAGONAL ESQUERDA: a mesma lógica, mas a coluna e a linha são somados
                // DIAGONAL DIREITA: a mesma lógica, mas há a subtração da linha e a soma do tamanho do tabuleiro.

                // CHAMADA RECURSIVA PARA INSERÇÃO DO PRÓXIMO TERMO
                backtracking(col+1, n, tabuleiro, resposta,
                        linhas | (1 << linha),
                        diagEsq | (1 << (col + linha)),
                        diagDir | (1 << (linha - col + (n - 1))));

                // PASSO 1D: Caso a condição não seja suprida, remoção da rainha.
                tabuleiro.removeLast();
                instrucoes++;
            }
        }

    }

    public static void main(String[] args) {
        for (int n = 4; n <= 6; n++) {
            System.out.println("\n---");
            System.out.println("N-Queens com n = " + n);
            long inicio = System.nanoTime();

            ArrayList<ArrayList<Integer>> resposta = NQueens.resolucao(n);

            long fim = System.nanoTime();
            long tempoMicro = (fim - inicio) / 1_000;

            if (resposta == null || resposta.isEmpty()) {
                System.out.println("Sem soluções para n = " + n);
            } else {
                int count = 1;
                for (ArrayList<Integer> solucao : resposta) {
                    System.out.print("--- SOLUÇÃO " + count++ + " --- \n[");
                    for (int i = 0; i < solucao.size(); i++) {
                        System.out.print(solucao.get(i) + 1);
                        if (i != solucao.size() - 1) System.out.print(" ");
                    }
                    System.out.println("]");
                }


            }

            System.out.println("Iterações: " + contador);
            System.out.println("Instruções: " + instrucoes);
            System.out.println("Tempo: " + tempoMicro + "micros");
        }
    }
}
