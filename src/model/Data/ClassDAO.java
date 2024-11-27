package model.Data;

import model.Class;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/school";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public void insertClass(String classId, String className, String classTeacher) {
        String query = "INSERT INTO class (`class_id`, `class_name`, `class_teacher`) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, classId);
            statement.setString(2, className);
            statement.setString(3, classTeacher);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Class Registered: " + className + ", ID: " + classId + ", Teacher Incharge: " + classTeacher);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Class> getAllClasses() {
        List<Class> classes = new ArrayList<>();
        String query = "SELECT * FROM class";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String classId = resultSet.getString("class_id");
                String className = resultSet.getString("class_name");
                String classTeacher = resultSet.getString("class_teacher");
                classes.add(new Class(classId, className, classTeacher));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }


        public List<String> getAllClassNames() {
            List<String> classNames = new ArrayList<>();
            String query = "SELECT class_name FROM class";
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement statement = conn.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    classNames.add(resultSet.getString("class_name"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return classNames;
        }

    }
