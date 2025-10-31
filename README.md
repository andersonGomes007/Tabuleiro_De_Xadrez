# Tabuleiro de Xadrez

Um simples jogo de xadrez implementado em Java.

## Descrição

Este projeto é uma implementação de um jogo de xadrez em modo texto com uma interface gráfica simples para entrada de dados, utilizando `JOptionPane`. O objetivo é fornecer uma implementação funcional das regras de movimento das peças de xadrez e da lógica básica do jogo.

## Funcionalidades

*   Tabuleiro de xadrez 8x8.
*   Movimentação de todas as peças de xadrez: Peão, Torre, Cavalo, Bispo, Dama e Rei.
*   Validação de movimentos básicos para cada peça.
*   Alternância de turnos entre jogadores (Branco e Preto).
*   Detecção de condição de xeque.
*   Interface de entrada de jogadas via `JOptionPane`.

## Como Executar

1.  **Pré-requisitos:**
    *   Java Development Kit (JDK) instalado.

2.  **Compilação:**
    Navegue até o diretório `src` e compile os arquivos Java:
    ```bash
    javac tabuleiroDeXadrez/*.java
    ```

3.  **Execução:**
    Ainda no diretório `src`, execute a classe principal:
    ```bash
    java tabuleiroDeXadrez.TabuleiroDeXadrez
    ```

## Como Jogar

*   Ao executar o programa, o tabuleiro de xadrez será exibido no console.
*   Uma caixa de diálogo (`JOptionPane`) aparecerá solicitando a jogada do jogador atual.
*   As jogadas devem ser inseridas no formato de notação de xadrez, por exemplo: `e2-e4`.
*   O jogo alterna entre os jogadores (Branco e Preto) a cada jogada válida.
*   Se um movimento for inválido, uma mensagem de erro será exibida.

## Estrutura do Projeto

*   `src/tabuleiroDeXadrez/`: Contém todos os arquivos-fonte do projeto.
    *   `TabuleiroDeXadrez.java`: A classe principal que contém o loop do jogo.
    *   `Tabuleiro.java`: Representa o tabuleiro de xadrez e o estado do jogo.
    *   `Peca.java`: Representa uma peça de xadrez.
    *   `Mover.java`: Representa um movimento de uma peça.
    *   `ValidadorDeMovimento.java`: Contém a lógica para validar os movimentos das peças.
    *   `CorDaPeca.java`: Enum для as cores das peças (BRANCO, PRETO).
    *   `TipoDaPeca.java`: Enum para os tipos de peças (PEAO, TORRE, etc.).
