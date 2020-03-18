package main.com;

public class Main {

    public static void main(String[] args) {
        Grafo grafo=new Grafo();
        grafo.inicializarGrafos();
        grafo.insereUsuariosTeste();

        Usuario usuario=new Usuario("Fabiano", 10);
        Usuario usuario2=new Usuario("Maria", 10);

        grafo.inserirRelacao();

    }
}
