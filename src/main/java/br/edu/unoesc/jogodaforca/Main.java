package br.edu.unoesc.jogodaforca;

public class Main {
    public static void main(String[] args) {

        System.out.println("Bem-vindo ao Jogo da Forca!");

        Jogo jogo = new Jogo();
        jogo.iniciarJogo();
        //jogo.adivinharPalavra("bana");
        jogo.adivinharLetra('B');
        jogo.adivinharLetra('a');
        jogo.adivinharPalavra("BaNAna");
        jogo.sair(true);
        //jogo.sair(false);

        System.out.printf("Finalizado: %b, Ganhado: %b, Saiu: %b \n", jogo.isJogoFinalizado(), jogo.isJogadorGanhou(), jogo.isJogadorSaiu());

    }
}