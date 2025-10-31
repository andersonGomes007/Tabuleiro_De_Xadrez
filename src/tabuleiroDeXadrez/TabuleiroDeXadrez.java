package tabuleiroDeXadrez;

import javax.swing.*;

public class TabuleiroDeXadrez {

    public static void main(String[] args) {
        tabuleiroDeXadrez.Tabuleiro tabuleiro = new tabuleiroDeXadrez.Tabuleiro();
        tabuleiro.pecaInicial();
        tabuleiroDeXadrez.ValidadorDeMovimento validador = new tabuleiroDeXadrez.ValidadorDeMovimento();
        tabuleiroDeXadrez.CorDaPeca corDoJogadorAtual = tabuleiroDeXadrez.CorDaPeca.BRANCO;

        while (true) {
            tabuleiro.mostrarTabuleiro();
            String jogadaStr = JOptionPane.showInputDialog(null,
                    "Vez do jogador " + corDoJogadorAtual + ". Digite sua jogada (ex: e2-e4)",
                    "Jogo de Xadrez",
                    JOptionPane.QUESTION_MESSAGE);

            if (jogadaStr == null) {
                // Usuário fechou a caixa de diálogo
                break;
            }

            try {
                int[] moveCoords = parseMove(jogadaStr);
                int linhaInicial = moveCoords[0];
                int colunaInicial = moveCoords[1];
                int linhaFinal = moveCoords[2];
                int colunaFinal = moveCoords[3];

                Mover mover = new Mover(linhaInicial, colunaInicial, linhaFinal, colunaFinal);

                if (validador.validarMovimento(tabuleiro, mover, corDoJogadorAtual)) {
                    tabuleiro.moverPeca(mover);
                    corDoJogadorAtual = (corDoJogadorAtual == CorDaPeca.BRANCO) ? CorDaPeca.PRETO : CorDaPeca.BRANCO;
                } else {
                    JOptionPane.showMessageDialog(null, "Movimento inválido! Tente novamente.", "Erro de Movimento", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static int[] parseMove(String jogada) {
        jogada = jogada.trim().toLowerCase();
        if (!jogada.matches("[a-h][1-8]-[a-h][1-8]")) {
            throw new IllegalArgumentException("Formato de jogada inválido. Use o formato 'e2-e4'.");
        }

        char colInicialChar = jogada.charAt(0);
        int linhaInicial = 8 - Character.getNumericValue(jogada.charAt(1));
        char colFinalChar = jogada.charAt(3);
        int linhaFinal = 8 - Character.getNumericValue(jogada.charAt(4));

        int colunaInicial = colInicialChar - 'a';
        int colunaFinal = colFinalChar - 'a';

        return new int[]{linhaInicial, colunaInicial, linhaFinal, colunaFinal};
    }
}
