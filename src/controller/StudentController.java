package controller;

import model.Data.StudentDAO;
import model.StudentModel;
import view.Admin;
import view.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentController {
    private Student studentView;
    private StudentDAO studentDAO;
    private Admin adminView;
    private boolean isUpdateMode = false;

    public StudentController(Student studentView, Admin adminView) {
        this.studentView = studentView;
        this.studentDAO = new StudentDAO();
        this.adminView = adminView;
        this.studentView.addButtonListener(new ButtonActionListener());
        loadStudents();
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
                    handleEdit();
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
        String id = studentView.getId();
        String name = studentView.getName();
        String age = studentView.getAge();
        String studentClass = studentView.getStudentClass();
        String contactInfo = studentView.getContactInfo();

        if (id.isEmpty() || name.isEmpty() || age.isEmpty() || studentClass.isEmpty() || contactInfo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: All fields must be filled out.");
            return;
        }

        studentDAO.insertStudent(id, name, age, studentClass, contactInfo);
        studentView.populateTable(studentDAO.getAllStudents());
        JOptionPane.showMessageDialog(null, "Student inserted successfully!");
        loadStudents(); // Refreshes the table after saving
        studentView.clearFields(); // Clears textFields after saving
    }

    private void handleDelete() {
        int selectedRow = studentView.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Error: No student selected.");
            return;
        }

        String studentId = studentView.getStudentIdAt(selectedRow);
        studentDAO.deleteStudent(studentId);
        JOptionPane.showMessageDialog(null, "Student deleted successfully!");
        loadStudents(); // Refreshes the table after deleting
    }

    private void handleBack() {
        adminView.getCardLayout().show(adminView.getMainPanel(), "AdminContents");
    }

    private void handleEdit() {
        if (!isUpdateMode) {
            int selectedRow = studentView.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Error: No student selected.");
                return;
            }

            // Populate text fields with selected row data
            studentView.setId(studentView.getStudentIdAt(selectedRow));
            studentView.setName(studentView.getNameAt(selectedRow));
            studentView.setAge(studentView.getAgeAt(selectedRow));
            studentView.setStudentClass(studentView.getClassAt(selectedRow));
            studentView.setContactInfo(studentView.getContactInfoAt(selectedRow));

            isUpdateMode = true; // Set update mode to true
        } else {
            // Save the updated data
            String id = studentView.getId();
            String name = studentView.getName();
            String age = studentView.getAge();
            String studentClass = studentView.getStudentClass();
            String contactInfo = studentView.getContactInfo();

            if (id.isEmpty() || name.isEmpty() || age.isEmpty() || studentClass.isEmpty() || contactInfo.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: All fields must be filled out.");
                return;
            }

            studentDAO.updateStudent(id, name, age, studentClass, contactInfo);
            studentView.populateTable(studentDAO.getAllStudents());
            JOptionPane.showMessageDialog(null, "Student updated successfully!");
            loadStudents(); // Refreshes the table after updating
            studentView.clearFields(); // Clears textFields after updating
            isUpdateMode = false; // Reset update mode
        }
    }

    private void loadStudents() {
        List<StudentModel> students = studentDAO.getAllStudents();
        studentView.populateTable(students);
    }
}