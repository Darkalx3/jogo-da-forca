package br.edu.unoesc.jogodaforca;

import java.io.Serializable;
import java.util.ArrayList;

public class Save implements Serializable {

    // Variáveis

    private ArrayList<Character> letrasAdivinhadas;
    private ArrayList<Character> letrasErradas;
    private String palavraAdvinhada;
    private String palavra;
    private int quantTentativas;

    // Construtores

    public Save(String palavra) {
        /* quantTentativas pode ser por padrão 6 */
        this.quantTentativas = 6;
        this.palavra = palavra;
        this.palavraAdvinhada = "";
        letrasAdivinhadas = new ArrayList<>();
        letrasErradas = new ArrayList<>();
    }

    public Save(String palavra, int quantTentativas) {
        this.quantTentativas = quantTentativas;
        this.palavra = palavra;
        this.palavraAdvinhada = "";
        letrasAdivinhadas = new ArrayList<>();
        letrasErradas = new ArrayList<>();
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

        this.palavraAdvinhada = "";
        for(int i=0;i<this.palavra.length();i++) {
            if(letraFoiAdivinhada(this.palavra.charAt(i))) {
                this.palavraAdvinhada += this.palavra.charAt(i);
            } else {
                this.palavraAdvinhada += ' ';
            }
        }
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

    public String getPalavra() {
        return palavra;
    }

    public int getQuantTentativas() {
        return quantTentativas;
    }

    public String getPalavraAdvinhada() {
        return palavraAdvinhada;
    }

    // Métodos Privados

    private boolean letraFoiAdivinhada(char letra) {
        for(int i=0;i<this.letrasAdivinhadas.size();i++) {
            if(this.letrasAdivinhadas.get(i)==letra) {
                return true;
            }
        }
        return false;
    }

}
