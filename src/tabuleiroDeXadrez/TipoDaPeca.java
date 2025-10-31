package tabuleiroDeXadrez;

public enum TipoDaPeca {
    TORRE('t'),
    CAVALO('c'),
    BISPO('b'),
    DAMA('d'),
    REI('r'),
    PEAO('p');

    private final char simbolo;

    TipoDaPeca(char simbolo) {
        this.simbolo = simbolo;
    }

    public char getSimbolo() {
        return simbolo;
    }
}