import java.math.BigDecimal;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        ConversorMoneda conversor = new ConversorMoneda();

        int opcion;
        String origen;
        String destino;
        URI direccion;
        Moneda moneda;
        boolean continuar = true;

        do{
            menu();
            try {
                System.out.print("Opcion: ");
                opcion = Integer.parseInt(entrada.nextLine());
                switch (opcion) {
                    case 1:
                        try {
                            System.out.print("Digite el monto a convertir: ");
                            double monto = entrada.nextDouble();
                            entrada = new Scanner(System.in);
                            System.out.print("Digite el codigo de la moneda de origen: ");
                            origen = entrada.nextLine().toUpperCase();
                            System.out.print("Digite el codigo de la moneda de destino: ");
                            destino = entrada.nextLine().toUpperCase();

                            direccion = URI.create("https://v6.exchangerate-api.com/v6/a6ebc38dba1f71deba6aac3f/pair/"
                                    + origen + "/" + destino + "/" + monto);
                            moneda = conversor.convertirMoneda(direccion);

                            System.out.println("$" + monto + gentilicioMoneda(origen) + " equivalen a $"
                                    + moneda.conversion_result() + gentilicioMoneda(destino) + "\n");
                            break;
                        } catch (Exception e) {
                            System.out.println("Codigo invalido: " + e.getMessage());
                        }
                        break;
                    case 2:
                        try {
                            entrada = new Scanner(System.in);
                            System.out.print("Digite el codigo de la moneda a conocer: ");
                            origen = entrada.nextLine().toUpperCase();
                            System.out.print("Digite el codigo de la moneda de destino: ");
                            destino = entrada.nextLine().toUpperCase();
                            direccion = URI.create("https://v6.exchangerate-api.com/v6/a6ebc38dba1f71deba6aac3f/pair/" + origen + "/" + destino + "/");
                            moneda = conversor.convertirMoneda(direccion);

                            System.out.println(1 + gentilicioMoneda(origen) + " equivale a "
                                    + moneda.conversion_rate() + gentilicioMoneda(destino));

                            break;
                        } catch (Exception e) {
                            System.out.println("Codigo invalido: " + e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.println("Vuelve pronto a nuestro conversor de monedas");
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opcion incorecta.");
                }
            } catch (Exception e) {
                System.out.println("Caracteres invalido: " + e.getMessage());
            }
        }while (continuar);
    }

    public static void menu(){
        System.out.println("""
                
                **************************************************
                Moneda                Codigo
                * Euro                  EUR
                * Dolar -               USD
                * Peso Col              COP
                * Peso Arg              ARS
                * Real Bra              BRL
                * Peso Chi              CLP
                * Bolivar Ven           VES
                * Boliviano Bol         BOB
                * Peso Uru              UYU
                * Guarani Para          PYG
                * Nuevo Sol Peru        PEN
                **************************************************
                ************* Converor de monedas ****************
                **************************************************
                
                Elije la opcion: 1, 2 o 3.
                1. Convertir cierta cantidad de valor a otra moneda.
                2. Conocer el valor de una moneda en otra moneda.
                3. Salir
                """);
    }

    public static String gentilicioMoneda(String origen){
        HashMap<String, String> nacionalidad = new HashMap<>();
        nacionalidad.put("USD", " Dolares estadounidense");
        nacionalidad.put("EUR", " Euros");
        nacionalidad.put("COP", " Pesos colombiano");
        nacionalidad.put("ARS", " Pesos argentino");
        nacionalidad.put("BRL", " Reales brasilero");
        nacionalidad.put("CLP", " Pesos chileno");
        nacionalidad.put("VES", " Bolivares venezolano");
        nacionalidad.put("BOB", " Bolivianos");
        nacionalidad.put("UYU", " Pesos uruguayo");
        nacionalidad.put("PYG", " Guaranis paraguayo");
        nacionalidad.put("PEN", " Soles peruano");

        return nacionalidad.get(origen);
    }
}