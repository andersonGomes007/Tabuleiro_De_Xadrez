package tabuleiroDeXadrez;

public class ValidadorDeMovimento {

    public boolean validarMovimento(Tabuleiro tabuleiro, Mover mover, CorDaPeca corDoJogador) {
        Peca peca = tabuleiro.getPeca(mover.getLinhaInicial(), mover.getColunaInicial());
        if (peca == null || peca.getCor() != corDoJogador) {
            return false;
        }

        switch (peca.getTipo()) {
            case PEAO:
                return validarMovimentoPeao(tabuleiro, mover, corDoJogador);
            case TORRE:
                return validarMovimentoTorre(tabuleiro, mover, corDoJogador);
            case CAVALO:
                return validarMovimentoCavalo(tabuleiro, mover, corDoJogador);
            case BISPO:
                return validarMovimentoBispo(tabuleiro, mover, corDoJogador);
            case DAMA:
                return validarMovimentoDama(tabuleiro, mover, corDoJogador);
            case REI:
                return validarMovimentoRei(tabuleiro, mover, corDoJogador);
            default:
                return false;
        }
    }

    private boolean validarMovimentoPeao(Tabuleiro tabuleiro, Mover mover, CorDaPeca corDoJogador) {
        int direcao = (corDoJogador == CorDaPeca.BRANCO) ? -1 : 1;
        int linhaInicial = mover.getLinhaInicial();
        int linhaFinal = mover.getLinhaFinal();
        int colunaInicial = mover.getColunaInicial();
        int colunaFinal = mover.getColunaFinal();

        // Movimento de 1 casa para frente
        if (colunaInicial == colunaFinal && tabuleiro.getPeca(linhaFinal, colunaFinal) == null) {
            if (linhaFinal == linhaInicial + direcao) {
                return true;
            }
            // Movimento de 2 casas para frente no primeiro movimento
            if (linhaFinal == linhaInicial + 2 * direcao && ((corDoJogador == CorDaPeca.BRANCO && linhaInicial == 6) || (corDoJogador == CorDaPeca.PRETO && linhaInicial == 1))) {
                return tabuleiro.getPeca(linhaInicial + direcao, colunaInicial) == null;
            }
        }

        // Captura na diagonal
        if (Math.abs(colunaInicial - colunaFinal) == 1 && linhaFinal == linhaInicial + direcao) {
            Peca pecaCapturada = tabuleiro.getPeca(linhaFinal, colunaFinal);
            return pecaCapturada != null && pecaCapturada.getCor() != corDoJogador;
        }

        return false;
    }

    private boolean validarMovimentoTorre(Tabuleiro tabuleiro, Mover mover, CorDaPeca corDoJogador) {
        int linhaInicial = mover.getLinhaInicial();
        int linhaFinal = mover.getLinhaFinal();
        int colunaInicial = mover.getColunaInicial();
        int colunaFinal = mover.getColunaFinal();

        if (linhaInicial != linhaFinal && colunaInicial != colunaFinal) {
            return false; // Nao esta se movendo em linha reta
        }

        // Verifica se ha pecas no caminho
        if (linhaInicial == linhaFinal) {
            int direcao = (colunaFinal > colunaInicial) ? 1 : -1;
            for (int i = colunaInicial + direcao; i != colunaFinal; i += direcao) {
                if (tabuleiro.getPeca(linhaInicial, i) != null) {
                    return false;
                }
            }
        } else {
            int direcao = (linhaFinal > linhaInicial) ? 1 : -1;
            for (int i = linhaInicial + direcao; i != linhaFinal; i += direcao) {
                if (tabuleiro.getPeca(i, colunaInicial) != null) {
                    return false;
                }
            }
        }

        Peca pecaFinal = tabuleiro.getPeca(linhaFinal, colunaFinal);
        return pecaFinal == null || pecaFinal.getCor() != corDoJogador;
    }

    private boolean validarMovimentoCavalo(Tabuleiro tabuleiro, Mover mover, CorDaPeca corDoJogador) {
        int linhaInicial = mover.getLinhaInicial();
        int linhaFinal = mover.getLinhaFinal();
        int colunaInicial = mover.getColunaInicial();
        int colunaFinal = mover.getColunaFinal();

        int deltaLinha = Math.abs(linhaInicial - linhaFinal);
        int deltaColuna = Math.abs(colunaInicial - colunaFinal);

        if ((deltaLinha == 2 && deltaColuna == 1) || (deltaLinha == 1 && deltaColuna == 2)) {
            Peca pecaFinal = tabuleiro.getPeca(linhaFinal, colunaFinal);
            return pecaFinal == null || pecaFinal.getCor() != corDoJogador;
        }

        return false;
    }

    private boolean validarMovimentoBispo(Tabuleiro tabuleiro, Mover mover, CorDaPeca corDoJogador) {
        int linhaInicial = mover.getLinhaInicial();
        int linhaFinal = mover.getLinhaFinal();
        int colunaInicial = mover.getColunaInicial();
        int colunaFinal = mover.getColunaFinal();

        if (Math.abs(linhaInicial - linhaFinal) != Math.abs(colunaInicial - colunaFinal)) {
            return false; // Nao esta se movendo na diagonal
        }

        int direcaoLinha = (linhaFinal > linhaInicial) ? 1 : -1;
        int direcaoColuna = (colunaFinal > colunaInicial) ? 1 : -1;

        int i = linhaInicial + direcaoLinha;
        int j = colunaInicial + direcaoColuna;

        while (i != linhaFinal && j != colunaFinal) {
            if (tabuleiro.getPeca(i, j) != null) {
                return false; // Peca no caminho
            }
            i += direcaoLinha;
            j += direcaoColuna;
        }

        Peca pecaFinal = tabuleiro.getPeca(linhaFinal, colunaFinal);
        return pecaFinal == null || pecaFinal.getCor() != corDoJogador;
    }

    private boolean validarMovimentoDama(Tabuleiro tabuleiro, Mover mover, CorDaPeca corDoJogador) {
        // A Dama se move como a Torre e o Bispo
        return validarMovimentoTorre(tabuleiro, mover, corDoJogador) || validarMovimentoBispo(tabuleiro, mover, corDoJogador);
    }

    private boolean validarMovimentoRei(Tabuleiro tabuleiro, Mover mover, CorDaPeca corDoJogador) {
        int linhaInicial = mover.getLinhaInicial();
        int linhaFinal = mover.getLinhaFinal();
        int colunaInicial = mover.getColunaInicial();
        int colunaFinal = mover.getColunaFinal();

        int deltaLinha = Math.abs(linhaInicial - linhaFinal);
        int deltaColuna = Math.abs(colunaInicial - colunaFinal);

        if (deltaLinha <= 1 && deltaColuna <= 1) {
            Peca pecaFinal = tabuleiro.getPeca(linhaFinal, colunaFinal);
            return pecaFinal == null || pecaFinal.getCor() != corDoJogador;
        }

        return false;
    }
}
