package com.joaovictor.gerenciador;

import java.util.Scanner;
import java.util.ArrayList;

class Tarefa {
    // molde para guardar o texto e se estiver pronto ou não
    String descricao;
    boolean concluida;

    public Tarefa(String descricao) {
        this.descricao = descricao;
        this.concluida = false; // qual quer tarefa nova começa pendente
    }
}

class GerenciadorDeTarefas {

    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<Tarefa> historico = new ArrayList<>();
    public static int escolha;
    public static String adicionarEscolha;

    static void main(String[] args){
        // rodar o menu até digitar 5 pra sair
        do {
            listar();
        } while (escolha != 5);
    }

    public static void listar(){
        try {
            System.out.print("""
                   
                   ======= MENU =======
                   
                   Escolha as opções a baixo:
                   1 - Visualizar Tarefas
                   2 - Adicionar Tarefas
                   3 - Tarefas Finalizadas
                   4 - Remover Tarefas
                   5 - Sair
                   
                   Digite sua opção:""");

            escolha = sc.nextInt();
            sc.nextLine(); // limpando o "buffer" para próximo nextLine não pular

            switch (escolha) {
                case 1 -> escolha1();
                case 2 -> escolha2();
                case 3 -> escolha3();
                case 4 -> escolha4();
                case 5 -> System.out.print("Saindo...");
                default -> System.out.println("Valor errado!");
            }
        }
        catch (Exception e) {
            // se digitar letra em vez de número cai aqui
            System.out.println("Valor invalido!");
            sc.nextLine(); // limpa o erro para não travar o “Loop”
        }
    }

    public static void escolha1(){
        if(historico.isEmpty()){
            System.out.println("\nNenhum Tarefa Encontrada!\n");
        }else {
            System.out.println("\n ---- Tarefas ----\n");
            for (int i = 0; i < historico.size(); i++) {
                Tarefa t = historico.get(i);
                // mostrar o check visual
                String status = t.concluida ? "[OK]" : "[ ]";

                // usei o ".descricao" senão imprime o endereço da memória
                System.out.println("Tarefa " + (i+1) + ": " + t.descricao + " | Status: " + status);
            }
        }
    }

    public static void escolha2() {
        do {
            System.out.println("\nQual Tarefa deseja adicionar? ");
            System.out.print("Digite: ");
            String descricao = sc.nextLine();

            // criando o objeto tarefa igual o digitado
            Tarefa tarefa = new Tarefa(descricao);
            historico.add(tarefa);

            System.out.println("\nTarefa adicionada com sucesso!\n");
            System.out.println("Deseja adicionar mais alguma Tarefa? (S/N)");
            adicionarOp();
        } while (adicionarEscolha.equals("S"));
    }

    public static void escolha3(){
        if(historico.isEmpty()){
            System.out.println("\nLista vazia!\n");
        } else {
            do {
                escolha1(); // mostra a lista para saber qual número escolher

                System.out.print("\nQual tarefa deseja usar? \n");
                System.out.print("Digite: ");
                int escolherTarefa = sc.nextInt();
                sc.nextLine();
                int concluirTarefa = escolherTarefa - 1; // ajuste do índice 0

                if (concluirTarefa >= 0 && concluirTarefa < historico.size()) {
                    Tarefa t = historico.get(concluirTarefa);
                    System.out.print("\nVocê escolheu a Tarefa: " + (concluirTarefa + 1) + "\n");
                    System.out.print("Deseja marcar essa tarefa como concluída? (S/N)\n");
                    System.out.print("Digite: ");

                    // joga pra maiúsculo pra aceitar 's' ou 'S'
                    String cTarefa = sc.nextLine().toUpperCase();

                    if (cTarefa.equals("S")) {
                        t.concluida = true;
                        System.out.println("\nTarefa concluída adicionada com sucesso!\n");
                        System.out.println("Deseja adicionar mais alguma Tarefa como concluída? (S/N)\n");
                        adicionarOp();

                    }else {
                        t.concluida = false;
                        System.out.println("Tarefa continua a espera...");
                    }

                }else {
                    System.out.println("Valor invalido!");
                }
            }while (adicionarEscolha.equals("S"));
        }
    }

    public static void escolha4(){
        if (historico.isEmpty()) {
            System.out.println("\nNenhum Tarefa para excluir Encontrada!\n");
        } else {
            do {
                escolha1();
                System.out.print("\nQual tarefa deseja excluir? ");
                System.out.print("Digite: ");

                int escolherTarefa = sc.nextInt();
                sc.nextLine(); // limpeza de buffer
                int rTarefa = escolherTarefa - 1;

                if (rTarefa >= 0 && rTarefa < historico.size()) {
                    historico.remove(rTarefa);
                    System.out.println("\nTarefa removida com sucesso!\n");
                    System.out.println("Deseja remover mais alguma Tarefa? (S/N)");
                    adicionarOp();
                } else {
                    System.out.println("Valor invalido!");
                }
            }while (adicionarEscolha.equals("S"));
        }
    }
    public static void adicionarOp(){

        System.out.print("Digite: ");
        adicionarEscolha = sc.nextLine().toUpperCase();

    }
}