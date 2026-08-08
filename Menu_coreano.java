import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class MenuCoreano {

    static class Item {
        String nome;
        double preco;

        Item(String nome, double preco) {
            this.nome = nome;
            this.preco = preco;
        }
    }

    public static void main(String[] args) {
        Map<Integer, Item> menu = criarMenu();

        try (Scanner ler = new Scanner(System.in);
             FileWriter arquivo = new FileWriter("conta.txt")) {

            exibirMenu();

            double conta = 0;
            Map<Integer, Integer> quantidades = new HashMap<>();
            StringBuilder resumoGeral = new StringBuilder();

            char opcao;
            do {
                System.out.print("Quantos itens você gostaria de pedir? ");
                int qnt = ler.nextInt();

                boolean sair = false;
                for (int i = 0; i < qnt && !sair; i++) {
                    System.out.print("Digite o código do " + (i + 1) + "º item escolhido: ");
                    int codigo = ler.nextInt();

                    if (codigo == 0) {
                        sair = true;
                        break;
                    }

                    if (menu.containsKey(codigo)) {
                        Item item = menu.get(codigo);
                        conta += item.preco;
                        quantidades.put(codigo, quantidades.getOrDefault(codigo, 0) + 1);
                        resumoGeral.append("- ").append(item.nome).append("\n");
                        System.out.println(item.nome + " escolhido");
                    } else {
                        System.out.println("Opção inválida! Digite novamente.");
                        i--; // repete a iteração
                    }
                }

                System.out.println("\nGostaria de pedir algo a mais? (S/N)");
                opcao = ler.next().charAt(0);

            } while (opcao != 'N' && opcao != 'n');

            gerarResumo(arquivo, menu, quantidades, resumoGeral, conta);

        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Use apenas números.");
        }
    }

    private static Map<Integer, Item> criarMenu() {
        Map<Integer, Item> menu = new HashMap<>();
        menu.put(11, new Item("Bibimbap", 53.5));
        menu.put(12, new Item("Bulgogui", 60.0));
        menu.put(13, new Item("Toppoki", 64.0));
        menu.put(14, new Item("Sopa Sundubu", 65.0));
        menu.put(21, new Item("Soju", 49.0));
        menu.put(22, new Item("Refrigerante", 8.5));
        menu.put(23, new Item("Água", 6.5));
        menu.put(24, new Item("Suco", 12.0));
        return menu;
    }

    private static void exibirMenu() {
        System.out.println("      Bem Vindo ao Restaurante Hanok!");
        System.out.println("=====================MENU======================");
        System.out.println("========COMIDAS========|========BEBIDAS========");
        System.out.println("11-Bibimbap     R$53,50|21-Soju         R$49,00");
        System.out.println("12-Bulgogui     R$60,00|22-Refrigerante R$8,50");
        System.out.println("13-Toppoki      R$64,00|23-Água         R$6,50");
        System.out.println("14-Sopa Sundubu R$65,00|24-Suco         R$12,00");
        System.out.println("00-SAIR");
    }

    private static void gerarResumo(FileWriter arquivo, Map<Integer, Item> menu,
                                    Map<Integer, Integer> quantidades,
                                    StringBuilder resumoGeral, double conta) throws IOException {

        System.out.println("\n======= RESUMO DO PEDIDO =======");
        arquivo.write("======= RESUMO DO PEDIDO =======\n");

        System.out.println(resumoGeral);
        arquivo.write(resumoGeral.toString());

        System.out.println("Quantidade de cada item:");
        arquivo.write("\nQuantidade de cada item:\n");

        for (Map.Entry<Integer, Integer> entry : quantidades.entrySet()) {
            int codigo = entry.getKey();
            int qtd = entry.getValue();
            Item item = menu.get(codigo);
            System.out.println("- " + item.nome + ": " + qtd);
            arquivo.write("- " + item.nome + ": " + qtd + "\n");
        }

        System.out.printf("\nTotal da conta: R$ %.2f\n", conta);
        arquivo.write("\nTotal da conta: R$" + String.format("%.2f\n", conta));

        System.out.println("Obrigado pela preferência!");
    }
}
