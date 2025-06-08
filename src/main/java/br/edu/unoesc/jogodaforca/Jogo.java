package br.edu.unoesc.jogodaforca;

public class Jogo {

    // Variáveis

    private Save save;
    private boolean jogoIniciado;
    private CLI cli;

    // Construtores

    public Jogo() {

    }
    public Jogo(Save save) {
        carregarSave(save);
    }

    // Métodos

    public void iniciarJogo() {

    }
    public void continuarJogo() {

    }
    public void adivinharLetra() {

    }
    public void adivinharPalavra() {

    }
    public void sair() {

    }
    public String aleatorizarPalavra() {
        return "Banana"; // apenas para testes
    }
    public void verificarEstado() {

    }
    public void exibirEstado() {

    }
    public void finalizarJogo() {

    }

    // Métodos Get e Set

    public Save getSave() {
        return save;
    }
    public void carregarSave(Save save) {
        this.save = save;
    }
    public boolean isJogoIniciado() {
        return jogoIniciado;
    }
}
