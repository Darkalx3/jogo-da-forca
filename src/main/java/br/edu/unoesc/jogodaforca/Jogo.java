package br.edu.unoesc.jogodaforca;

import java.util.ArrayList;
import java.util.List;

public class Jogo {

    // Variáveis

    private boolean jogoIniciado;
    private boolean jogoFinalizado;
    private boolean jogadorGanhou;
    private boolean jogadorSaiu;
    private Save save;
    private Encapsulamento data;

    // Construtor

    public Jogo() {
        this.data = new Encapsulamento();
    }

    // Métodos

    public void iniciarJogo() {
        save = new Save(aleatorizarPalavra());
        definirEstadoPadrao();
    }

    public boolean continuarJogo() {
        if(data.existeEstado()) {
            save = data.carregarEstado();
            if(save==null) {
                return false;
            }
            definirEstadoPadrao();
            return true;
        }
        return false;
    }

    public boolean adivinharLetra(char letra) {
        letra = Character.toLowerCase(letra); // converte a letra para minúscula

        if(isJogoIniciado() && !isJogoFinalizado() && !letraJaEscolhida(letra)) {

            boolean possuiLetra = false;
            String palavra = save.getPalavra();

            for(int i=0;i<palavra.length();i++) {
                if(letra == palavra.charAt(i)) {
                    possuiLetra = true;
                    break;
                }
            }

            if(possuiLetra) {
                save.acrescentarLetrasAdivinhadas(letra);
            } else {
                save.acrescentarLetrasErradas(letra);
                save.diminuirQuantTentativas();
            }

            verificarEstado();
            return true;
        }
        return false;
    }

    public boolean adivinharPalavra(String palavra) {
        palavra = palavra.toLowerCase(); // converte a palavra para minúscula

        if(isJogoIniciado() && !isJogoFinalizado()) {

            if(palavra.equals(save.getPalavra())) {
                this.jogadorGanhou = true;
            } else {
                save.diminuirQuantTentativas();
            }

            verificarEstado();
            return true;
        }
        return false;
    }

    public boolean sair(boolean salvarJogo) {
        if(isJogoIniciado() && !isJogoFinalizado()) {
            if(salvarJogo) {
                data.salvarEstado(this.save);
            }
            jogoFinalizado = true;
            jogadorSaiu = true;
            return true;
        }
        return false;
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

    // Métodos Privados

    private boolean letraJaEscolhida(char letra) {

        ArrayList<Character> letrasAdvinhadas = save.getLetrasAdivinhadas();
        ArrayList<Character> letrasErradas = save.getLetrasErradas();

        for(int i=0;i<letrasAdvinhadas.size();i++) {
            if(letrasAdvinhadas.get(i)==letra) {
                return true;
            }
        }

        for(int i=0;i<letrasErradas.size();i++) {
            if(letrasErradas.get(i)==letra) {
                return true;
            }
        }

        return false;
    }

    private String aleatorizarPalavra() {
        List<String> lista = data.PegarListaDePalavras();

        if(lista==null || lista.isEmpty()) {
            return "perseverar"; // apenas para testes, dá para colocar uma lista padrão e fazer um random do mesmo jeito, só adicionar algumas palavras padrão
        }

        // Código para aleatorizar uma entre as palavras, não esqueça de usar .toLowerCase() antes de retornar a palavra

        return "lua"; // apenas para testes, remove depois de implementar o aleatorizar
    }

    private void definirEstadoPadrao() {
        this.jogoIniciado = true;
        this.jogoFinalizado = false;
        this.jogadorSaiu = false;
        this.jogadorGanhou = false;
    }

    private void verificarEstado() {
        if (isJogadorGanhou() || save.getQuantTentativas()==0) {
            this.jogoFinalizado = true;
        } else if(save.getPalavraAdvinhada().equals(save.getPalavra())){
            this.jogoFinalizado = true;
            this.jogadorGanhou = true;
        }
    }
}
