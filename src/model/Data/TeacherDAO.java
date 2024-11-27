package model.Data;

import model.TeacherModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/school";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public void insertTeacher(String id, String name, String contactInfo, String classIncharge) {
        if (!classExists(classIncharge)) {
            System.out.println("Error: Class does not exist.");
            return;
        }

        String query = "INSERT INTO teacher (teacher_id, teacher_name, contact_info, teacher_class) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, id);
            statement.setString(2, name);
            statement.setString(3, contactInfo);
            statement.setString(4, classIncharge);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean classExists(String classId) {
        String query = "SELECT 1 FROM class WHERE class_id = ?";
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

    public List<TeacherModel> getAllTeachers() {
        List<TeacherModel> teachers = new ArrayList<>();
        String query = "SELECT * FROM teacher";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String id = resultSet.getString("teacher_id");
                String name = resultSet.getString("teacher_name");
                String contactInfo = resultSet.getString("contact_info");
                String teacherClass = resultSet.getString("teacher_class");
                teachers.add(new TeacherModel(id, name, contactInfo, teacherClass));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    public void deleteTeacher(String teacherId) {
        String query = "DELETE FROM teacher WHERE teacher_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, teacherId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}