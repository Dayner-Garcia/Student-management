package service;

import Utils.InputUtils;
import domain.Models.Course;

import java.util.ArrayList;
import java.util.Scanner;

public class CourseService extends BaseService<Course> {
    private final Scanner sc = new Scanner(System.in);

    public void menu() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Cursos ---");
            System.out.println("1. Agregar curso");
            System.out.println("2. Listar cursos");
            System.out.println("3. Buscar curso por nombre");
            System.out.println("4. Eliminar curso");
            System.out.println("5. Volver al menú principal");
            opcion = InputUtils.leerEntero("Seleccione una opción: ", sc);

            switch (opcion) {
                case 1 -> agregarCurso();
                case 2 -> listarCursos();
                case 3 -> buscarCurso();
                case 4 -> eliminarCurso();
                case 5 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 5);
    }

    private void agregarCurso() {
        String nombre = InputUtils.leerCadena("Nombre del curso: ");
        Course nuevoCurso = new Course(nombre, new ArrayList<>());
        var result = create(nuevoCurso);
        System.out.println(result.getMessage());
    }

    private void listarCursos() {
        var result = findAll();
        if (!result.isSuccess() || result.getData().isEmpty()) {
            System.out.println("No hay cursos registrados.");
            return;
        }

        result.getData().forEach(c -> {
            System.out.printf("Curso: %s | Estudiantes: %d\n", c.getName(),
                    c.getStudents() != null ? c.getStudents().size() : 0);
        });
    }

    private void buscarCurso() {
        String nombre = InputUtils.leerCadena("Nombre del curso a buscar: ");
        var result = findOne(c -> c.getName().equalsIgnoreCase(nombre));
        System.out.println(result.getMessage());
        if (result.isSuccess()) {
            var curso = result.getData();
            if (curso.getStudents() != null && !curso.getStudents().isEmpty()) {
                curso.getStudents().forEach(s ->
                        System.out.printf(" - %s (%s)\n", s.getName(), s.getCode()));
            } else {
                System.out.println("Sin estudiantes registrados en este curso.");
            }
        }
    }

    private void eliminarCurso() {
        String nombre = InputUtils.leerCadena("Nombre del curso a eliminar: ");
        var result = delete(c -> c.getName().equalsIgnoreCase(nombre));
        System.out.println(result.getMessage());
    }
}