package br.edu.unoesc.jogodaforca;

public class Main {
    public static void main(String[] args) {

        System.out.printf("Bem-vindo ao Jogo da Forca!");

        Jogo jogo = new Jogo();
        jogo.iniciarJogo();
        //jogo.adivinharPalavra("bana");
        jogo.adivinharLetra('d');
        jogo.adivinharLetra('v');
        jogo.adivinharLetra('l');
        jogo.adivinharLetra('s');
        jogo.adivinharLetra('c');
        jogo.adivinharLetra('-');
        //jogo.sair(false);

        System.out.printf("Finalizado: %b, Ganhado: %b, Saiu: %b \n", jogo.isJogoFinalizado(), jogo.isJogadorGanhou(), jogo.isJogadorSaiu());

    }
}