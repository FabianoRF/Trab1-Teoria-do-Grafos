package main.com;

import java.util.Scanner;

/**
 * Algumas considerações:
 * 1. Algumas funções utilizadas para testes foram mantidas, uum exemplo é a opção de exibir as estruturas, que exibe todos os dados das 3 estruturas.
 *
 * 2. Dado que no momento que é feita a inserção de uma relação a mesma é de "é seguidor de/ seguido por", no momento da exclusão da mesma interpretamos
 * que seria do mesmo modo, ou seja, a exclusão de uma relação faz com que x deixe de seguir y e vice versa.
 *
 * 3. Não descobrimos como limpar o terminal com alguma função semelhante ao system("cls") kk
 *
 * 4.Dado que este trabalho esta sendo desenvolvido Utilizando a Coleção treeset para a estrutura de lista AVL,
 * não foi possivel implementar os algoritimos de percurso pre-ordem e pós-ordem, apenas o in-ordem,
 * por se tratar de um recurso nativo da linguagem.
 *
 * 5.O arquivo de entrada pode ler relações, basta colocar a qtd de linhas e digitar: nomeX nomeY tempoAmizade
 */

public class Main {

    public static void main(String[] args) {
        int op=1, idade;
        String nome;
        Grafo grafo=new Grafo();
        grafo.inicializarGrafos();

        Scanner scanner=new Scanner(System.in);
        System.out.println("------------- TRABALHO PRÁTICO I SOBRE GRAFOS -------------");
        System.out.println("PROFESSOR: HUGO RESENDE");
        System.out.println("DISCIPLINA: TEORIA DOS GRAFOS");
        System.out.println("ALUNOS: FABIANO REIS & LUCAS GABRIEL");
        System.out.println("-----------------------------------------------------------");


        while(op!=0){
            System.out.println("------------- MENU -------------");
            System.out.println("1 Para cadastrar um usuario \n2 Para ler arquivo de texto"  +
                    "\n3 Para listar seguidores \n4 Para inserir relação \n5 Listar seguidores mais velhos" +
                    "\n6 Para exibir as estruras utilizadas no trabalho" +
                    "\n7 Remover um usuario \n8 Para remover uma relação entre os usuarios" +
                    "\n9 Atualizar relação de amizade \n10 Mostrar os Usuarios \n0 Para Encerrar");
            op=scanner.nextInt();
            System.out.println("--------------------------------------------------------------");
            switch(op){
                case 1:{
                    grafo.cadastraUsuario();
                    break;
                }
                case 2:{
                    grafo.leitor();
                    break;
                }
                case 3:{
                    grafo.exibeUsuarios();
                    System.out.println("Digite o nome do usuario:");
                    nome=scanner.next();
                    grafo.listarSeguidores(nome);
                    break;
                }
                case 4:{
                    grafo.inserirRelacao();
                    break;
                }
                case 5:{
                    grafo.listarSeguidoresVelhos();
                    break;
                }
                case 6:{
                    grafo.exibeEstruturas();
                    break;
                }
                case 7:{
                    grafo.exibeUsuarios();
                    System.out.println("Digite o nome do usuario:");
                    nome=scanner.next();
                    System.out.println("Digite a idade do usuario:");
                    idade=scanner.nextInt();
                    grafo.removerUsuario(nome, idade);
                    break;
                } case 8:{
                    grafo.exibeUsuarios();
                    grafo.removerRelacao("", "", true);
                    break;
                }
                case 9:{
                    grafo.atualizarRelacao("", "", 0, true);
                    break;
                }
                case 10:{
                    grafo.exibeUsuarios();
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
