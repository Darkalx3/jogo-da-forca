package br.edu.unoesc.jogodaforca;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CLI {

    // Variáveis

    private Scanner scan;

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

        while (true) {
            try {
                System.out.flush();
                return Integer.parseInt(scan.next());
            } catch (NumberFormatException e) {
                System.out.println(" Entrada inválida. Digite um número (0, 1 ou 2).");
                System.out.print(" Sua opção: ");
            }
        }
    }

    public int renderizarMenuOpcoes(Save save, String textError){ // retorna a opção escolhida no menu

        limparTela();
        System.out.print("\n"+
                        " -----------------------------------------------\n"+
                        "                 JOGO DA FORCA\n"+
                        " -----------------------------------------------\n\n  "
                        );

        // Nome do Jogador
        System.out.print(String.format(" JOGADOR: %s\n\n", save.getNomeJogador()));

        // Tentativas
        printarForca(save.getQuantTentativas());

        // Print Palavra Adivinhada
        String palavraAdvinhada = save.getPalavraAdvinhada();
        System.out.print("  ");
        for(int i=0;i<palavraAdvinhada.length();i++) { // imprime a palavra adivinhada até agora
            System.out.print(String.format("%c ", Character.toUpperCase(palavraAdvinhada.charAt(i))));
        }

        // Print Letras Erradas
        System.out.print("\n"+
                         " ----------------LETRAS ERRADAS-----------------\n "
        );

        ArrayList<Character> letrasErradas = save.getLetrasErradas();

        for (Character letrasErrada : letrasErradas) {
            System.out.print(" ");
            System.out.print(String.format("%c ", Character.toUpperCase(letrasErrada)));
        }
        System.out.print("\n");

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
        
        while (true) {
            try {
                System.out.flush();
                return Integer.parseInt(scan.next());
            } catch (NumberFormatException e) {
                System.out.println(" Entrada inválida. Digite um número (0, 1 ou 2).");
                System.out.print(" Sua opção: ");
            }
        }
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
        limparTela();
        System.out.print("\n"+
                        " -----------------------------------------------\n"+
                        "                 JOGO DA FORCA\n"+
                        " -----------------------------------------------\n\n  "
                        );

        //Forca
        printarForca(save.getQuantTentativas());

        // NomeJogador
        System.out.println(String.format(" JOGADOR: %s\n\n", save.getNomeJogador()));

        // Print Palavra Adivinhada
        String palavraAdvinhada = save.getPalavraAdvinhada();
        System.out.print("  ");
        for(int i=0;i<palavraAdvinhada.length();i++) { // imprime a palavra adivinhada até agora
            System.out.print(String.format("%c ", Character.toUpperCase(palavraAdvinhada.charAt(i))));
        }

        // Print Letras Erradas
        System.out.print("\n"+
                         " ----------------LETRAS ERRADAS-----------------\n "
        );
        ArrayList<Character> letrasErradas = save.getLetrasErradas();

        for (Character letrasErrada : letrasErradas) {
            System.out.print(" ");
            System.out.print(String.format("%c ", Character.toUpperCase(letrasErrada)));
        }

        System.out.print("\n");
        if(jogadorGanhou) {
            System.out.print(" -----------------------------------------------\n"+
                             "              PARABÉNS, VOCÊ GANHOU\n"+
                             "                O JOGO DA FORCA\n"+
                             " -----------------------------------------------\n");
        } else {
            System.out.print(" -----------------------------------------------\n"+
                             "            INFELIZMENTE, VOCÊ PERDEU\n"+
                             "                O JOGO DA FORCA\n"+
                             " -----------------------------------------------\n");
        }
        System.out.print(String.format("  A PALAVRA ERA: %s\n"+
                                       " -----------------------------------------------\n"+
                                       " -\n Digite algo para continuar: ", save.getPalavraEscolhida().toUpperCase()));

        System.out.flush();
        scan.next();
    }

    public String renderizarNomeJogador() {
        System.out.print(" -\n Digite o nome do Jogador: ");

        System.out.flush();
        return scan.next();
    }

    public boolean renderizarSair() {
        System.out.print(" -\n Deseja salvar o jogo antes de sair? (s/n) ");
        String resposta = scan.next().trim().toLowerCase();
        return resposta.startsWith("s");
    }

    public void mostrarMensagemSaida() {
        System.out.print(" Obrigado por jogar!");
    }

    // Funções Privadas
    private void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void printarForca(int Tentativas){
        switch (Tentativas) {
            case 0:
                printarAnimacaoFimDeJogo();
                break;
            case 1:
                System.out.print(
                    "    _______\n" +
                    "   |/      T\n" +
                    "   |      /\n"+
                    "   |     |_(_) \n" +
                    "   |       /|\\ \n" +
                    "   |       /    \n" +
                    "   |   ____b_____\n"+
                    "   |   |        |\n" +
                    "   |   |        |\n" +
                    "  _|__ |        |\n\n"
                );
                break;
            case 2:
                System.out.print(
                    "    _______\n" +
                    "   |/      T\n" +
                    "   |      /\n"+
                    "   |     |_(_) \n" +
                    "   |       /|\\ \n" +
                    "   |           \n" +
                    "   |   __________\n"+
                    "   |   |        |\n" +
                    "   |   |        |\n" +
                    "  _|__ |        |\n\n"
                );
                break;
            case 3:
                System.out.print(
                    "    _______\n" +
                    "   |/      T\n" +
                    "   |      /\n"+
                    "   |     |_(_)\n" +
                    "   |       /| \n" +
                    "   |           \n" +
                    "   |   __________\n"+
                    "   |   |        |\n" +
                    "   |   |        |\n" +
                    "  _|__ |        |\n\n"
                );
                break;
            case 4:
                System.out.print(
                    "    _______\n" +
                    "   |/      T\n" +
                    "   |      /\n"+
                    "   |     |_(_)\n" +
                    "   |        | \n" +
                    "   |\n" +
                    "   |   __________\n"+
                    "   |   |        |\n" +
                    "   |   |        |\n" +
                    "  _|__ |        |\n\n"
                );
                break;
            case 5:
                System.out.print(
                    "    _______\n" +
                    "   |/      T\n" +
                    "   |      /\n"+
                    "   |     |_(_)\n" +
                    "   |       \n" +
                    "   |       \n" +
                    "   |   __________\n"+
                    "   |   |        |\n" +
                    "   |   |        |\n" +
                    "  _|__ |        |\n\n"
                );
                break;
            case 6:
                System.out.print(
                    "    _______\n" +
                    "   |/      T\n" +
                    "   |       |\n"+
                    "   |       |\n" +
                    "   |       |\n" +
                    "   |      \n" +
                    "   |   __________\n"+
                    "   |   |        |\n" +
                    "   |   |        |\n" +
                    "  _|__ |        |\n\n"
                );
                break;
            default:
                System.out.print("Erro ao renderizar a forca.");
        }
    }

    private void printarAnimacaoFimDeJogo(){
        try {
            for (int i = 0; i < 12; i++) {
                limparTela();
                System.out.print("\n"+
                            " -----------------------------------------------\n"+
                            "                 JOGO DA FORCA\n"+
                            " -----------------------------------------------\n\n  "
                            );
                printarFrame(i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private void printarFrame(int frame){
        switch (frame) {
            case 0:
                System.out.print(
                            "    _______\n" +
                            "   |/      T\n" +
                            "   |      /\n"+
                            "   |     |_(_)\n" +
                            "   |       /|\\ \n" +
                            "   |       / \\ \n" +
                            "   |   ____b_b___\n"+
                            "   |   |        |\n" +
                            "   |   |        |\n" +
                            "  _|__ |        |\n\n"
                );
            break;
            case 1:
                System.out.print(
                            "    _______\n" +
                            "   |/      T\n" +
                            "   |      /\n"+
                            "   |     |_(_)\n" +
                            "   |       /|\\ \n" +
                            "   |       / \\ \n" +
                            "   |   _   b b  _\n"+
                            "   |   | \\     /|\n" +
                            "   |   |        |\n" +
                            "  _|__ |        |\n\n"
                        );
            break;
            case 2:
                System.out.print(
                            "    _______\n" +
                            "   |/      T\n" +
                            "   |       /\n"+
                            "   |      |\n"+
                            "   |      \\(_)\n" +
                            "   |       /|\\ \n" +
                            "   |   _   / \\  _\n" +
                            "   |   | \\ b b /|\n"+
                            "   |   |        |\n" +
                            "  _|__ |        |\n\n"
                            );
            break;
            case 3:
                System.out.print(
                            "    _______\n" +
                            "   |/      T\n" +
                            "   |       |\n"+
                            "   |       |\n"+
                            "   |       |\n" +
                            "   |      \\(_)/ \n" +
                            "   |   _    |   _\n" +
                            "   |   ||  / | ||\n"+
                            "   |   |   b b  |\n" +
                            "  _|__ |        |\n\n"
                            );
            break;
            case 4:
                System.out.print(
                            "    _______\n" +
                            "   |/      T\n" +
                            "   |       |\n"+
                            "   |       |\n"+
                            "   |       |\n" +
                            "   |       (_)\n" +
                            "   |   _   /|\\  _\n" +
                            "   |   ||   |\\ ||\n"+
                            "   |   |    b b |\n" +
                            "  _|__ |        |\n\n"
                            );
            break;
            case 5:
                System.out.print(
                            "    _______\n" +
                            "   |/      T\n" +
                            "   |       |\n"+
                            "   |       |\n"+
                            "   |       |\n" +
                            "   |      \\(_)/ \n" +
                            "   |   _    |   _\n" +
                            "   |   ||  / | ||\n"+
                            "   |   |   b b  |\n" +
                            "  _|__ |        |\n\n"
                            );
            break;
            case 6:
                System.out.print(
                            "    _______\n" +
                            "   |/      T\n" +
                            "   |       |\n"+
                            "   |       |\n"+
                            "   |       |\n" +
                            "   |       (_)\n" +
                            "   |   _   /|\\  _\n" +
                            "   |   ||   |\\ ||\n"+
                            "   |   |    b b |\n" +
                            "  _|__ |        |\n\n"
                            );
            break;
            case 7:
                System.out.print(
                            "    _______\n" +
                            "   |/      T\n" +
                            "   |       |\n"+
                            "   |       |\n"+
                            "   |       |\n" +
                            "   |      \\(_)/ \n" +
                            "   |   _    |   _\n" +
                            "   |   ||  / | ||\n"+
                            "   |   |   b b  |\n" +
                            "  _|__ |        |\n\n"
                            );
            break;
            case 8:
                System.out.print(
                            "    _______\n" +
                            "   |/      T\n" +
                            "   |       |\n"+
                            "   |       |\n"+
                            "   |       |\n" +
                            "   |       (_)\n" +
                            "   |   _   /|\\  _\n" +
                            "   |   ||   |\\ ||\n"+
                            "   |   |    b b |\n" +
                            "  _|__ |        |\n\n"
                            );
            break;
            case 9:
                System.out.print(
                            "    _______\n" +
                            "   |/      T\n" +
                            "   |       |\n"+
                            "   |       |\n"+
                            "   |       |\n" +
                            "   |      \\(_)/ \n" +
                            "   |   _    |   _\n" +
                            "   |   ||  / | ||\n"+
                            "   |   |   b b  |\n" +
                            "  _|__ |        |\n\n"
                            );
            break;
            case 10:
                System.out.print(
                            "    _______\n" +
                            "   |/      T\n" +
                            "   |       |\n"+
                            "   |       |\n"+
                            "   |       |\n" +
                            "   |       (_)\n" +
                            "   |   _   /|\\  _\n" +
                            "   |   ||   |\\ ||\n"+
                            "   |   |    b b |\n" +
                            "  _|__ |        |\n\n"
                            );
            break;
            case 11:
                System.out.print(
                            "    _______\n" +
                            "   |/      T\n" +
                            "   |       |\n"+
                            "   |       |\n"+
                            "   |       |\n" +
                            "   |       (_)\n" +
                            "   |   _   |||  _\n" +
                            "   |   ||  ||  ||\n"+
                            "   |   |   b b  |\n" +
                            "  _|__ |        |\n\n"
                            );
        }
    }

}
