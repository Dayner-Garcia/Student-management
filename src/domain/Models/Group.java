package domain.Models;

import domain.Models.Students.Student;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String name;
    private final List<Student> students = new ArrayList<>();

    public Group(String name) {
        this.name = name;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public String getName() {
        return name;
    }

    public double getApprovalPercentage() {
        long approved = students.stream().filter(Student::isApproved).count();
        return students.isEmpty() ? 0 : (approved * 100.0) / students.size();
    }
}