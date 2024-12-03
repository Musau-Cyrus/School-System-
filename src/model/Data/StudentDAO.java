package model.Data;

import model.StudentModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/school";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public void insertStudent(String id, String name, String age, String studentClass, String contactInfo) {
        if (!classExists(studentClass)) {
            System.out.println("Error: Class does not exist.");
            return;
        }

        String query = "INSERT INTO student (student_id, student_name, student_age, student_class, contact_info) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, id);
            statement.setString(2, name);
            statement.setString(3, age);
            statement.setString(4, studentClass);
            statement.setString(5, contactInfo);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean classExists(String classId) {
        String query = "SELECT 1 FROM class WHERE class = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, classId);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<StudentModel> getAllStudents() {
        List<StudentModel> students = new ArrayList<>();
        String query = "SELECT * FROM student";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String id = resultSet.getString("student_id");
                String name = resultSet.getString("student_name");
                String age = resultSet.getString("student_age");
                String studentClass = resultSet.getString("student_class");
                String contactInfo = resultSet.getString("contact_info");
                students.add(new StudentModel(id, name, age, studentClass, contactInfo));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public void deleteStudent(String studentId) {
        String query = "DELETE FROM student WHERE student_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, studentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(String id, String name, String age, String studentClass, String contactInfo) {
        String sql = "UPDATE student SET student_name = ?, student_age = ?, student_class = ?, contact_info = ? WHERE student_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, age);
            pstmt.setString(3, studentClass);
            pstmt.setString(4, contactInfo);
            pstmt.setString(5, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    public List<String> getAllClasses() {
        List<String> classes = new ArrayList<>();
        String query = "SELECT DISTINCT class FROM class";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                classes.add(resultSet.getString("class"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }
}