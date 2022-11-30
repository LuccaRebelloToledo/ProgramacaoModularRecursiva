package br.edu.univas;

import java.util.Scanner;

public class StartApp {

    public static void main(String[] args) {
        iniciarDicionario();
    }

    public static void iniciarDicionario() {
        Scanner scanner = new Scanner(System.in);
        int escolha;
        String[] dicionarioIngles = new String[3];
        String[] dicionarioPortugues = new String[3];

        do {
            printMenu();
            escolha = scanner.nextInt();
            if(escolha == 1) {
                cadastrarDicionarios(dicionarioIngles, dicionarioPortugues);
            } else if(escolha == 2) {
                editarDicionarios(dicionarioIngles, dicionarioPortugues);
            } else if(escolha == 3) {
                excluirDicionarios(dicionarioIngles, dicionarioPortugues);
            } else if(escolha == 4) {
                consultarDicionarios(dicionarioIngles, dicionarioPortugues);
            } else if(escolha == 0) {
                System.out.println("Bye Bye");
            } else {
                System.out.println("Opção Inválida!");
                System.out.println();
            }
        } while (escolha != 0);
        scanner.close();
    }

    public static void printMenu() {
        System.out.print("""
                1 - Cadastrar Dicionários
                2 - Editar Dicionário
                3 - Excluir Dicionário
                4 - Consultar Dicionário
                0 - Sair
                Escolha uma opção:\s""");
    }
    public static void cadastrarDicionarios(String[] dicionarioIngles, String[] dicionarioPortugues) {
        Scanner scanner = new Scanner(System.in);
            int index = verificaIndexNulo(dicionarioIngles);
            if(index != -1) {
                cadastrarDicionarioIngles(index, dicionarioIngles, dicionarioPortugues);
                index = verificaIndexNulo(dicionarioIngles);
                if(index != -1) {
                    System.out.print("Deseja continuar os cadastros? (S/N) ");
                    String continuar = scanner.nextLine();
                    if(continuar.equalsIgnoreCase("S")) {
                        cadastrarDicionarios(dicionarioIngles, dicionarioPortugues);
                    } else {
                        System.out.println();
                    }
                } else {
                    System.out.println("O dicionário está completo!");
                    System.out.println();
                }
            } else {
                System.out.println("O dicionário está completo!");
                System.out.println();
            }
    }

    public static void cadastrarDicionarioIngles(int index, String[] dicionarioIngles, String[] dicionarioPortugues) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Por favor, digite a palavra em inglês: ");
        String palavra = scanner.nextLine();
        if(verificaDuplicidade(dicionarioIngles, palavra)) {
            System.out.println();
            System.out.println("Está palavra já está cadastrada em seu dicionário!");
            corrigirDicionarioIngles(index, dicionarioIngles, dicionarioPortugues);
        } else {
            dicionarioIngles[index] = palavra.trim();
            cadastrarDicionarioPortugues(index, dicionarioPortugues, palavra);
        }
    }

    public static void corrigirDicionarioIngles(int index, String[] dicionarioIngles, String[] dicionarioPortugues) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Por favor, digite a palavra em inglês novamente: ");
        String palavra = scanner.nextLine();
        if(verificaDuplicidade(dicionarioIngles, palavra)) {
            System.out.println();
            System.out.println("Está palavra já está cadastrada em seu dicionário!");
            corrigirDicionarioIngles(index, dicionarioIngles, dicionarioPortugues);
        } else {
            dicionarioIngles[index] = palavra.trim();
            cadastrarDicionarioPortugues(index,dicionarioPortugues, palavra);
        }
    }


    public static void cadastrarDicionarioPortugues(int index, String[] dicionarioPortugues, String palavra) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Por favor, digite o significado da palavra '" + palavra.trim() + "': ");
        String significadoDaPalavra = scanner.nextLine();
        dicionarioPortugues[index] = significadoDaPalavra.trim();
    }

    public static void editarDicionarios(String[] dicionarioIngles, String[] dicionarioPortugues) {
        if(verificaDicionario(dicionarioIngles)) {
            Scanner scanner = new Scanner(System.in);
            imprimiDicionario(dicionarioIngles);
            System.out.print("Qual palavra deseja editar? ");
            String palavra = scanner.nextLine();
            int index = retornaIndex(dicionarioIngles, palavra);
            if(index != -1) {
                alterarPalavra(index, dicionarioIngles, dicionarioPortugues, palavra);
            } else {
                System.out.println("Palavra não encontrada!");
                System.out.println();
            }
        } else {
            System.out.println("É preciso preencher ao menos uma palavra no dicionário!");
            System.out.println();
        }
    }

    public static void excluirDicionarios(String[] dicionarioIngles, String[] dicionarioPortugues) {
        if(verificaDicionario(dicionarioIngles)) {
            Scanner scanner = new Scanner(System.in);
            imprimiDicionario(dicionarioIngles);
            System.out.print("Qual palavra deseja excluir? ");
            String palavra = scanner.nextLine();
            int index = retornaIndex(dicionarioIngles, palavra);
            if(index != -1) {
                excluirPalavra(index, dicionarioIngles, dicionarioPortugues);
                System.out.println("A palavra " + palavra + " foi excluída!");
                System.out.println();
            } else {
                System.out.println("Palavra não encontrada!");
                System.out.println();
            }
        } else {
            System.out.println("É preciso preencher ao menos uma palavra no dicionário!");
            System.out.println();
        }
    }

    public static void consultarDicionarios(String[] dicionarioIngles, String[] dicionarioPortugues) {
        if(verificaDicionario(dicionarioIngles)) {
            Scanner scanner = new Scanner(System.in);
            imprimiDicionario(dicionarioIngles);
            System.out.print("Qual palavra deseja saber o significado? ");
            String palavra = scanner.nextLine();
            int index = retornaIndex(dicionarioIngles, palavra);
            if(index != -1) {
                consultarSignificado(index, dicionarioPortugues, palavra);
            } else {
                System.out.println("Palavra não encontrada!");
                System.out.println();
            }
        } else {
            System.out.println("É preciso preencher ao menos uma palavra no dicionário!");
            System.out.println();
        }
    }

    public static void consultarSignificado(int index, String[] dicionarioPortugues, String palavra) {
        System.out.println("O significado da palavra " + palavra + " é: " + dicionarioPortugues[index]);
        System.out.println();
    }

    public static void imprimiDicionario(String[] dicionarioIngles) {
        int length = retornaQuantidadeCadastradas(dicionarioIngles);
        if(length == dicionarioIngles.length){
            length -= 1;
            for(int i = 0; i <= length; i++) {
                if(dicionarioIngles[i] != null && i < length) System.out.print(dicionarioIngles[i] + " - ");
                else if(dicionarioIngles[i] != null) {
                    System.out.println(dicionarioIngles[i]);
                    System.out.println();
                }
            }
        } else {
            for(int i = 0; i <= length; i++) {
                if(dicionarioIngles[i] != null && i < length - 1) System.out.print(dicionarioIngles[i] + " - ");
                else if(dicionarioIngles[i] != null) {
                    System.out.println(dicionarioIngles[i]);
                    System.out.println();
                }
            }
        }

    }

    public static void alterarPalavra(int index, String[] dicionarioIngles, String[] dicionarioPortugues, String palavra) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Qual será a palavra que substituirá '" + palavra + "'? ");
        String novaPalavra = scanner.nextLine();
        if(verificaDuplicidade(dicionarioIngles, novaPalavra)) {
            System.out.println();
            System.out.println("Está palavra já está cadastrada em seu dicionário!");
            alterarPalavra(index, dicionarioIngles, dicionarioPortugues, palavra);
        } else {
            dicionarioIngles[index] = novaPalavra.trim();
            cadastrarDicionarioPortugues(index, dicionarioPortugues, novaPalavra);
        }
    }
    public static void excluirPalavra(int index, String[] dicionarioIngles, String[] dicionarioPortugues) {
        dicionarioIngles[index] = null;
        dicionarioPortugues[index] = null;
    }

    public static int retornaIndex(String[] dicionarioIngles, String palavra) {
        int index = -1;
        for(int i = 0; i < dicionarioIngles.length; i++) {
            if(palavra.equals(dicionarioIngles[i])) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static int retornaQuantidadeCadastradas(String [] dicionarioIngles) {
        int contador = 0;
        for (String palavras : dicionarioIngles) {
            if (palavras != null) {
                contador++;
            }
        }
        return contador;
    }

    public static boolean verificaDuplicidade(String[] dicionarioIngles, String palavra) {
        for (String palavras : dicionarioIngles) {
            if (palavra.trim().equalsIgnoreCase(palavras)) return true;
        }
        return false;
    }

    public static boolean verificaDicionario(String[] dicionarioIngles) {
        for (String palavra : dicionarioIngles) {
            if (palavra != null) {
                return true;
            }
        }
        return false;
    }

    public static int verificaIndexNulo(String[] dicionarioIngles) {
        for(int i = 0; i < dicionarioIngles.length; i++) {
            if(dicionarioIngles[i] == null) return i;
        }
        return -1;
    }
}