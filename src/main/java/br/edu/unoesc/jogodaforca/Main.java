package br.edu.unoesc.jogodaforca;

import javax.crypto.ExemptionMechanismException;

public class Main {
    public static void main(String[] args) {

        // Variáveis

        Jogo jogo = new Jogo();
        CLI cli = new CLI();

        // Variáveis Auxiliares

        int opt = 0;
        char letra = ' ';
        String palavra = "";
        String errorMenu = null;
        String errorMenuOpcao = null;

        // Loop do Jogo

        while(true) {
            opt = cli.renderizarMenu(errorMenu);

            // Verificações de Inicio de Jogo

            if (opt == 0) {
                break;
            } else if (opt == 1) {
                jogo.iniciarJogo();
                errorMenu = null;
            } else if (opt == 2) {
                if (!jogo.continuarJogo()) {
                    errorMenu = "Não foi possível continuar o jogo";
                } else {
                    errorMenu = null;
                }
            } else {
                errorMenu = "A opção não existe";
            }

            // Loop da Partida

            try {
                while (jogo.isJogoIniciado() && !jogo.isJogoFinalizado()) {
                    opt = cli.renderizarMenuOpcoes(jogo.retornarEstado(), errorMenuOpcao);

                    // Verificação de Opções da Partida

                    if (opt == 0) {
                        boolean salvar = cli.renderizarSair();
                        jogo.sair(salvar); // Precisa de CLI para isso
                        errorMenuOpcao = null;
                        break;
                    } else if (opt == 1) {
                        letra = cli.renderizarAdivinharLetra();
                        if (!jogo.adivinharLetra(letra)) {
                            errorMenuOpcao = "Essa letra não é válida";
                        } else {
                            errorMenuOpcao = null;
                        }
                    } else if (opt == 2) {
                        palavra = cli.renderizarAdivinharPalavra();
                        if (!jogo.adivinharPalavra(palavra)) {
                            errorMenuOpcao = "Não foi possível adivinhar Palavra";
                        } else {
                            errorMenuOpcao = null;
                        }
                    } else {
                        errorMenuOpcao = "A opção não existe";
                    }
                }
            } catch (Exception e) {
                errorMenu = "Aconteceu um erro durante sua partida";
                jogo = new Jogo();
            }

            // Verifica o Final da Partida

            if (errorMenu == null && !jogo.isJogadorSaiu()) {
                cli.renderizarMenuOpcoes(jogo.retornarEstado(), errorMenuOpcao);
                cli.renderizarMenuFinal(jogo.retornarEstado(), jogo.isJogadorGanhou());
            }
        }
        cli.mostrarMensagemSaida();
    }
}