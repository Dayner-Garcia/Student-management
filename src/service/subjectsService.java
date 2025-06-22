package service;

import Utils.InputUtils;
import domain.Models.Subjects;

import java.util.ArrayList;
import java.util.Scanner;

public class subjectsService extends BaseService<Subjects> {
    private final Scanner sc = new Scanner(System.in);

    public void menu() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Asignaturas ---");
            System.out.println("1. Agregar asignatura");
            System.out.println("2. Listar asignaturas");
            System.out.println("3. Buscar asignatura por código");
            System.out.println("4. Eliminar asignatura");
            System.out.println("5. Volver al menú principal");
            opcion = InputUtils.leerEntero("Seleccione una opción: ", sc);

            switch (opcion) {
                case 1 -> agregarAsignatura();
                case 2 -> listarAsignaturas();
                case 3 -> buscarAsignatura();
                case 4 -> eliminarAsignatura();
                case 5 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 5);
    }

    private void agregarAsignatura() {
        String nombre = InputUtils.leerCadena("Nombre de la asignatura: ");
        String codigo = InputUtils.leerCadena("Código de la asignatura: ");
        Subjects nueva = new Subjects(nombre, codigo, new ArrayList<>());
        var result = create(nueva);
        System.out.println(result.getMessage());
    }

    private void listarAsignaturas() {
        var result = findAll();
        if (!result.isSuccess() || result.getData().isEmpty()) {
            System.out.println("No hay asignaturas registradas.");
            return;
        }

        result.getData().forEach(s ->
                System.out.printf("Asignatura: %s | Código: %s | Docentes: %d\n",
                        s.getName(), s.getCode(),
                        s.getTeachers() != null ? s.getTeachers().size() : 0));
    }

    private void buscarAsignatura() {
        String codigo = InputUtils.leerCadena("Ingrese el código de la asignatura: ");
        var result = findOne(s -> s.getCode().equalsIgnoreCase(codigo));
        System.out.println(result.getMessage());
        if (result.isSuccess()) {
            Subjects s = result.getData();
            System.out.println("Asignatura encontrada: " + s.getName());
        }
    }

    private void eliminarAsignatura() {
        String codigo = InputUtils.leerCadena("Código de la asignatura a eliminar: ");
        var result = delete(s -> s.getCode().equalsIgnoreCase(codigo));
        System.out.println(result.getMessage());
    }
}