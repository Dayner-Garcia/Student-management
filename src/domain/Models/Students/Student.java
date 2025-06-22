package domain.Models.Students;

import java.util.ArrayList;
import java.util.List;

public abstract class Student {
    private String name;
    private String code;
    private final List<Double> grades = new ArrayList<>();

    public Student(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public abstract String getType();

    public void addGrade(double grade) {
        grades.add(grade);
    }

    public double calculateAverage() {
        return grades.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public boolean isApproved() {
        return calculateAverage() >= 70;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public List<Double> getGrades() {
        return grades;
    }
}