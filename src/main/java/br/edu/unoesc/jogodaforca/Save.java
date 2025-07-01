package br.edu.unoesc.jogodaforca;

import java.io.Serializable;
import java.util.ArrayList;

public class Save implements Serializable {

    // Variáveis

    private static final long serialVersionUID = 2L;
    private String nomeJogador;
    private ArrayList<Character> letrasAdivinhadas;
    private ArrayList<Character> letrasErradas;
    private String palavraAdvinhada;
    private String palavraEscolhida;
    private int quantTentativas;

    // Construtores

    public Save(String palavra, String nomeJogador) {
        /* quantTentativas pode ser por padrão 6 */
        this.quantTentativas = 6;
        this.nomeJogador = nomeJogador;
        this.palavraEscolhida = palavra;
        this.palavraAdvinhada = "";
        letrasAdivinhadas = new ArrayList<>();
        letrasErradas = new ArrayList<>();
        atualizarPalavraAdvinhada();
    }

    public Save(String palavra, int quantTentativas, String nomeJogador) {
        this.quantTentativas = quantTentativas;
        this.nomeJogador = nomeJogador;
        this.palavraEscolhida = palavra;
        this.palavraAdvinhada = "";
        letrasAdivinhadas = new ArrayList<>();
        letrasErradas = new ArrayList<>();
        atualizarPalavraAdvinhada();
    }

    // Métodos de Adição / Subtração

    public boolean diminuirQuantTentativas() {
        if (getQuantTentativas()>0) {
            this.quantTentativas -= 1;
            return true;
        }
        return false;
    }

    public void acrescentarLetrasAdivinhadas(char letra) {
        letrasAdivinhadas.add(letra);
        atualizarPalavraAdvinhada();
    }

    public void acrescentarLetrasErradas(char letra) {
        letrasErradas.add(letra);
    }

    // Métodos Get e Set

    public ArrayList<Character> getLetrasAdivinhadas() {
        return letrasAdivinhadas;
    }

    public ArrayList<Character> getLetrasErradas() {
        return letrasErradas;
    }

    public String getPalavraEscolhida() {
        return palavraEscolhida;
    }

    public int getQuantTentativas() {
        return quantTentativas;
    }

    public String getPalavraAdvinhada() {
        return palavraAdvinhada;
    }

    public String getNomeJogador() { return nomeJogador; }

    // Métodos Privados

    private boolean letraFoiAdivinhada(char letra) {
        for(int i=0;i<this.letrasAdivinhadas.size();i++) {
            if(this.letrasAdivinhadas.get(i)==letra) {
                return true;
            }
        }
        return false;
    }

    private void atualizarPalavraAdvinhada() {
        this.palavraAdvinhada = "";
        for(int i=0;i<this.palavraEscolhida.length();i++) {
            if(letraFoiAdivinhada(this.palavraEscolhida.charAt(i))) {
                this.palavraAdvinhada += this.palavraEscolhida.charAt(i);
            } else {
                this.palavraAdvinhada += '_';
            }
        }
    }
}
