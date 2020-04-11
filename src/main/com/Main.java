package main.com;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int op=1;
        String nome;
        Grafo grafo=new Grafo();
        grafo.inicializarGrafos();
        grafo.leitor();

        Scanner scanner=new Scanner(System.in);


        while(op!=0){
            System.out.println("1 para cadastrar, 2 para inserir relação, 3 para listar seguidores, 4 para ler arquivo,\n 5 listar velhos, 0 para sair:");
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
                case 4:{
                    grafo.leitor();
                    break;
                }
                case 5:{
                    grafo.listarSeguidoresVelhos();
                    break;
                }
                case 6:{
                    grafo.exibeUsuarios();
                    break;
                }
                case 7:{
                    System.out.println("Digite o nome do usuario:");
                    nome=scanner.next();
                    grafo.removerUsuario(nome);
                    break;
                }
                default:{
                    System.out.println("Opção inválida! Digite novamente...");
                    break;
                }
            }
        }
    }
}
