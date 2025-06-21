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
        return scan.nextInt();
    }

    public int renderizarMenuOpcoes(Save save, String textError) { // retorna a opção escolhida no menu

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
        return scan.nextInt();
    }

    /* adicionem outras funções conforme a necessidade ...*/

}
