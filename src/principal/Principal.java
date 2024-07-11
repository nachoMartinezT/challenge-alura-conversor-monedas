package principal;

import modules.ConsultaMoneda;
import modules.CrearArchivo;
import modules.Moneda;
import modules.MonedaExchange;
import registro.Log;
import registro.Registro;

import java.util.Date;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        ConsultaMoneda consulta = new ConsultaMoneda();
        Scanner teclado = new Scanner(System.in);
        Log misRegistros = new Log();

        while (true) {
            boolean opcionValida = true;
            System.out.println("""
                    \n
                         ¡BIENVENIDO/A AL CONVERSOR DE MONEDAS!     
                    \n""");
            System.out.println("""
                    1) Convertir USD    =>  ARS
                    2) Convertir ARS    =>  USD
                    3) Convertir USD    =>  BRL
                    4) Convertir BRL    =>  USD
                    5) Convertir USD    =>  COP
                    6) Convertir COP    =>  USD
                    7) Elija sus propias monedas
                    8) Salir\n""");


            System.out.println("Elija una de las opciones: ");
            var opcion = teclado.nextLine();

            String base = "", objetivo = "", pais = "";
            try {
                if (Integer.valueOf(opcion) == 8) {
                    System.out.println(misRegistros);
                    CrearArchivo arch = new CrearArchivo();
                    arch.guardarJson(misRegistros);
                    break;
                }

                switch (Integer.valueOf(opcion)) {
                    case 1:
                        base = "USD";
                        objetivo = "ARS";
                        pais = "Argentina";
                        break;
                    case 2:
                        base = "ARS";
                        objetivo = "USD";
                        pais = "Argentina";
                        break;
                    case 3:
                        base = "USD";
                        objetivo = "BRL";
                        pais = "Brasil";
                        break;
                    case 4:
                        base = "BRL";
                        objetivo = "USD";
                        pais = "Brasil";
                        break;
                    case 5:
                        base = "USD";
                        objetivo = "COP";
                        pais = "Colombia";
                        break;
                    case 6:
                        base = "COP";
                        objetivo = "USD";
                        pais = "Colombia";
                        break;
                    case 7:
                        System.out.println("Escriba el código de la moneda base: ");
                        base = teclado.nextLine();
                        System.out.println("Escriba el código de la moneda objetivo: ");
                        objetivo = teclado.nextLine();
                        pais = "";
                        break;
                    default:
                        System.out.println("Opción no válida. Vuelva a intentarlo.");
                        opcionValida = false;
                        break;
                }

                if (opcionValida) {
                    System.out.println("Ingrese el importe a convertir: ");
                    Double valor = Double.valueOf(teclado.nextLine());

                    MonedaExchange monedaExch = consulta.buscaConversion(base, objetivo);

                    if (monedaExch.conversion_rate() != null) {      //Que el valor de la API no sea null (codigo mal ingresado)
                        Moneda miMoneda;
                        miMoneda = new Moneda(objetivo, pais);
                        miMoneda.setRatio(monedaExch);
                        miMoneda.setLastUpdate(monedaExch);

                        Double valConvertidoDouble = miMoneda.calcularConversion(valor);
                        System.out.println("\nEl importe de " + valor + " " + base + " equivale a =>>> " + String.format("%.2f", valConvertidoDouble) + " " + objetivo + "\n");

                        Date ahoraDate = new Date();
                        Registro reg = new Registro(ahoraDate.toString(), valor, base, objetivo, miMoneda.getRatio(), valConvertidoDouble, miMoneda.getLastUpdate());
                        misRegistros.agregarRegistro(reg);
                    } else {
                        System.out.println("El código de moneda ingresado es incorrecto. Vuelva a intentarlo.");
                    }
                }


            } catch (NumberFormatException e) {
                System.out.println("Ingreso no válido. Vuelva a intentarlo. " + e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
