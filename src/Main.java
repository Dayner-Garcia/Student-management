import Utils.InputUtils;
import service.*;

import java.util.Scanner;

public class Main {
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final teacherService teacherService = new teacherService();
    private static final subjectsService subjectsService = new subjectsService();
    private static final SubjectsManagementService subjectsManagementService = new SubjectsManagementService();

    private static void menuEstudiantes() {
        studentService.menu();
    }

    private static void menuDocentes() {
        teacherService.menu();
    }

    private static void menuCursos() {
        courseService.menu();
    }

    private static void menuAsignaturas() {
        subjectsService.menu();
    }

    private static void menuEstudiantesPorAsignatura() {
        subjectsManagementService.menu();
    }

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Gestionar Estudiantes");
            System.out.println("2. Gestionar Docentes");
            System.out.println("3. Gestionar Cursos");
            System.out.println("4. Gestionar Asignaturas");
            System.out.println("5. Gestión de estudiantes por asignatura (avanzada)");
            System.out.println("6. Salir");
            do {
                opcion = InputUtils.leerEntero("Seleccione una opción (1–6): ", new Scanner(System.in));
            } while (opcion < 1 || opcion > 6);


            switch (opcion) {
                case 1 -> menuEstudiantes();
                case 2 -> menuDocentes();
                case 3 -> menuCursos();
                case 4 -> menuAsignaturas();
                case 5 -> menuEstudiantesPorAsignatura();
                case 6 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }
}