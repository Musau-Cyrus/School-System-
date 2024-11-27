package view;

import controller.ClassController;
import model.Class;
import model.Data.ClassDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClassesView extends JPanel {
    private SearchBar searchBar;
    private TextField nameField, idField, teacherIncharge;
    private JButton save, update, delete, back;
    private JPanel formPanel, buttonPanel, searchPanel;
    private JTable classTable;
    private DefaultTableModel tableModel;

    public ClassesView() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Class ID:"));
        idField = new TextField();
        formPanel.add(idField);

        formPanel.add(new JLabel("Class Name:"));
        nameField = new TextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Teacher InCharge:"));
        teacherIncharge = new TextField();
        formPanel.add(teacherIncharge);

        buttonPanel = new JPanel(new FlowLayout());
        save = new JButton("SAVE");
        buttonPanel.add(save);
        update = new JButton("UPDATE");
        buttonPanel.add(update);
        delete = new JButton("DELETE");
        buttonPanel.add(delete);
        back = new JButton("BACK");
        buttonPanel.add(back);

        searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBackground(Color.WHITE);

        searchBar = new SearchBar(20);
        searchPanel.add(searchBar, BorderLayout.SOUTH);
        searchPanel.add(buttonPanel, BorderLayout.NORTH);

        add(formPanel, BorderLayout.NORTH);
        add(searchPanel, BorderLayout.CENTER);

        tableModel = new DefaultTableModel(new String[]{"Class ID", "Class Name", "Class Teacher"}, 0);
        classTable = new JTable(tableModel);
        add(new JScrollPane(classTable), BorderLayout.SOUTH);
    }

    public void initializeController(Admin adminView) {
        new ClassController(this, adminView);
    }

    public String getClassName() {
        return nameField.getText();
    }

    public String getId() {
        return idField.getText();
    }

    public String getTeacher() {
        return teacherIncharge.getText();
    }

    public JButton getSaveButton() {
        return save;
    }

    public JButton getBackButton() {
        return back;
    }

    public void populateTable(List<Class> classes) {
        tableModel.setRowCount(0); // Clear existing data
        for (Class cls : classes) {
            tableModel.addRow(new Object[]{cls.getClassId(), cls.getClassName(), cls.getClassTeacher()});
        }
    }

    public void clearFields() {
        idField.setText("");
        nameField.setText("");
        teacherIncharge.setText("");
    }
}