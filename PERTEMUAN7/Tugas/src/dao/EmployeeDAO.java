package PERTEMUAN7.Tugas.src.dao;

import PERTEMUAN7.Tugas.src.db.MySqlConnection;
import PERTEMUAN7.Tugas.src.model.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employees";
        try (Connection conn = MySqlConnection.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Employee employee = new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("position"),
                    rs.getString("department"),
                    rs.getString("email"),
                    rs.getBigDecimal("salary")
                );
                employees.add(employee);
            }
        } catch (SQLException e) {
            System.out.println("Error saat menyiapkan statement atau mengeksekusi query");
            System.out.println("Error: " + e.getMessage());
            System.out.println("SQL State: " + e.getSQLState());
            e.printStackTrace();
        }
        return employees;
    }

    public void addEmployee(Employee employee) {
        String query = "INSERT INTO employees (name, position, department, email, salary) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = MySqlConnection.getInstance().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getPosition());
            pstmt.setString(3, employee.getDepartment());
            pstmt.setString(4, employee.getEmail());
            pstmt.setBigDecimal(5, employee.getSalary());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saat menyiapkan statement atau mengeksekusi query");
            System.out.println("Error: " + e.getMessage());
            System.out.println("SQL State: " + e.getSQLState());
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int id) {
        String query = "DELETE FROM employees WHERE id = ?";
        try (Connection conn = MySqlConnection.getInstance().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saat menyiapkan statement atau mengeksekusi query");
            System.out.println("Error: " + e.getMessage());
            System.out.println("SQL State: " + e.getSQLState());
            e.printStackTrace();
        }
    }
}
