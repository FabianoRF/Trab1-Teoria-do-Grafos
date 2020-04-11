package main.com;

//classe que contem as tres estruturas e os metodos principais do trabalho

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class Grafo {

    public ArrayList<Usuario> listaUsuarios;//lista que armazena todos os usuarioss
    public int[][] matrizDP;
    public ArrayList<ArrayList<UsuarioSegue>> listaAD;
    public ArrayList<TreeSet<UsuarioSegue>> listaAVL;



    //metodos pedidos no problema
    public void inicializarGrafos() {
        //Lista geral
        this.listaUsuarios = new ArrayList<Usuario>();
        //Matriz
        this.matrizDP = new int[50][50];
        //Lista
        this.listaAD = new ArrayList<>();
        //AVL
        this.listaAVL = new ArrayList<>();
    }

    public void inserirUsuario(Usuario usuario) {//pronto
        int i;
        boolean b = true;

        //Verifica se ha o usuario na lista principal, se nao existir emite uma mensagem de erro
        for (i = 0; i < this.listaUsuarios.size(); i++) {
            if (usuario.getNome().equals(this.listaUsuarios.get(i).getNome())) {
                b = false;
                break;
            }
        }

        if (b) { // Se chegar até esse ponto significa que esse usuario não existe na lista ainda.
            this.listaUsuarios.add(usuario);//Insere o usuario na lista
            this.listaAD.add(new ArrayList<>());//Cria uma lista dentro da lista AD
            this.listaAVL.add(new TreeSet<>());//Cria uma arvore dentro da lista AVL
        } else {
            System.out.println("ERRO! O usuario já existe na lista!");
        }
    }

    public void inserirRelacao() {//pronto
        Scanner scanner = new Scanner(System.in);
        int tempoAmizade;
        int indice, indice2;//representam os indices Usuarios da relação
        boolean b = true;
        UsuarioSegue usuarioSegue = new UsuarioSegue();
        UsuarioSegue usuarioAuxAvl= new UsuarioSegue();

        System.out.println("Digite o usuario 1:");
        String nome = scanner.next();
        System.out.println("Digite o usuario 2:");
        String nome2 = scanner.next();
        System.out.println("Informe o tempo de amizade:");
        tempoAmizade = scanner.nextInt();

        indice = this.retornaIndice(nome);
        indice2 = this.retornaIndice(nome2);//pega o indice do uduario 2 para recuperar sua idade e inserir na classe UsuarioSegue

        usuarioSegue.setIndiceUsuario(indice2);
        usuarioSegue.setTempo(tempoAmizade);

        if (indice >= 0) {//ou seja se o indice existe
            for (int i = 0; i < this.listaAD.get(indice).size(); i++) {
                if (this.listaAD.get(indice).get(i).getIndiceUsuario() == this.retornaIndice(nome2)) {//Comparando se já existe uma relação
                    b = true;
                    System.out.println("Essa relação já existe, deseja atualiza-la? (s / n)");
                    String resposta = scanner.next();

                    if (resposta.equals("s")) {
                        this.listaAVL.get(indice).remove(usuarioSegue);//remove da avl antes de atualizar para depois add dnv o atualizado
                        System.out.println("Informe o novo tempo de amizade: ");
                        usuarioSegue.setTempo(scanner.nextInt());//le o novo tempo de amizade

                        b = false;
                        //Agora vamos inserir nas listas
                        this.listaAD.get(indice).get(i).setTempo(usuarioSegue.getTempo());//atualiza na lista de adjacencias
                        this.matrizDP[this.retornaIndice(nome)][this.retornaIndice(nome2)] = usuarioSegue.getTempo();//atualiza o tempo na matriz de pesos
                        this.listaAVL.get(indice).add(usuarioSegue);//adiciona agora depois de atualizado

                    } else {
                        System.out.println("Relação não alterada!");
                    }
                    break;
                }
            }
            if (b) {//caso nao exista uma relação b se mantem verdadeiro
                this.listaAD.get(indice).add(usuarioSegue);//add na lista de adjacencias

                this.listaAVL.get(indice).add(usuarioSegue);//add na lista de AVL
                this.matrizDP[this.retornaIndice(nome)][this.retornaIndice(nome2)] = usuarioSegue.getTempo();//add o tempo na matriz de pesos
                System.out.println("\nteste se inseriu nas estruturas");
            }
        } else {
            System.out.println("Ocorreu algum erro, provavelmente o usuario nao esta na lista!");
        }
    }

    //versão da inserir relação utilizada pelo leitor de arquivo que recebe como parametro um vetor de string com usuario x, y e tempo de amizade(obs: não atualiza se ja houver)
    public void inserirRelacao(String[] auxRelacao) {//pronto
        int indice, indice2;//representam os indices Usuarios da relação
        boolean b = true;

        indice = this.retornaIndice(auxRelacao[0]);
        indice2 = this.retornaIndice(auxRelacao[1]);//pega o indice do uduario 2 para recuperar sua idade e inserir na classe UsuarioSegue
        UsuarioSegue usuarioSegue = new UsuarioSegue();
        usuarioSegue.setIndiceUsuario(indice2);
        usuarioSegue.setTempo(Integer.parseInt(auxRelacao[2]));

        if (indice >= 0) {//ou seja se o indice existe
            this.listaAD.get(indice).add(usuarioSegue);//add na lista de adjacencias
            this.listaAVL.get(indice).add(usuarioSegue);//add na lista de AVL
            this.matrizDP[this.retornaIndice(auxRelacao[0])][this.retornaIndice(auxRelacao[1])] = usuarioSegue.getTempo();//add o tempo na matriz de pesos
            //System.out.println("\nteste se inseriu nas estruturas");

        } else {
            System.out.println("Ocorreu algum erro, provavelmente o usuario nao esta na lista!");
        }
    }


    /**
     * Dado que este trabalho esta sendo desenvolvido Utilizando a Coleção treeset para a estrutura de lista AVL,
     * não foi possivel implementar os algoritimos de percurso pre-ordem e pós-ordem, apenas o in-ordem,
     * por se tratar de um recurso nativo da linguagem.
     */

    public void listarSeguidores(String nome) {
        int indice, i, idade, j;
        String auxNome;
        Usuario usuarioAux;

        indice=this.retornaIndice(nome);
        //exibindo da listaAD e da Matriz DP

        //é seguido por:
        System.out.println("Seguido por:");
        for(i=0;i<listaAD.size();i++){
            auxNome=listaUsuarios.get( i ).getNome();//pega o nome do usuario que esta sendo iterado
            idade=listaUsuarios.get( i ).getIdade();//pega a idade do usuario que esta sendo iterado
            for(j=0;j<listaAD.get(i).size();j++){


                if(listaAD.get(i).get(j).getIndiceUsuario() == indice){//verifica se o cod do usuario buscado é seguido por outro(se sim, exibe)
                    System.out.printf("Nome: %s, idade: %d\n", auxNome, idade);
                }
            }
        }

        //segue:
        if(listaAD.get(indice).size()!=0){//confere se possui seguidores
            System.out.printf("\nO usuario %s, segue os seguintes usuarios: ", nome);
            System.out.println("\n*** Exibindo da Lista de Adjacencias e Matriz de Pesos ***\n");
            for(i=0;i<listaAD.get(indice).size();i++){
                //pega o nome e a idade dos usuarios que seguem o usuario pedido
                auxNome=listaUsuarios.get(this.listaAD.get(indice).get(i).getIndiceUsuario()).getNome();
                idade=listaUsuarios.get(this.listaAD.get(indice).get(i).getIndiceUsuario()).getIdade();
                System.out.printf("Nome: %s, idade: %d\n", auxNome, idade);
            }

            //exibição da arvore avl
            System.out.println("\n*** Exibindo da Lista de AVL in-ordem ***\n");
            Iterator<UsuarioSegue> iterator=listaAVL.get(indice).iterator();//cria um iterator que recebe a AVL do usuario em questao

            while(iterator.hasNext()){
                indice=iterator.next().getIndiceUsuario();//pega o indice do usuario dentro da AVL
                usuarioAux=this.listaUsuarios.get(indice);//pega o usuario que possui aquele indice para depois exibi-lo
                System.out.printf("Nome: %s, idade: %d\n", usuarioAux.getNome(), usuarioAux.getIdade());
            }

        }else{
            System.out.println("Este usuario não segue ninguém!");
        }
    }

    public void listarSeguidoresVelhos(){
        int i,j,k;
        int idadeSeguido, idadeSegue, idadeAux, tempoAmizade;
        String nomeSeguido, nomeAux, nomeSegue;
        boolean b;//utilizado para verificar se o usuario possui ou nao seguidores

        for(i=0;i<listaAD.size();i++){
            b=true;//adimite que não pssui seguidores mais velhos
            idadeSeguido=listaUsuarios.get(i).getIdade();//pega a idade do usuario que esta sendo comparada agora
            nomeSeguido=listaUsuarios.get(i).getNome();//pega o nome do usuario que esta sendo comparada agora
            System.out.printf("\nO usuario %s: \n", nomeSeguido);

            for(j=0;j<listaAD.size();j++){

                if(i!=j){//não verifica os seguidores do mesmo usuario
                    idadeSegue=listaUsuarios.get(j).getIdade();//armazena a idade do usuario que sera comparado
                    nomeSegue=listaUsuarios.get(j).getNome();//armazena o nome do usuario que sera comparado

                    if(idadeSeguido<idadeSegue){//caso o usuario Y seja mais velho que o X, procura se o Y segue X para exibi-lo

                        for(k=0;k<listaAD.get(j).size();k++){
                            nomeAux=listaUsuarios.get(listaAD.get(j).get(k).getIndiceUsuario()).getNome();//armazena o nome do usuario que sera comparado
                            idadeAux=listaUsuarios.get(listaAD.get(j).get(k).getIndiceUsuario()).getIdade();//armazena o nome do usuario que sera comparado
                            tempoAmizade=listaAD.get(j).get(k).getTempo();//armazena o tempo de amizade

                            if(nomeAux.equals(nomeSeguido)){//se for verdade aqui pode se dizer que um usuario mais velho segue o usuario em questão, portanto exibe
                                System.out.printf("É seguido por %s de idade %d por %d meses!\n", nomeSegue, idadeAux, tempoAmizade);//exibe o usuario que possui seguidro mais velho, o nome do seguidor mais velho e seu quantitativo
                                b=false;//admite que ele possui seguidor mais velho
                            }
                        }
                    }
                }
            }
            if(b){
                System.out.println("Não possui nenhum seguidor mais velho!\n");
            }
        }


    }


    public void atualizarRelacao() {

    }

    public void removerUsuario(String nome) {
        int indice=this.retornaIndice(nome);

        if(this.verificaExistencia(nome)){
            //excluindo das estruturas
            this.listaUsuarios.remove(indice);//remove lista principal
            this.listaAD.remove(indice);//remove lista ad junto com os seguidores
            this.listaAVL.remove(indice);//remove listaAVL junto com a arvore de seguidores

            for(int i=0;i<50;i++){
                for(int j=0;j<50;j++){
                    if(i==indice){
                        matrizDP[i][indice]=-1;//Como a matriz não é dinamica a estrategia de utilizar o -1 é para diferenciar
                    }
                }
            }

        }else{
            System.out.println("\nERRO! O usuario não existe!\n");
        }

    }

    public void removerRelacao() {

    }

    public int retornaIndice(String nome) {//Metodo que retorna o indice do uisuario OBS: caso não ache ele retorna -1
        int i, indice = -1;
        for (i = 0; i < this.listaUsuarios.size(); i++) {
            if (nome.equals(this.listaUsuarios.get(i).getNome())) {
                indice = i;
            }
        }
        return indice;
    }

    public boolean verificaExistencia(String nome){//Metodo que retorna se o o nome está na lista

        for(int i=0;i<listaUsuarios.size();i++){
            if(listaUsuarios.get(i).getNome().equals(nome)){
                return true;
            }
        }
        return false;
    }

    //leitor de txt para ler usuarios e  caso necessario, as relações
    public void leitor(){
        String nomeArq = "Entrada.txt";
        int n;
        try {
            FileReader arq = new FileReader(nomeArq);
            BufferedReader lerArq = new BufferedReader(arq);

            String linha= lerArq.readLine();//Le a primeira linha para saber o numero de usuarios do arquivo
            n=Integer.parseInt(linha);//converte para inteiro

            String[] nomeIdade;//vetor de string que sera utilizado para o split que separará o nome da idade
            String nome;
            int idade;
            //INICIO DA LEITURA DOS USUARIOS
            for (int i=0; i<n ; i++){
                linha=lerArq.readLine();
                nomeIdade=linha.split(" ");//separa o nome da idade
                nome=nomeIdade[0];
                idade=Integer.parseInt(nomeIdade[1]);
                this.inserirUsuario(new Usuario(nome, idade));//insere o usuario na rspectiva linha
            }
            //FIM LEITURA USUARIOS

            //INICIO LEITURA RELAÇÕES
            linha= lerArq.readLine();//Le a primeira linha para saber o numero de relações do arquivo

            if(linha!=(null)){//se esta linha conter algum numero significa que haverá inserção de relações
                n=Integer.parseInt(linha);//converte para inteiro
                String[] auxRelacao;
                for(int i=0;i<n;i++){
                    linha=lerArq.readLine();
                    auxRelacao=linha.split(" ");//faz o split para separar o usuario x, y e o tempo de amizade

                    this.inserirRelacao(auxRelacao);
                }
            }else {
                System.out.println("O arquivo não possui relações");
            }
            //FIM RELAÇÕES
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Erro na leitura do arquivo!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro na leitura do arquivo!");
        }
    }

}
