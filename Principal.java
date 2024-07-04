import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        ConversorDeMoedas conversorDeMoedas = new ConversorDeMoedas();

            while(true) {

            System.out.println("""
                    Dólar Americano - USD
                    Dólar Canadense - CAD
                    Libra Esterlina - GBP
                    Euro - EUR
                    Franco Suiço - CHF
                    Real - BRL
                    
                    Digite a sigla da moeda de origem:
                    """);
            var moedaOrigem = leitura.nextLine().toUpperCase();

            if(moedaOrigem.equalsIgnoreCase("SAIR")) {
                break;
            }

            System.out.println("Digite a moeda de destino: ");
            var moedaDestino = leitura.nextLine().toUpperCase();

            System.out.println("Digite o valor a ser convertido: ");
            var valorParaConverter = leitura.nextDouble();
            leitura.nextLine();

            try {
                double taxaDeConversao = conversorDeMoedas.buscaTaxaDeConversao(moedaOrigem, moedaDestino);
                double valorConvertido = valorParaConverter * taxaDeConversao;
                System.out.printf("%.2f %s é equivalente a %.2f %s\n", valorParaConverter, moedaOrigem, valorConvertido, moedaDestino);

                Conversor novoConversor = conversorDeMoedas.buscaMoeda(moedaOrigem);
                GeradorDeArquivos gerador = new GeradorDeArquivos();
                gerador.salvaJson(novoConversor);
            } catch (RuntimeException | IOException e) {
                System.out.println(e.getMessage());
                System.out.println("Finalizando a aplicação");
            }
        }
        leitura.close();
        }
}
