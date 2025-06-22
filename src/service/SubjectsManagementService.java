package service;

import Utils.InputUtils;
import domain.Models.Group;
import domain.Models.Students.DistanceStudent;
import domain.Models.Students.PresentialStudent;
import domain.Models.Students.Student;
import domain.Models.Subjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SubjectsManagementService {
    private final Scanner sc = new Scanner(System.in);
    private final List<Subjects> subjects = new ArrayList<>();

    public void menu() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Asignaturas y Grupos ---");
            System.out.println("1. Crear asignatura");
            System.out.println("2. Agregar grupo a asignatura");
            System.out.println("3. Agregar estudiante a grupo");
            System.out.println("4. Registrar calificación");
            System.out.println("5. Ver calificaciones por grupo");
            System.out.println("6. Ver % de aprobados por grupo");
            System.out.println("7. Volver al menú principal");

            opcion = InputUtils.leerEntero("Seleccione una opción: ", sc);

            switch (opcion) {
                case 1 -> crearAsignatura();
                case 2 -> agregarGrupo();
                case 3 -> agregarEstudiante();
                case 4 -> registrarCalificacion();
                case 5 -> verCalificaciones();
                case 6 -> verPorcentajeAprobados();
                case 7 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 7);
    }

    private Subjects buscarAsignaturaPorCodigo() {
        String codigo = InputUtils.leerCadena("Código de la asignatura: ");
        return subjects.stream()
                .filter(s -> s.getCode().equalsIgnoreCase(codigo))
                .findFirst()
                .orElse(null);
    }

    private Group buscarGrupo(Subjects asignatura) {
        String nombreGrupo = InputUtils.leerCadena("Nombre del grupo: ");
        return asignatura.getGroups().stream()
                .filter(g -> g.getName().equalsIgnoreCase(nombreGrupo))
                .findFirst()
                .orElse(null);
    }

    private void crearAsignatura() {
        String nombre = InputUtils.leerCadena("Nombre de la asignatura: ");
        String codigo = InputUtils.leerCadena("Código de la asignatura: ");
        Subjects nueva = new Subjects(nombre, codigo, new ArrayList<>());
        subjects.add(nueva);
        System.out.println("Asignatura creada correctamente.");
    }

    private void agregarGrupo() {
        Subjects asignatura = buscarAsignaturaPorCodigo();
        if (asignatura == null) {
            System.out.println("Asignatura no encontrada.");
            return;
        }
        String nombreGrupo = InputUtils.leerCadena("Nombre del grupo: ");
        asignatura.addGroup(new Group(nombreGrupo));
        System.out.println("Grupo agregado.");
    }

    private void agregarEstudiante() {
        Subjects asignatura = buscarAsignaturaPorCodigo();
        if (asignatura == null) {
            System.out.println("Asignatura no encontrada.");
            return;
        }
        Group grupo = buscarGrupo(asignatura);
        if (grupo == null) {
            System.out.println("Grupo no encontrado.");
            return;
        }
        String nombre = InputUtils.leerCadena("Nombre del estudiante: ");
        String codigo = InputUtils.leerCadena("Código del estudiante: ");

        System.out.println("Tipo de estudiante: ");
        System.out.println("1. Presencial");
        System.out.println("2. A distancia");
        int tipo = InputUtils.leerEntero("Seleccione: ", sc);

        Student estudiante;
        if (tipo == 1) {
            estudiante = new PresentialStudent(nombre, codigo);
        } else {
            estudiante = new DistanceStudent(nombre, codigo);
        }
        grupo.addStudent(estudiante);
        System.out.println("Estudiante agregado.");
    }

    private void registrarCalificacion() {
        Subjects asignatura = buscarAsignaturaPorCodigo();
        if (asignatura == null) {
            System.out.println("Asignatura no encontrada.");
            return;
        }
        Group grupo = buscarGrupo(asignatura);
        if (grupo == null) {
            System.out.println("Grupo no encontrado.");
            return;
        }

        String codigo = InputUtils.leerCadena("Código del estudiante: ");
        Student estudiante = grupo.getStudents().stream()
                .filter(s -> s.getCode().equalsIgnoreCase(codigo))
                .findFirst()
                .orElse(null);

        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        double nota = InputUtils.leerEntero("Ingrese nota (0-100): ", sc);
        estudiante.addGrade(nota);
        System.out.println("Calificación registrada.");
    }

    private void verCalificaciones() {
        Subjects asignatura = buscarAsignaturaPorCodigo();
        if (asignatura == null) {
            System.out.println("Asignatura no encontrada.");
            return;
        }
        Group grupo = buscarGrupo(asignatura);
        if (grupo == null) {
            System.out.println("Grupo no encontrado.");
            return;
        }

        System.out.printf("Calificaciones del grupo %s:\n", grupo.getName());
        for (Student s : grupo.getStudents()) {
            System.out.printf("%s (%s) - Promedio: %.2f - Tipo: %s\n",
                    s.getName(), s.getCode(), s.calculateAverage(), s.getType());
        }
    }

    private void verPorcentajeAprobados() {
        Subjects asignatura = buscarAsignaturaPorCodigo();
        if (asignatura == null) {
            System.out.println("Asignatura no encontrada.");
            return;
        }
        Group grupo = buscarGrupo(asignatura);
        if (grupo == null) {
            System.out.println("Grupo no encontrado.");
            return;
        }

        double porcentaje = grupo.getApprovalPercentage();
        System.out.printf("Porcentaje de aprobados en %s: %.2f%%\n", grupo.getName(), porcentaje);
    }
}