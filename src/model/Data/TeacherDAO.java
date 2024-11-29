package model.Data;

import model.TeacherModel;
import java.sql.Connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/school";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private Connection connection;

    public TeacherDAO(Connection connection) {
        this.connection = connection;
    }


    public boolean classExists(String classId) throws SQLException {
        String query = "SELECT COUNT(*) FROM class WHERE class_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, classId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public List<String> getAllTeacherNames() throws SQLException {
        List<String> teacherNames = new ArrayList<>();
        String query = "SELECT teacher_name FROM teacher";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                teacherNames.add(resultSet.getString("teacher_name"));
            }
        }
        return teacherNames;
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
                String teacherClass = resultSet.getString("class");
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
    public void updateTeacherClass(String teacherId, String classIncharge) throws SQLException {
        String query = "UPDATE teacher SET class = ? WHERE teacher_name = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, classIncharge);
            statement.setString(2, teacherId);
            statement.executeUpdate();
        }
    }

    public void insertTeacher(String id, String name, String classIncharge, String contactInfo) {
        String query = "INSERT INTO teacher (teacher_id, teacher_name, class, contact_info) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, id);
            statement.setString(2, name);
            statement.setString(3, classIncharge);
            statement.setString(4, contactInfo);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
    }

}
}