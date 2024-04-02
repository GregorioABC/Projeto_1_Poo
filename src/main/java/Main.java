import java.util.Scanner;
//classe produto
class Produto {
    String nome;
    int quantidade;
    double preco;
    String categoria;
    Produto(String nome, int quantidade, double preco, String categoria) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.categoria = categoria;
    }
}
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Produto[] estoque = new Produto[100];
    static int numProdutos = 0;
    static String usuario;
    static String senha;
    public static void main(String[] args) {
        cadastrarUsuario();
        login();
        menu();
    }
    //usuario 
    static void cadastrarUsuario() {
        usuario = "admin";
        senha = "123";
    }
    //login de acesso
    static void login() {
        System.out.println("Olá seja bem vindo(a) ao Mundo Suplementos!!");
        System.out.print("Digite seu login: ");
        String login = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senhaDigitada = scanner.nextLine();
        if (!login.equals(usuario) || !senhaDigitada.equals(senha)) {
            System.out.println("Login ou senha incorretos.");
            System.exit(0);
        }
    }
    //menu de escolha
    static void menu() {
        int opcao;
        do {
            System.out.println("\nMENU:");
            System.out.println("1. Adicionar suplemento");
            System.out.println("2. Remover suplemento");
            System.out.println("3. Consultar suplemento");
            System.out.println("4. Atualizar suplemento");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    adicionarProduto();
                    break;
                case 2:
                    removerProduto();
                    break;
                case 3:
                    consultarProduto();
                    break;
                case 4:
                    atualizarProduto();
                    break;
                case 0:
                    System.out.println("Saindo do programa.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
    //adicionar produto
    static void adicionarProduto() {
        System.out.print("\nDigite o nome do Suplemento: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a quantidade do suplemento: ");
        int quantidade = scanner.nextInt();
        if (quantidade <= 0) {
            System.out.println("Quantidade inválida. A quantidade deve ser maior que zero.");
            return;
        }
        System.out.print("Digite o preço do suplemento: ");
        double preco = scanner.nextDouble();
        if (preco <= 0) {
            System.out.println("Preço inválido. O preço deve ser positivo.");
            return;
        }
        scanner.nextLine(); 
        System.out.println("Digite o tipo do suplemento:");
        System.out.println("1. Em pó");
        System.out.println("2. Em pílulas");
        int tipo = scanner.nextInt();
        scanner.nextLine(); 
        String categoria;
        if (tipo == 1) {
            categoria = "em pó";
        } else if (tipo == 2) {
            categoria = "em pílulas";
        } else {
            System.out.println("Opção inválida. O programa será encerrado.");
            return;
        }
        Produto produto = new Produto(nome, quantidade, preco, categoria);
        estoque[numProdutos] = produto;
        numProdutos++;
        System.out.println("Suplemento adicionado.");
        if (quantidade <= 1) {
            System.out.println("ALERTA: Estoque baixo para o suplemento '" + nome + "'.");
        }
    }
    //remover o produto
    static void removerProduto() {
        System.out.print("\nDigite o nome do suplemento a ser removido : ");
        String nome = scanner.nextLine();
        boolean encontrado = false;
        for (int i = 0; i < numProdutos; i++) {
            if (estoque[i].nome.equalsIgnoreCase(nome)) {
                encontrado = true;
                for (int j = i; j < numProdutos - 1; j++) {
                    estoque[j] = estoque[j + 1];
                }
                numProdutos--;
                System.out.println("Suplemento removido");
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Suplemento não encontrado.");
        }
    }
    //consultar o produto
    static void consultarProduto() {
        System.out.println("\nDigite o tipo do suplemento:");
        System.out.println("1. Em pó");
        System.out.println("2. Em pílulas");
        int tipo = scanner.nextInt();
        scanner.nextLine(); 
        String categoria;
        if (tipo == 1) {
            categoria = "em pó";
        } else if (tipo == 2) {
            categoria = "em pílulas";
        } else {
            System.out.println("Opção inválida. O programa será encerrado.");
            return;
        }
        System.out.print("Digite o nome do suplemento para consulta : ");
        String nome = scanner.nextLine();
        boolean encontrado = false;
        for (int i = 0; i < numProdutos; i++) {
            if (estoque[i].categoria.equals(categoria) && estoque[i].nome.equalsIgnoreCase(nome)) {
                encontrado = true;
                System.out.println("Nome: " + estoque[i].nome);
                System.out.println("Quantidade: " + estoque[i].quantidade);
                System.out.println("Preço: " + estoque[i].preco);
                System.out.println("Categoria: " + estoque[i].categoria);
                if (estoque[i].quantidade <= 1) {
                    System.out.println("ALERTA: Estoque baixo para este suplemento.");
                }
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Suplemento não encontrado no estoque.");
        }
    }
    //atualizar o produto
    static void atualizarProduto() {
        System.out.print("\nDigite o nome do Suplemento que vai ser atualizado : ");
        String nome = scanner.nextLine();
        boolean encontrado = false;
        for (int i = 0; i < numProdutos; i++) {
            if (estoque[i].nome.equalsIgnoreCase(nome)) {
                encontrado = true;
                System.out.print("Digite a nova quantidade de suplemento: ");
                int quantidade = scanner.nextInt();
                System.out.print("Digite o novo preço do suplemento: ");
                double preco = scanner.nextDouble();
                estoque[i].quantidade = quantidade;
                estoque[i].preco = preco;
                System.out.println("Suplemento atualizado");
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Produto não encontrado.");
        }
    }
}
