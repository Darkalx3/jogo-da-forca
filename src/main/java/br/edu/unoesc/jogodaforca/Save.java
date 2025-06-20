package br.edu.unoesc.jogodaforca;

import java.util.ArrayList;

public class Save {

    // Variáveis

    private ArrayList<Character> letrasAdivinhadas;
    private ArrayList<Character> letrasErradas;
    private String palavra;
    private int quantTentativas;

    // Construtores

    public Save(String palavra) {
        /* quantTentativas pode ser por padrão 6 */
        this.quantTentativas = 6;
        this.palavra = palavra;
        letrasAdivinhadas = new ArrayList<>();
        letrasErradas = new ArrayList<>();
    }

    public Save(String palavra, int quantTentativas) {
        this.quantTentativas = quantTentativas;
        this.palavra = palavra;
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
    }

    public void acrescentarLetrasErradas(char letra) {
        letrasErradas.add(letra);
    }

    // Métodos Get

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

}
