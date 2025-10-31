package tabuleiroDeXadrez;

public class Mover {
    private int linhaInicial;
    private int colunaInicial;
    private int linhaFinal;
    private int colunaFinal;

    public Mover(int linhaInicial, int colunaInicial, int linhaFinal, int colunaFinal) {
        this.linhaInicial = linhaInicial;
        this.colunaInicial = colunaInicial;
        this.linhaFinal = linhaFinal;
        this.colunaFinal = colunaFinal;
    }

    public int getLinhaInicial() {
        return linhaInicial;
    }

    public void setLinhaInicial(int linhaInicial) {
        this.linhaInicial = linhaInicial;
    }

    public int getColunaInicial() {
        return colunaInicial;
    }

    public void setColunaInicial(int colunaInicial) {
        this.colunaInicial = colunaInicial;
    }

    public int getLinhaFinal() {
        return linhaFinal;
    }

    public void setLinhaFinal(int linhaFinal) {
        this.linhaFinal = linhaFinal;
    }

    public int getColunaFinal() {
        return colunaFinal;
    }

    public void setColunaFinal(int colunaFinal) {
        this.colunaFinal = colunaFinal;
    }
}
