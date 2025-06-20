package br.edu.unoesc.jogodaforca;

public class Jogo {

    // Variáveis

    private boolean jogoIniciado;
    private boolean jogoFinalizado;
    private boolean jogadorGanhou;
    private boolean jogadorSaiu;
    private Save save;
    private Encapsulamento data;

    // Construtor

    public Jogo() {}

    // Métodos

    public void iniciarJogo() {
        save = new Save(aleatorizarPalavra());
        definirEstadoPadrao();
    }

    public boolean continuarJogo() {
        if(data.existeEstado()) {
            save = data.carregarEstado();
            definirEstadoPadrao();
            return true;
        }
        return false;
    }

    public boolean adivinharLetra(char letra) {
        if(isJogoIniciado() && !isJogoFinalizado()) {

            // Codigo para adivinhar letra

            verificarEstado();
            return true;
        }
        return false;
    }

    public boolean adivinharPalavra(String palavra) {
        if(isJogoIniciado() && !isJogoFinalizado()) {

            // Codigo para adivinhar palavra

            verificarEstado();
            return true;
        }
        return false;
    }

    public boolean sair(boolean salvarJogo) {
        if(isJogoIniciado() && !isJogoFinalizado() && salvarJogo) {
                data.salvarEstado(this.save);
                jogadorSaiu = true;
                jogoFinalizado = true;
                return true;
        }
        return false;
    }

    // Métodos Privados

    private String aleatorizarPalavra() {
        return "Banana"; // apenas para testes
    }

    private void definirEstadoPadrao() {
        this.jogoIniciado = true;
        this.jogoFinalizado = false;
        this.jogadorSaiu = false;
        this.jogadorGanhou = false;
    }

    private void verificarEstado() {

        /* essa função deve verificar o estado do jogo, se ele foi encerrado por quantidades de tentativas ou se o jogador ganhou */

    }

    // Métodos Get

    public Save retornarEstado() {
        return this.save;
    }

    public boolean isJogoIniciado() {
        return jogoIniciado;
    }

    public boolean isJogoFinalizado() {
        return jogoFinalizado;
    }

    public boolean isJogadorGanhou() {
        return jogadorGanhou;
    }

    public boolean isJogadorSaiu() {
        return jogadorSaiu;
    }
}
