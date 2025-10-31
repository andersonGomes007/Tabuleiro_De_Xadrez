package tabuleiroDeXadrez;

public class Peca {
    private CorDaPeca cor;
    private TipoDaPeca tipo;

    public Peca(TipoDaPeca tipo, CorDaPeca cor) {
        this.tipo = tipo;
        this.cor = cor;
    }

    public CorDaPeca getCor() {
        return cor;
    }

    public void setCor(CorDaPeca cor) {
        this.cor = cor;
    }

    public TipoDaPeca getTipo() {
        return tipo;
    }

    public void setTipo(TipoDaPeca tipo) {
        this.tipo = tipo;
    }

    public char getSimbolo() {
        if (cor == CorDaPeca.BRANCO) {
            return Character.toUpperCase(tipo.getSimbolo());
        } else {
            return tipo.getSimbolo();
        }
    }
}