import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Menu_coreano {

    public static void main(String[] args) throws IOException {

        Scanner ler = new Scanner(System.in);
        FileWriter arquivo = new FileWriter("conta.txt");

        System.out.println("      Bem Vindo ao Restaurante Hanok!");
        System.out.println("=====================MENU======================\n"
                + "=======COMIDAS=========|======BEBIDAS==========\n"
                + "11-Bibimbap     R$53,50|21-Soju         R$49,00\n"
                + "12-Bulgogui     R$60,00|22-Refrigerante R$8,50\n"
                + "13-Toppoki      R$64,00|23-Água         R$6,50\n"
                + "14-Sopa Sundubu R$65,00|24-Suco         R$12,00\n"
                + "00-SAIR");

        char opcao;
        double conta = 0; // corrigido: float -> double

        do {
            System.out.print("Quantos itens você gostaria de pedir? ");
            int qnt = ler.nextInt();

            String[] resumo = new String[qnt];

            boolean sair = false;
			
            for (int i = 0; i < qnt && !sair; i++) {
                
                System.out.print("Digite o código do " + (i+1) + "º item escolhido: ");
                int codigo = ler.nextInt();

                switch (codigo) {

                    case 11:
                        System.out.println("Bibimbap escolhido");
                        conta += 53.5;
                        resumo[i] = "Bibimbap";
                        break;

                    case 12:
                        System.out.println("Bulgogui escolhido");
                        conta += 60;
                        resumo[i] = "Bulgogui";
                        break;

                    case 13:
                        System.out.println("Toppoki escolhido");
                        conta += 64;
                        resumo[i] = "Toppoki";
                        break;

                    case 14:
                        System.out.println("Sopa Sundubu escolhida");
                        conta += 65;
                        resumo[i] = "Sopa Sundubu";
                        break;

                    case 21:
                        System.out.println("Soju escolhido");
                        conta += 49;
                        resumo[i] = "Soju";
                        break;

                    case 22:
                        System.out.println("Refrigerante escolhido");
                        conta += 8.5;
                        resumo[i] = "Refrigerante";
                        break;

                    case 23:
                        System.out.println("Água escolhida");
                        conta += 6.5;
                        resumo[i] = "Água";
                        break;

                    case 24:
                        System.out.println("Suco escolhido");
                        conta += 12;
                        resumo[i] = "Suco";
                        break;

                    case 00:
                        sair = true;
                        break;

                    default:
                        System.out.println("Opção inválida! Digite novamente.");
                        i--;
                }
            }

            // resumo do pedido
            System.out.println("\n======= RESUMO DO PEDIDO =======");
            arquivo.write("======= RESUMO DO PEDIDO =======\n");

            for (String item : resumo) {

                if (item != null) {
                    System.out.println("- " + item);
                    arquivo.write("- " + item + "\n");
                }
            }

            System.out.printf("Total da conta: R$ %.2f\n", conta);
            arquivo.write("Total da conta: R$"+ String.format("%.2f\n", conta));

            System.out.println("\nGostaria de pedir algo a mais? (S/N)");
            opcao = ler.next().charAt(0);

        } while (opcao != 'N' && opcao != 'n');

        System.out.println("Obrigado pela preferência!");

        ler.close();
		arquivo.close();
    }
}
