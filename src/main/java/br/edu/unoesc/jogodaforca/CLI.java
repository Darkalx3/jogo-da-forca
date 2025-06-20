package br.edu.unoesc.jogodaforca;

import java.util.Scanner;

public class CLI {

    // Variáveis

    Scanner scan;

    // Construtor

    public CLI() {
        scan = new Scanner(System.in);
    }

    // Métodos

    public int renderizarMenu() { // retorna a opção escolhida no menu

        // Fazer um try catch aqui
        System.out.print("Menu Provisorio: ");// print menu (precisa fazer o menu)
        return scan.nextInt();
    }

    /* adicionem outras funções conforme a necessidade ...*/

}
