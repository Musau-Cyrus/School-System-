package controller;

import model.Class;
import model.Data.ClassDAO;
import view.Admin;
import view.ClassesView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClassController {
    private ClassesView classView;
    private ClassDAO classDAO;
    private Admin adminView;

    public ClassController(ClassesView classView, Admin adminView) {
        this.classView = classView;
        this.classDAO = new ClassDAO();
        this.adminView = adminView;

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