import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Menu_coreano {

    public static void main(String[] args) throws IOException {

        Scanner ler = new Scanner(System.in); // para ler as entradas do usuário
        FileWriter arquivo = new FileWriter("conta.txt"); // para criar um arquivo de texto onde o resumo do pedido será salvo

        System.out.println("      Bem Vindo ao Restaurante Hanok!");
        System.out.println("=====================MENU======================\n"
                + "========COMIDAS========|========BEBIDAS========\n"
                + "11-Bibimbap     R$53,50|21-Soju         R$49,00\n"
                + "12-Bulgogui     R$60,00|22-Refrigerante R$8,50\n"
                + "13-Toppoki      R$64,00|23-Água         R$6,50\n"
                + "14-Sopa Sundubu R$65,00|24-Suco         R$12,00\n"
                + "00-SAIR");

        char opcao; // variável para controlar se o cliente deseja pedir algo a mais ou finalizar o pedido
        double conta = 0; // variável para calcular o total da conta
        int qnt11 = 0, qnt12 = 0, qnt13 = 0, qnt14 = 0, qnt21 = 0, qnt22 = 0, qnt23 = 0, qnt24 = 0; 
        // variáveis para contar a quantidade de cada item pedido
        String resumoGeral = ""; // variável para armazenar o resumo geral do pedido, que será exibido no final

        do {
            System.out.print("Quantos itens você gostaria de pedir? ");
            int qnt = ler.nextInt(); // quantidade de itens que o cliente deseja pedir

            boolean sair = false; // variável para controlar se o cliente deseja sair do processo de escolha dos itens, caso ele digite o código 00

            for (int i = 0; i < qnt && !sair; i++) {

                System.out.print("Digite o código do " + (i+1) + "º item escolhido: ");
                int codigo = ler.nextInt(); 
                // código do item escolhido pelo cliente, que será usado para identificar qual item foi pedido e calcular o valor da conta

                switch (codigo) {

                    case 11:
                        System.out.println("Bibimbap escolhido");
                        conta += 53.5;
                        qnt11++;
                        resumoGeral += "- Bibimbap\n";
                        break;

                    case 12:
                        System.out.println("Bulgogui escolhido");
                        conta += 60;
                        qnt12++;
                        resumoGeral += "- Bulgogui\n";
                        break;

                    case 13:
                        System.out.println("Toppoki escolhido");
                        conta += 64;
                        qnt13++;
                        resumoGeral += "- Toppoki\n";
                        break;

                    case 14:
                        System.out.println("Sopa Sundubu escolhida");
                        conta += 65;
                        qnt14++;
                        resumoGeral += "- Sopa Sundubu\n";
                        break;

                    case 21:
                        System.out.println("Soju escolhido");
                        conta += 49;
                        qnt21++;
                        resumoGeral += "- Soju\n";
                        break;

                    case 22:
                        System.out.println("Refrigerante escolhido");
                        conta += 8.5;
                        qnt22++;
                        resumoGeral += "- Refrigerante\n";
                        break;

                    case 23:
                        System.out.println("Água escolhida");
                        conta += 6.5;
                        qnt23++;
                        resumoGeral += "- Água\n";
                        break;

                    case 24:
                        System.out.println("Suco escolhido");
                        conta += 12;
                        qnt24++;
                        resumoGeral += "- Suco\n";
                        break;

                    case 00:
                        sair = true; // se o cliente digitar 00, ele deseja sair do processo de escolha dos itens, então a variável sair é setada como true para interromper o loop
                        break;

                    default:
                        System.out.println("Opção inválida! Digite novamente.");
                        i--; // para repetir a iteração atual e permitir que o cliente digite um código válido
                        break;
                }
            }

            System.out.println("\nGostaria de pedir algo a mais? (S/N)"); 
            opcao = ler.next().charAt(0); // o cliente é perguntado se deseja pedir algo a mais, e a resposta é lida como um caractere. 

        } while (opcao != 'N' && opcao != 'n'); // o loop continua enquanto o cliente desejar pedir algo a mais, ou seja, enquanto ele não digitar 'N' ou 'n'


        // exibir o resumo geral do pedido e o total da conta
        System.out.println("\n======= RESUMO DO PEDIDO =======");
        arquivo.write("======= RESUMO DO PEDIDO =======\n");

        System.out.println(resumoGeral); 
        arquivo.write(resumoGeral);

        System.out.println("Quantidade de cada item:"); 
        arquivo.write("\nQuantidade de cada item:\n");

        if (qnt11 > 0) {
            System.out.println("- Bibimbap: " + qnt11);
            arquivo.write("- Bibimbap: " + qnt11 + "\n");
        }
        if (qnt12 > 0) {
            System.out.println("- Bulgogui: " + qnt12);
            arquivo.write("- Bulgogui: " + qnt12 + "\n");
        }
        if (qnt13 > 0) {
            System.out.println("- Toppoki: " + qnt13);
            arquivo.write("- Toppoki: " + qnt13 + "\n");
        }
        if (qnt14 > 0) {
            System.out.println("- Sopa Sundubu: " + qnt14);
            arquivo.write("- Sopa Sundubu: " + qnt14 + "\n");
        }
        if (qnt21 > 0) {
            System.out.println("- Soju: " + qnt21);
            arquivo.write("- Soju: " + qnt21 + "\n");
        }
        if (qnt22 > 0) {
            System.out.println("- Refrigerante: " + qnt22);
            arquivo.write("- Refrigerante: " + qnt22 + "\n");
        }
        if (qnt23 > 0) {
            System.out.println("- Água: " + qnt23);
            arquivo.write("- Água: " + qnt23 + "\n");
        }
        if (qnt24 > 0) {
            System.out.println("- Suco: " + qnt24);
            arquivo.write("- Suco: " + qnt24 + "\n");
        }

        System.out.printf("\nTotal da conta: R$ %.2f\n", conta);
        arquivo.write("\nTotal da conta: R$"+ String.format("%.2f\n", conta));

        System.out.println("Obrigado pela preferência!");

        ler.close();
        arquivo.close();
    }
}