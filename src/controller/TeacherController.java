package controller;

import model.Data.TeacherDAO;
import model.TeacherModel;
import view.Admin;
import view.TeacherView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class TeacherController {
    private TeacherView teacherView;
    private TeacherDAO teacherDAO;
    private Admin adminView;

    public TeacherController(TeacherView teacherView, Admin adminView) {
        this.teacherView = teacherView;
        this.adminView = adminView;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "");
            this.teacherDAO = new TeacherDAO(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Unable to connect to the database.");
            return;
        }

        this.teacherView.addButtonListener(new ButtonActionListener());
        loadTeachers();
    }

    class ButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            switch (actionCommand) {
                case "SAVE":
                    handleSave();
                    break;
                case "UPDATE":
                    //handleEdit();
                    break;
                case "DELETE":
                    handleDelete();
                    break;
                case "BACK":
                    handleBack();
                    break;
                default:
                    System.out.println("Unknown");
            }
        }
    }

    private void handleSave() {
        String id = teacherView.getId();
        String name = teacherView.getName();
        String classIncharge = teacherView.getClassIncharge();
        String contactInfo = teacherView.getContactInfo();

        if (id.isEmpty() || name.isEmpty() || contactInfo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: All fields except Class Incharge must be filled out.");
            return;
        }

        // Set classIncharge to null if "None" is selected
        if ("None".equals(classIncharge)) {
            classIncharge = null;
        }

        TeacherModel teacher = new TeacherModel(id, name, classIncharge, contactInfo);

        try {
            teacherDAO.insertTeacher(id, name, classIncharge, contactInfo);
            if (classIncharge != null) {
                teacherDAO.updateTeacherClass(id, classIncharge);
            }
            JOptionPane.showMessageDialog(null, "Teacher inserted successfully!");
            loadTeachers(); // Refreshes the table after saving
            teacherView.clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Unable to insert teacher.");
        }

    }

    private void handleSave1() {
        String id = teacherView.getId();
        String name = teacherView.getName();
        String classIncharge = teacherView.getClassIncharge();
        String contactInfo = teacherView.getContactInfo();

        if (id.isEmpty() || name.isEmpty() || classIncharge.isEmpty() || contactInfo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: All fields must be filled out.");
            return;
        }

        teacherDAO.insertTeacher(id, name, classIncharge, contactInfo);
        JOptionPane.showMessageDialog(null, "Teacher inserted successfully!");
        loadTeachers(); // Refreshes the table after saving
        teacherView.clearFields();
    }

    private void handleDelete() {
        int selectedRow = teacherView.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Error: No teacher selected.");
            return;
        }

        String teacherId = teacherView.getTeacherIdAt(selectedRow);
        teacherDAO.deleteTeacher(teacherId);
        JOptionPane.showMessageDialog(null, "Teacher deleted successfully!");
        loadTeachers(); // Refreshes the table after deleting
    }

    private void handleBack() {
        adminView.getCardLayout().show(adminView.getMainPanel(), "AdminContents");
    }

    public void loadTeachers() {
        List<TeacherModel> teachers = teacherDAO.getAllTeachers();
        teacherView.populateTable(teachers);
    }
}