package service;

import Utils.InputUtils;
import domain.Models.Teacher;

import java.util.ArrayList;
import java.util.Scanner;

public class teacherService extends BaseService<Teacher> {

    private final Scanner sc = new Scanner(System.in);

    public void menu() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Docentes ---");
            System.out.println("1. Registrar docente");
            System.out.println("2. Listar docentes");
            System.out.println("3. Buscar docente por documento");
            System.out.println("4. Eliminar docente");
            System.out.println("5. Volver al menú principal");

            opcion = InputUtils.leerEntero("Seleccione una opción: ", sc);

            switch (opcion) {
                case 1 -> registrarDocente();
                case 2 -> listarDocentes();
                case 3 -> buscarDocente();
                case 4 -> eliminarDocente();
                case 5 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 5);
    }

    private void registrarDocente() {
        String nombre = InputUtils.leerCadena("Nombre del docente: ");
        String documento = InputUtils.leerCadena("Documento: ");
        String telefono = InputUtils.leerTelefono("Teléfono (10 dígitos): ");
        String email = InputUtils.leerEmail("Correo electrónico: ");
        Teacher nuevo = new Teacher(nombre, documento, telefono, email, new ArrayList<>());
        var result = create(nuevo);
        System.out.println(result.getMessage());
    }

    private void listarDocentes() {
        var result = findAll();
        if (!result.isSuccess() || result.getData().isEmpty()) {
            System.out.println("No hay docentes registrados.");
            return;
        }

        result.getData().forEach(t ->
                System.out.printf("Docente: %s | Documento: %s | Email: %s\n",
                        t.getName(), t.getDocument(), t.getEmail()));
    }

    private void buscarDocente() {
        String documento = InputUtils.leerCadena("Ingrese el documento del docente: ");
        var result = findOne(t -> t.getDocument().equalsIgnoreCase(documento));
        System.out.println(result.getMessage());
        if (result.isSuccess()) {
            Teacher t = result.getData();
            System.out.printf("Nombre: %s\nEmail: %s\nTeléfono: %s\n",
                    t.getName(), t.getEmail(), t.getPhone());
        }
    }

    private void eliminarDocente() {
        String documento = InputUtils.leerCadena("Documento del docente a eliminar: ");
        var result = delete(t -> t.getDocument().equalsIgnoreCase(documento));
        System.out.println(result.getMessage());
    }
}