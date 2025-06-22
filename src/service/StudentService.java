package service;

import Utils.InputUtils;
import domain.Models.Students.DistanceStudent;
import domain.Models.Students.PresentialStudent;
import domain.Models.Students.Student;

import java.util.Scanner;

public class StudentService extends BaseService<Student> {

    private final Scanner sc = new Scanner(System.in);

    public void menu() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Estudiantes ---");
            System.out.println("1. Agregar estudiante");
            System.out.println("2. Listar estudiantes");
            System.out.println("3. Buscar estudiante por código");
            System.out.println("4. Eliminar estudiante");
            System.out.println("5. Volver al menú principal");
            opcion = InputUtils.leerEntero("Seleccione una opción: ", sc);

            switch (opcion) {
                case 1 -> agregarEstudiante();
                case 2 -> listarEstudiantes();
                case 3 -> buscarEstudiante();
                case 4 -> eliminarEstudiante();
                case 5 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 5);
    }

    private void agregarEstudiante() {
        String nombre = InputUtils.leerCadena("Nombre del estudiante: ");
        String codigo = InputUtils.leerCadena("Código del estudiante: ");

        System.out.println("Tipo de estudiante:");
        System.out.println("1. Presencial");
        System.out.println("2. A distancia");
        int tipo;
        do {
            tipo = InputUtils.leerEntero("Seleccione: ", sc);
            if (tipo != 1 && tipo != 2) {
                System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (tipo != 1 && tipo != 2);

        Student nuevo;
        if (tipo == 1) {
            nuevo = new PresentialStudent(nombre, codigo);
        } else if (tipo == 2) {
            nuevo = new DistanceStudent(nombre, codigo);
        } else {
            System.out.println("Tipo inválido. Cancelando operación.");
            return;
        }

        var result = create(nuevo);
        System.out.println(result.getMessage());
    }

    private void listarEstudiantes() {
        var result = findAll();
        if (!result.isSuccess() || result.getData().isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
        } else {
            result.getData().forEach(s -> System.out.printf("%s (%s) - Tipo: %s\n", s.getName(), s.getCode(), s.getType()));
        }
    }

    private void buscarEstudiante() {
        String code = InputUtils.leerCadena("Código del estudiante: ");
        var result = findOne(s -> s.getCode().equalsIgnoreCase(code));
        System.out.println(result.getMessage());
        if (result.isSuccess()) {
            var s = result.getData();
            System.out.printf("Nombre: %s\nCódigo: %s\nTipo: %s\n", s.getName(), s.getCode(), s.getType());
        }
    }

    private void eliminarEstudiante() {
        String code = InputUtils.leerCadena("Código del estudiante a eliminar: ");
        var result = delete(s -> s.getCode().equalsIgnoreCase(code));
        System.out.println(result.getMessage());
    }
}