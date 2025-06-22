package domain.Models;

import java.util.ArrayList;
import java.util.List;

public class Subjects {

    private String name;
    private String code;
    private List<Teacher> teachers;
    private List<Group> groups = new ArrayList<>();

    public Subjects(String name, String code, List<Teacher> teachers) {
        this.name = name;
        this.code = code;
        this.teachers = teachers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public void addGroup(Group group) {
        this.groups.add(group);
    }
}