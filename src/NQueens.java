package src;

import java.util.ArrayList;
import java.util.List;

public class NQueens {

    // Primeiramente, criaremos um metodo principal para criação da matriz resposta.
    // Após isso, criação de estrutura de dados para representar o tabuleiro. Iniciar da primeira posição.

    public static ArrayList<ArrayList<Integer>> resolucao(int n) {

        // Primeira condição de parada: o tamanho do tabuleiro precisa ser maior ou igual a dois.
        if (n < 2) return null;

        // Instância de estrutura de dados
        ArrayList<ArrayList<Integer>> resposta = new ArrayList<>();
        ArrayList<Integer> tabuleiro = new ArrayList<>();

        // iniciar a resolução do ponto inicial do tabuleiro.
        backtracking(0, n, tabuleiro, resposta, 0, 0, 0);

        return resposta;
    }

    public static boolean posicaoValida( int coluna, int n, int linha, int linhas, int diagEsq, int diagDir) {

        // Primeira exigência: Verificação se a linha está ocupada.
        // Deslocamento de bits de linhas para a direita, verificando se o bit correspondente é 1.
        boolean linhaLivre = ((linhas >> linha) & 1) == 0;

        // Segunda exigência: Verificação da diagonal esquerda.
        // Deslocamento de bits igual a (linha + coluna) para verificar esta posição.
        boolean diagonalEsquerdaLivre = !(((diagEsq >> (linha + coluna)) & 1) == 1);

        // Terceira exigência: Verificação da diagonal direita.
        // Deslocamento de bits igual a (linha - coluna + n) para verificar esta posição.
        boolean diagonalDireitaLivre = ((diagDir >> (linha - coluna + n)) & 1) == 1;

        // Retorno precisa seguir as três exigências para ser uma posição válida.
        return !(linhaLivre && !diagonalEsquerdaLivre && !diagonalDireitaLivre);

    }

    public static void backtracking(int col, int n, ArrayList<Integer> tabuleiro, ArrayList<ArrayList<Integer>> resposta,
                             int linhas, int diagEsq, int diagDir) {

        // Condição de parada: todas as rainhas estão posicionadas
        if (col == n) {
            resposta.add(new ArrayList<>(tabuleiro));
            return;
        }

        // PASSO 1: Tentar posicionar uma rainha em todos os elementos da coluna atual
        for (int linha = 1; linha <= n; linha++) {

            // PASSO 1A: Verificar se a posição é válida
            if (posicaoValida(col, n, linha, linhas, diagEsq, diagDir)) {

                // PASSO 1B: Adicionar a posição da rainha na lista
                tabuleiro.add(linha);

                // PASSO 1C: Chamar novamente o metodo para inserir a rainha na proxima coluna
                // Neste caso, usaremos o conceito de Bit Masking para representar as linhas ocupadas

                // EXPLICAÇÃO: No comando das linhas, colocamos um bit 1 na posição ocupada, em que
                // o uso de (1 << linha) deslocará o bit para a posição. Neste caso, | será responsável pela ativaçào do bit.

                // LINHAS: Parâmetro linhas vira <linhas | (1 << linha)>
                // DIAGONAL ESQUERDA: a mesma lógica, mas a coluna e a linha são somados
                // DIAGONAL DIREITA: a mesma lógica, mas há a subtração da linha e a soma do tamanho do tabuleiro.

                // CHAMADA RECURSIVA PARA INSERÇÃO DO PRÓXIMO TERMO
                backtracking(col+1, n, tabuleiro, resposta, linhas | (1 << linha),
                        diagEsq | (1 << (col + linha)), diagDir | (1 << (col - linha + n)));

                // PASSO 1D: Caso a condição não seja suprida, remoção da rainha.
                tabuleiro.removeLast();
            }
        }

    }

    public static void main(String[] args) {
        int n = 4;
        ArrayList<ArrayList<Integer>> resposta = NQueens.resolucao(n);

        if (resposta != null) {
            for (ArrayList<Integer> solucao : resposta) {
                System.out.print("--- SOLUÇÃO " + (resposta.indexOf(solucao) + 1) + " --- \n[" );
                for (int i = 0; i < solucao.size(); i++) {
                    System.out.print(solucao.get(i));
                    if (i != n-1) System.out.print(" ");
                }
                System.out.println("]");
            }
        }


    }
}
