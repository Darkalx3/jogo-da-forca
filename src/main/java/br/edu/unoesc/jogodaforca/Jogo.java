package br.edu.unoesc.jogodaforca;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Classe principal do jogo da forca.
 * Gerencia o estado do jogo, as tentativas do jogador, e a lógica de adivinhação.
 */
public class Jogo {

    // Flags de controle do estado do jogo
    private boolean jogoIniciado;   // Indica se o jogo foi iniciado
    private boolean jogoFinalizado; // Indica se o jogo foi finalizado
    private boolean jogadorGanhou;  // Indica se o jogador venceu
    private boolean jogadorSaiu;    // Indica se o jogador saiu do jogo antes do fim

    // Objetos auxiliares
    private Save save;              // Guarda o estado atual do jogo (palavra, tentativas, letras)
    private Encapsulamento data;    // Responsável por salvar, carregar e limpar o estado do jogo

    /**
     * Construtor: inicializa o objeto de persistência.
     */
    public Jogo() {
        this.data = new Encapsulamento();
    }

    /**
     * Inicia um novo jogo: escolhe uma palavra aleatória e define flags padrão.
     */
    public void iniciarJogo() {
        save = new Save(aleatorizarPalavra());
        definirEstadoPadrao();
    }

    /**
     * Continua um jogo salvo anteriormente, caso exista estado salvo.
     *      */
    public boolean continuarJogo() {
        if (data.existeEstado()) {
            save = data.carregarEstado();
            if (save == null) {
                return false;
            }
            definirEstadoPadrao();
            return true;
        }
        return false;
    }

    /**
     * Processa a tentativa de adivinhar uma letra.
     * Remove acentos, converte para minúscula, valida e atualiza estado.
     *      *      */
    public boolean adivinharLetra(char letra) {
        letra = removerAcentos(Character.toString(letra)).charAt(0);
        letra = Character.toLowerCase(letra);

        if (existeLetraInvalida(letra)) {
            return false;
        }

        if (isJogoIniciado() && !isJogoFinalizado() && !letraJaEscolhida(letra)) {
            boolean possuiLetra = false;
            String palavra = save.getPalavraEscolhida();

            // Verifica se a letra está na palavra
            for (int i = 0; i < palavra.length(); i++) {
                if (letra == palavra.charAt(i)) {
                    possuiLetra = true;
                    break;
                }
            }

            // Atualiza lista de letras e tentativas
            if (possuiLetra) {
                save.acrescentarLetrasAdivinhadas(letra);
            } else {
                save.acrescentarLetrasErradas(letra);
                save.diminuirQuantTentativas();
            }

            // Verifica vitória, derrota ou fim de jogo
            verificarEstado();
            return true;
        }
        return false;
    }

    /**
     * Tenta adivinhar a palavra completa.
     * Converte e remove acentos antes de comparar.
     *      *      */
    public boolean adivinharPalavra(String palavra) {
        palavra = palavra.toLowerCase();
        palavra = removerAcentos(palavra);

        if (isJogoIniciado() && !isJogoFinalizado()) {
            if (palavra.equals(save.getPalavraEscolhida())) {
                this.jogadorGanhou = true;
            } else {
                save.diminuirQuantTentativas();
            }
            verificarEstado();
            return true;
        }
        return false;
    }

    /**
     * Permite ao jogador sair do jogo, com opção de salvar estado.
     *      *      */
    public boolean sair(boolean salvarJogo) {
        if (isJogoIniciado() && !isJogoFinalizado()) {
            if (salvarJogo) {
                data.salvarEstado(this.save);
            }
            jogoFinalizado = true;
            jogadorSaiu = true;
            return true;
        }
        return false;
    }

    // Métodos de acesso ao estado do jogo

    /**
     * Retorna o objeto Save com o estado do jogo.
     */
    public Save retornarEstado() {
        return this.save;
    }

    public boolean isJogoIniciado() { return jogoIniciado; }
    public boolean isJogoFinalizado() { return jogoFinalizado; }
    public boolean isJogadorGanhou() { return jogadorGanhou; }
    public boolean isJogadorSaiu() { return jogadorSaiu; }

    // Métodos privados auxiliares

    /**
     * Verifica se a letra já foi tentada, seja certa ou errada.
     *      *      */
    private boolean letraJaEscolhida(char letra) {
        ArrayList<Character> adv = save.getLetrasAdivinhadas();
        ArrayList<Character> err = save.getLetrasErradas();
        return adv.contains(letra) || err.contains(letra);
    }

    /**
     * Seleciona aleatoriamente uma palavra da lista ou usa palavras padrão.
     *      */
    private String aleatorizarPalavra() {
        List<String> lista = data.PegarListaDePalavras();
        if (lista == null || lista.isEmpty()) {
            lista = List.of("perseverar","lua","liberdade","computador","internet","forca","jogo","palavra","código");
        }
        int indice = (int) (Math.random() * lista.size());
        String palavra = lista.get(indice).toLowerCase();
        return removerAcentos(palavra);
    }

    /**
     * Inicializa as flags padrão do jogo.
     */
    private void definirEstadoPadrao() {
        this.jogoIniciado = true;
        this.jogoFinalizado = false;
        this.jogadorSaiu = false;
        this.jogadorGanhou = false;
    }

    /**
     * Verifica se o jogo deve ser finalizado por vitória ou esgotamento de tentativas.
     */
    private void verificarEstado() {
        if (isJogadorGanhou() || save.getQuantTentativas() == 0) {
            this.jogoFinalizado = true;
            data.zerarEstado();
        } else if (save.getPalavraAdvinhada().equals(save.getPalavraEscolhida())) {
            this.jogoFinalizado = true;
            this.jogadorGanhou = true;
            data.zerarEstado();
        }
    }

    /**
     * Verifica se o caractere informado não é uma letra válida (a-z ou '-').
     *      *      */
    private static boolean existeLetraInvalida(char letra) {
        return !( (letra >= 'a' && letra <= 'z') || letra == '-');
    }

    /**
     * Remove acentos de uma string utilizando Normalizer e regex.
     *      *      */
    private static String removerAcentos(String texto) {
        if (texto == null) {
            return null;
        }
        String textoNormalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        Pattern padrao = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return padrao.matcher(textoNormalizado).replaceAll("");
    }
}
