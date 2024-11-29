package controller;

import model.Class;
import model.Data.ClassDAO;
import model.Data.TeacherDAO;
import view.Admin;
import view.ClassesView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ClassController {
    private ClassesView classView;
    private ClassDAO classDAO;
    private Admin adminView;
    private TeacherDAO teacherDAO;
    private TeacherController teacherController;

    public ClassController(ClassesView classView, Admin adminView) {
        this.classView = classView;
        this.classDAO = new ClassDAO();
        this.adminView = adminView;
        this.teacherController = teacherController;

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "");
            this.teacherDAO = new TeacherDAO(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Unable to connect to the database.");
            return;
        }

        this.classView.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSave();
            }
        });

        this.classView.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBack();
            }
        });

        loadClasses();
    }

    private void handleSave() {
        String classId = classView.getId();
        String className = classView.getClassName();
        String classTeacher = classView.getTeacher();

        if (classId.isEmpty() || className.isEmpty() || classTeacher.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all fields.");
            return;
        }

        classDAO.insertClass(classId, className, classTeacher);

        // Update the teacher's class in the teacher table
        try {
            teacherDAO.updateTeacherClass(classTeacher, classId);
            teacherController.loadTeachers(); // Refresh the teacher table
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Unable to update teacher's class.");
        }
        loadClasses(); // Refreshes the table after saving
        classView.clearFields(); // Clears the form fields after saving
    }

    private void handleBack() {
        adminView.getCardLayout().show(adminView.getMainPanel(), "AdminContents");
    }

    private void loadClasses() {
        List<Class> classes = classDAO.getAllClasses();
        classView.populateTable(classes);
    }
}