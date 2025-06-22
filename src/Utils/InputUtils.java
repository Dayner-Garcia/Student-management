package Utils;

import java.util.Scanner;

public class InputUtils {

    private static final Scanner scanner = new Scanner(System.in);

    public static int leerEntero(String mensaje, Scanner sc) {
        int numero;
        while (true) {
            System.out.print(mensaje);
            try {
                numero = Integer.parseInt(sc.nextLine());
                if (numero > 0) return numero;
                System.out.println("Debe ser mayor que 0.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida, debe ser un número entero.");
            }
        }
    }

    public static int leerEntero() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese un número: ");
            }
        }
    }

    public static String leerCadena(String mensaje) {
        String texto;
        while (true) {
            System.out.print(mensaje);
            texto = scanner.nextLine();
            if (!texto.trim().isEmpty()) {
                return texto;
            } else {
                System.out.println("Entrada inválida. No puede estar vacía.");
            }
        }
    }

    public static String leerEmail(String mensaje) {
        String correo;
        while (true) {
            correo = leerCadena(mensaje);
            if (correo.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
                return correo;
            } else {
                System.out.println("Correo electrónico inválido.");
            }
        }
    }

    public static String leerTelefono(String mensaje) {
        String telefono;
        while (true) {
            telefono = leerCadena(mensaje);
            if (telefono.matches("\\d{10}")) {
                return telefono;
            } else {
                System.out.println("Teléfono inválido. Solo dígitos (10).");
            }
        }
    }
}