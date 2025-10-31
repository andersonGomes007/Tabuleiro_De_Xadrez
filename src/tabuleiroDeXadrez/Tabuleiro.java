package tabuleiroDeXadrez;

import java.util.Arrays;

public class Tabuleiro {
    private final Peca[][] posicoes;

    public Tabuleiro() {
        this.posicoes = new Peca[8][8];
    }

    public void pecaInicial() {
        // Pecas Pretas
        posicoes[0][0] = new Peca(TipoDaPeca.TORRE, CorDaPeca.PRETO);
        posicoes[0][1] = new Peca(TipoDaPeca.CAVALO, CorDaPeca.PRETO);
        posicoes[0][2] = new Peca(TipoDaPeca.BISPO, CorDaPeca.PRETO);
        posicoes[0][3] = new Peca(TipoDaPeca.DAMA, CorDaPeca.PRETO);
        posicoes[0][4] = new Peca(TipoDaPeca.REI, CorDaPeca.PRETO);
        posicoes[0][5] = new Peca(TipoDaPeca.BISPO, CorDaPeca.PRETO);
        posicoes[0][6] = new Peca(TipoDaPeca.CAVALO, CorDaPeca.PRETO);
        posicoes[0][7] = new Peca(TipoDaPeca.TORRE, CorDaPeca.PRETO);
        for (int i = 0; i < 8; i++) {
            posicoes[1][i] = new Peca(TipoDaPeca.PEAO, CorDaPeca.PRETO);
        }

        // Pecas Brancas
        posicoes[7][0] = new Peca(TipoDaPeca.TORRE, CorDaPeca.BRANCO);
        posicoes[7][1] = new Peca(TipoDaPeca.CAVALO, CorDaPeca.BRANCO);
        posicoes[7][2] = new Peca(TipoDaPeca.BISPO, CorDaPeca.BRANCO);
        posicoes[7][3] = new Peca(TipoDaPeca.DAMA, CorDaPeca.BRANCO);
        posicoes[7][4] = new Peca(TipoDaPeca.REI, CorDaPeca.BRANCO);
        posicoes[7][5] = new Peca(TipoDaPeca.BISPO, CorDaPeca.BRANCO);
        posicoes[7][6] = new Peca(TipoDaPeca.CAVALO, CorDaPeca.BRANCO);
        posicoes[7][7] = new Peca(TipoDaPeca.TORRE, CorDaPeca.BRANCO);
        for (int i = 0; i < 8; i++) {
            posicoes[6][i] = new Peca(TipoDaPeca.PEAO, CorDaPeca.BRANCO);
        }
    }

    public Peca getPeca(int linha, int coluna) {
        if (linha < 0 || linha >= 8 || coluna < 0 || coluna >= 8) {
            return null;
        }
        return posicoes[linha][coluna];
    }

    public void setPeca(int linha, int coluna, Peca peca) {
        posicoes[linha][coluna] = peca;
    }

    public void moverPeca(Mover mover) {
        Peca peca = getPeca(mover.getLinhaInicial(), mover.getColunaInicial());
        setPeca(mover.getLinhaFinal(), mover.getColunaFinal(), peca);
        setPeca(mover.getLinhaInicial(), mover.getColunaInicial(), null);
    }

    public Tabuleiro copia() {
        Tabuleiro novoTabuleiro = new Tabuleiro();
        for (int i = 0; i < 8; i++) {
            novoTabuleiro.posicoes[i] = Arrays.copyOf(this.posicoes[i], 8);
        }
        return novoTabuleiro;
    }

    public void mostrarTabuleiro() {
        System.out.println("  a b c d e f g h");
        System.out.println("-------------------");
        for (int linha = 0; linha < 8; linha++) {
            System.out.print((8 - linha) + "|");
            for (int coluna = 0; coluna < 8; coluna++) {
                Peca peca = getPeca(linha, coluna);
                if (peca == null) {
                    System.out.print(" .");
                } else {
                    System.out.print(" " + peca.getSimbolo());
                }
            }
            System.out.println("|" + (8 - linha));
        }
        System.out.println("-------------------");
        System.out.println("  a b c d e f g h");
    }

    public int[] encontrarRei(CorDaPeca corDoRei) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Peca peca = posicoes[i][j];
                if (peca != null && peca.getTipo() == TipoDaPeca.REI && peca.getCor() == corDoRei) {
                    return new int[]{i, j};
                }
            }
        }
        return null; // Rei nao encontrado (nao deveria acontecer em um jogo valido)
    }

    public boolean estaEmXeque(CorDaPeca corDoRei, ValidadorDeMovimento validador) {
        int[] posRei = encontrarRei(corDoRei);
        if (posRei == null) {
            return false; // Nao ha rei no tabuleiro
        }

        int linhaRei = posRei[0];
        int colunaRei = posRei[1];

        CorDaPeca corOponente = (corDoRei == CorDaPeca.BRANCO) ? CorDaPeca.PRETO : CorDaPeca.BRANCO;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Peca pecaOponente = posicoes[i][j];
                if (pecaOponente != null && pecaOponente.getCor() == corOponente) {
                    // Simula o movimento da peca do oponente para a posicao do rei
                    Mover moverSimulado = new Mover(i, j, linhaRei, colunaRei);
                    if (validador.validarMovimento(this, moverSimulado, corOponente)) {
                        return true; // Rei esta em xeque
                    }
                }
            }
        }
        return false;
    }
}