package br.edu.unoesc.jogodaforca;

import java.util.ArrayList;
import java.util.Scanner;

public class CLI {

    // Variáveis

    Scanner scan;

    // Construtor

    public CLI() {
        scan = new Scanner(System.in);
    }

    // Métodos

    public int renderizarMenu(String textError) {

        limparTela();
        System.out.print("\n"+
                         " -----------------------------------------------\n"+
                         "                 JOGO DA FORCA\n"+
                         " -----------------------------------------------\n"+
                         " [1] Iniciar Jogo\n"+
                         " [2] Continuar Jogo\n"+
                         " [0] Sair do Jogo\n"+
                         " -----------------------------------------------\n");

        if(textError==null) {
            System.out.print(" Sua opção: ");
        } else {
            System.out.print(String.format(" ERRO: %s\n"+
                                           " -----------------------------------------------\n"+
                                           " Sua opção: ", textError));
        }

        // Fazer um try catch aqui
        System.out.flush();
        return scan.nextInt();
    }

    public int renderizarMenuOpcoes(Save save, String textError) { // retorna a opção escolhida no menu

        limparTela();
        System.out.print("\n"+
                        " -----------------------------------------------\n"+
                        "                 JOGO DA FORCA\n"+
                        " -----------------------------------------------\n\n  "
                        );


        // Print Palavra Adivinhada

        String palavraAdvinhada = save.getPalavraAdvinhada();
        for(int i=0;i<palavraAdvinhada.length();i++) { // imprime a palavra adivinhada até agora
            System.out.print(String.format("%c ", Character.toUpperCase(palavraAdvinhada.charAt(i))));
        }

        // Print Letras Erradas

        System.out.print("\n"+
                         " ----------------LETRAS ERRADAS-----------------\n "
        );

        ArrayList<Character> letrasErradas = save.getLetrasErradas();

        for(int i=0;i<letrasErradas.size();i++) {
            System.out.print(" ");
                System.out.print(String.format("%c ", Character.toUpperCase(letrasErradas.get(i))));
        }
        System.out.print("\n");

        // Tentativas

        System.out.print(String.format(" -----------------------------------------------\n"+
                                       " TENTATIVAS: %d\n", save.getQuantTentativas()));

        // Print Menu de Opções

        System.out.print(" -----------------------------------------------\n"+
                         " [1] Adivinhar Letra\n"+
                         " [2] Adivinhar Palavra\n"+
                         " [0] Sair da Partida\n"+
                         " -----------------------------------------------\n");

        if(textError==null) {
            System.out.print(" Sua opção: ");
        } else {
            System.out.print(String.format(" ERRO: %s\n"+
                                           " -----------------------------------------------\n"+
                                           " Sua opção: ", textError));
        }

        // Fazer um try catch aqui
        System.out.flush();
        return scan.nextInt();
    }

    public char renderizarAdivinharLetra() {

        System.out.print("\n -\n Digite a letra: ");
        System.out.flush();
        return scan.next().charAt(0);
    }

    public String renderizarAdivinharPalavra() {

        System.out.print("\n -\n Digite a palavra: ");
        System.out.flush();
        return scan.next();
    }

    public void renderizarMenuFinal(Save save, boolean jogadorGanhou) {
        if(jogadorGanhou) {
            System.out.print(" -----------------------------------------------\n"+
                             "              PARABÉNS, VOCÊ GANHOU\n"+
                             "                O JOGO DA FORCA\n"+
                             " -----------------------------------------------\n");
        } else {
            System.out.print(" -----------------------------------------------\n"+
                             "            INFELIZMENTE, VOCÊ FEZ O L\n"+
                             "                NO JOGO DA FORCA\n"+
                             " -----------------------------------------------\n");
        }
        System.out.print(String.format("  A PALAVRA ERA: %s\n"+
                                       " -----------------------------------------------\n"+
                                       " -\n Digite algo para continuar: ", save.getPalavraEscolhida().toUpperCase()));

        System.out.flush();
        scan.next();
    }

    // Funções Privadas

    private void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /* adicionem outras funções conforme a necessidade ...*/

}
