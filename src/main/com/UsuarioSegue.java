package main.com;

//classe que armazena quanto tempo um determinado usuario segue outro na rede social

public class UsuarioSegue implements Comparable<UsuarioSegue>{//interface que diz para a treeset o que sera comparado
    private int indiceUsuario;
    private int tempo;

    public UsuarioSegue() {
    }

    public UsuarioSegue(int indiceUsuario, int tempo) {
        this.indiceUsuario = indiceUsuario;
        this.tempo = tempo;
    }

    public int getIndiceUsuario() {
        return indiceUsuario;
    }

    public void setIndiceUsuario(int indiceUsuario) {
        this.indiceUsuario = indiceUsuario;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    @Override
    public int compareTo(UsuarioSegue o) {
        return this.indiceUsuario - o.indiceUsuario;//exibe in-ordem
    }
}
