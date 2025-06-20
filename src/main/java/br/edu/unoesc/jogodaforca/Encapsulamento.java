package br.edu.unoesc.jogodaforca;

import java.util.EnumSet;
import java.util.ArrayList;

public class Encapsulamento {

    // Contrutor

    public Encapsulamento() {}

    // Funções

    public ArrayList<Character> PegarListaDePalavras() {
        ArrayList<Character> lista = new ArrayList<Character>();

        /* Código para pegar data/words.data */

        return lista;
    }

    public boolean existeEstado() {

        /* Código para verificar se existe data/estado.data */

        return false;
    }

    public Save carregarEstado() {
        Save estado = new Save("teste");

        /* Código para carregar estado de data/estado.data */

        return estado;
    }

    public void salvarEstado(Save estado) {

        /* Código para salvar estado em data/estado.data */

    }


}
