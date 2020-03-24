package main.com;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int op=1;
        String nome;
        Grafo grafo=new Grafo();
        grafo.inicializarGrafos();
        grafo.insereUsuariosTeste();

        Usuario usuario=new Usuario("Fabiano", 10);
        Usuario usuario2=new Usuario("Maria", 10);

        Scanner scanner=new Scanner(System.in);


        while(op!=0){
            System.out.println("1 para cadastrar, 2 para inserir relação, 3 para listar seguidores, 0 para sair:");
            op=scanner.nextInt();
            switch(op){
                case 1:{
                    break;
                }
                case 2:{
                    grafo.inserirRelacao();
                    break;
                }
                case 3:{
                    System.out.println("Digite o nome do usuario:");
                    nome=scanner.next();
                    grafo.listarSeguidores(nome);
                    break;
                }
            }
        }




    }
}
