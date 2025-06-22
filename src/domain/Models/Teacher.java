package domain.Models;

import java.util.List;

public class Teacher {
    private String name;
    private String document;
    private String phone;
    private String email;
    private List<Course> courses;

    public Teacher(String name, String document, String phone, String email, List<Course> courses) {
        this.name = name;
        this.document = document;
        this.phone = phone;
        this.email = email;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
