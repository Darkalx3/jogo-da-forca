package br.edu.unoesc.jogodaforca;

import java.io.*;
import java.nio.file.*;
import java.util.EnumSet;
import java.util.ArrayList;
import java.util.List;

public class Encapsulamento {

    // Variáveis

    private Path dir;
    private Path words;
    private Path readme;
    private Path state;

    // Contrutor

    public Encapsulamento() {
        this.dir = Paths.get("data");
        this.words = Paths.get("data/words.txt");
        this.readme = Paths.get("data/readme.txt");
        this.state = Paths.get("data/state.data");
    }

    // Funções

    public List<String> PegarListaDePalavras() {
        List<String> lista = null;

        try {

            // Verifica se diretórios e arquivos existem, senão, cria eles em branco
            if(!Files.exists(dir)) {
                Files.createDirectory(dir);
            }
            if(!Files.exists(words)) {
                Files.createFile(words);
            }
            if(!Files.exists(readme)) {
                Files.createFile(readme);
            }

            // Lê todas as linhas do arquivo
            if(Files.isReadable(words)) {
                lista = Files.readAllLines(words);
            }

            // Imprime uma mensagem no readme.txt
            if(Files.isWritable(readme)) {
                Files.write(readme,"Coloque as palavra a serem usadas, uma em cada linha em words.txt ".getBytes());
            }

        } catch (IOException e) {
            System.out.print(String.format("Erro: %s\n", e.getMessage()));
        }

        return lista;
    }

    public boolean existeEstado() {
        return Files.exists(state);
    }

    public Save carregarEstado() {
        Save estado = null;

        try {
            if(Files.isReadable(state)) {
                ObjectInputStream obj = new ObjectInputStream(new FileInputStream(state.toFile()));
                estado = (Save) obj.readObject();
                obj.close();
            }
        } catch (InvalidClassException y) {
            System.out.print("Erro: versão da classe é incompatível.");
        } catch (ClassNotFoundException | IOException e) {
            System.out.print(String.format("Erro: %s\n", e.getMessage()));
        }

        return estado;
    }

    public void salvarEstado(Save estado) {
        try {
            if(!Files.exists(dir)) {
                Files.createDirectory(dir);
            }
            if(!Files.exists(state)) {
                Files.createFile(state);
            }

            ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(state.toFile()));
            obj.writeObject(estado);
            obj.close();

        } catch (IOException e) {
            System.out.print(String.format("Erro: %s\n", e.getMessage()));
        }

    }

    public void zerarEstado() {
        try {
            Files.deleteIfExists(state);
        } catch (IOException e) {
            System.out.print(String.format("Erro: %s\n", e.getMessage()));
        }
    }

}
