import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String menu = """
                [d] Depositar
                [s] Sacar
                [e] Extrato
                [q] Sair
                
                =>""";

        double saldo = 0;
        double limite = 500;
        StringBuilder extrato = new StringBuilder();
        int numeroSaques = 0;
        final int LIMITE_SAQUES = 3;

        while (true) {
            System.out.print(menu);
            String opcao = scanner.next();

            switch (opcao) {
                case "d" -> {
                    System.out.print("Informar o valor do depósito: ");
                    double valor = scanner.nextDouble();
                    if (valor > 0) {
                        saldo += valor;
                        extrato.append(String.format("Depósito: R$ %.2f%n", valor));
                    } else {
                        System.out.println("Operação falhou: o valor informado é inválido.");
                    }
                }
                case "s" -> {
                    System.out.print("Informe o valor do saque: ");
                    double valor = scanner.nextDouble();

                    boolean excedeuSaldo = valor > saldo;
                    boolean excedeuLimite = valor > limite;
                    boolean excedeuSaques = numeroSaques >= LIMITE_SAQUES;

                    if (excedeuSaldo) {
                        System.out.println("Operação falhou! Você não tem saldo suficiente.");
                    } else if (excedeuLimite) {
                        System.out.println("Operação falhou! O valor do saque excedeu o limite.");
                    } else if (excedeuSaques) {
                        System.out.println("Operação falhou! Número máximo de saques excedido.");
                    } else if (valor > 0) {
                        saldo -= valor;
                        extrato.append(String.format("Saque: R$ %.2f%n", valor));
                        numeroSaques++;
                    } else {
                        System.out.println("Operação falhou! O valor informado é inválido.");
                    }
                }
                case "e" -> {
                    System.out.println("\n=============== EXTRATO ===============");
                    if (extrato.length() == 0) {
                        System.out.println("Não foram realizadas movimentações.");
                    } else {
                        System.out.println(extrato);
                    }
                    System.out.printf("Saldo: R$ %.2f%n", saldo);
                    System.out.println("========================================");
                }
                case "q" -> {
                    System.out.println("Encerrando o sistema...");
                    return; // Encerra o loop e o programa
                }
                default -> System.out.println("Operação inválida. Por favor, selecione novamente a operação desejada.");
            }
        }
    }
}
d