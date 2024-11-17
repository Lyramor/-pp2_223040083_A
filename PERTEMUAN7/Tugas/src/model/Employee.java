package PERTEMUAN7.Tugas.src.model;

import java.math.BigDecimal;

public class Employee {
    private int id;
    private String name;
    private String position;
    private String department;
    private String email;
    private BigDecimal salary;

    public Employee(int id, String name, String position, String department, String email, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.department = department;
        this.email = email;
        this.salary = salary;
    }

    public Employee(String name, String position, String department, String email, BigDecimal salary) {
        this.name = name;
        this.position = position;
        this.department = department;
        this.email = email;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", department='" + department + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                '}';
    }
}