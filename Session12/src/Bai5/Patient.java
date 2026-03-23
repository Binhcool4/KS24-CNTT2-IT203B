package Bai5;

public class Patient {
    private int id;
    private String name;
    private int age;
    private String department;

    public Patient(int id, String name, int age, String department) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
    }
    @Override
    public String toString() {
        return String.format("| %-4d | %-20s | %-4d | %-15s |", id, name, age, department);
    }
}