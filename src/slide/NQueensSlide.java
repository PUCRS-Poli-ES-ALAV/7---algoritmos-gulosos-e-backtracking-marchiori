package src.slide;

import java.util.ArrayList;
import java.util.List;

public class NQueensSlide {

    public static List<List<String>> resolver(int n) {

        // METODO APENAS PARA INICIALIZAR AS VARIÁVEIS.

        List<List<String>> resultados = new ArrayList<>();
        char[][] tabuleiro = new char[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tabuleiro[i][j] = '.';
            }
        }

        backtrack(resultados, tabuleiro, 0, n);
        return resultados;
    }

    public static void backtrack(List<List<String>> resultados, char[][] tabuleiro, int linha, int n) {

        // Se chegou na última linha, significa que todas as rainhas foram posicionadas.
        if (linha == n) {
            resultados.add(criarSolucao(tabuleiro));
            // Registramos a solução encontrada e retornamos.
            return;
        }

        // Vamos percorrer todas as colunas!
        for (int coluna = 0; coluna < n; coluna++) {
            // PARA SER VÁLIDO:
            // 1. Todas as posições da mesma coluna devem ser livres.
            // 2. Todas as posições da diagonal esquerda devem ser livres.
            // 3. Todas as posições da diagonal direita devem ser livres.
            if (ehValido(tabuleiro, linha, coluna, n)) {
                // Se a posição é válida, colocamos a rainha.
                tabuleiro[linha][coluna] = 'Q';
                // Tentamos com a linha seguinte.
                backtrack(resultados, tabuleiro, linha + 1, n);
                // Se não for possível, desfazemos a escolha (poda).
                tabuleiro[linha][coluna] = '.'; // desfaz a escolha (poda)
            }
        }
    }

    public static boolean ehValido(char[][] tabuleiro, int linha, int coluna, int n) {
        // Mesma coluna
        for (int i = 0; i < linha; i++) {
            if (tabuleiro[i][coluna] == 'Q') return false;
        }

        // Diagonal esquerda
        for (int i = linha - 1, j = coluna - 1; i >= 0 && j >= 0; i--, j--) {
            if (tabuleiro[i][j] == 'Q') return false;
        }

        // Diagonal direita
        for (int i = linha - 1, j = coluna + 1; i >= 0 && j < n; i--, j++) {
            if (tabuleiro[i][j] == 'Q') return false;
        }

        return true;
    }

    public static List<String> criarSolucao(char[][] tabuleiro) {
        List<String> solucao = new ArrayList<>();
        for (char[] linha : tabuleiro) {
            solucao.add(new String(linha));
        }
        return solucao;
    }

    public static void main(String[] args) {
        int n = 6;
        List<List<String>> solucoes = resolver(n);

        int count = 1;
        for (List<String> solucao : solucoes) {
            System.out.println("--- Solução " + count++ + " ---");
            for (String linha : solucao) {
                System.out.println(linha);
            }
            System.out.println();
        }
    }
}