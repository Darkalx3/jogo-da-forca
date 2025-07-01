package br.edu.unoesc.jogodaforca;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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

    public void iniciarJogo(String nomeJogador) {
        save = new Save(aleatorizarPalavra(), nomeJogador);
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
        letra = removerAcentos(Character.toString(letra)).charAt(0); // remove acentos da palavra
        letra = Character.toLowerCase(letra); // converte a letra para minúscula

        if(existeLetraInvalida(letra)) { // verifica se o que foi digitado não é uma letra
            return false;
        }

        if(isJogoIniciado() && !isJogoFinalizado() && !letraJaEscolhida(letra)) {

            boolean possuiLetra = false;
            String palavra = save.getPalavraEscolhida();

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
        palavra = removerAcentos(palavra); // remove acentos da palavra 

        if(isJogoIniciado() && !isJogoFinalizado()) {

            if(palavra.equals(save.getPalavraEscolhida())) {
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

        if (lista == null || lista.isEmpty()) {
            lista = List.of("perseverar","lua", "liberdade", "computador", "internet", "forca", "jogo", "palavra", "código");
        }

        int indice = (int) (Math.random() * lista.size());
        String palavra = lista.get(indice).toLowerCase();
        palavra = removerAcentos(palavra); // remove acentos da palavra

        return palavra;
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
            data.zerarEstado();
        } else if(save.getPalavraAdvinhada().equals(save.getPalavraEscolhida())){
            this.jogoFinalizado = true;
            this.jogadorGanhou = true;
            data.zerarEstado();
        }
    }

    private static boolean existeLetraInvalida(char letra) {
        if(letra == 'a' || letra == 'b' || letra == 'c' || letra == 'd' || letra == 'e' ||
           letra == 'f' || letra == 'g' || letra == 'h' || letra == 'i' || letra == 'j' ||
           letra == 'k' || letra == 'l' || letra == 'm' || letra == 'n' || letra == 'o' ||
           letra == 'p' || letra == 'q' || letra == 'r' || letra == 's' || letra == 't' ||
           letra == 'u' || letra == 'v' || letra == 'w' || letra == 'x' || letra == 'y' ||
           letra == 'z' || letra == '-') {
            return false;
        }
        return true;
    }

    private static String removerAcentos(String texto) {
        if (texto == null) {
            return null;
        }
        // 1. Normaliza o texto para a forma de decomposição canônica (NFD)
        String textoNormalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        
        // 2. Define o padrão de regex para encontrar diacríticos (acentos)
        Pattern padrao = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        
        // 3. Remove os acentos substituindo-os por uma string vazia
        return padrao.matcher(textoNormalizado).replaceAll("");
    }
}
