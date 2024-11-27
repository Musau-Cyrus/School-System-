package view;

import controller.TeacherController;
import model.Data.TeacherDAO;
import model.TeacherModel;
import util.DataFilter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TeacherView extends JPanel {
    private JTable teacherTable;
    private JScrollPane scrollPane;
    private TableRowSorter<TeacherTableModel> rowSorter;
    private JPanel formPanel, buttonPanel, searchPanel;
    private JTextField idField, nameField, subjectField, contactField;
    private JButton save, update, delete, back;
    private TeacherTableModel teacherTableModel;
    private SearchBar searchBar;
    private JComboBox<String> classInchargeComboBox;

    public TeacherView() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        formPanel.add(idField);

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);


        formPanel.add(new JLabel("Class Incharge:"));
        classInchargeComboBox = new JComboBox<>();
        formPanel.add(classInchargeComboBox);

        formPanel.add(new JLabel("Contact Info:"));
        contactField = new JTextField();
        formPanel.add(contactField);

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
        searchBar = new SearchBar(20);
        searchBar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String query = searchBar.getText();
                DataFilter.filterTable(teacherTable, query);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String query = searchBar.getText();
                DataFilter.filterTable(teacherTable, query);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String query = searchBar.getText();
                DataFilter.filterTable(teacherTable, query);

            }
        });

        searchPanel.add(buttonPanel, BorderLayout.NORTH);
        searchPanel.add(searchBar, BorderLayout.SOUTH);


        teacherTableModel = new TeacherTableModel();
        teacherTable = new JTable(teacherTableModel);
        rowSorter = new TableRowSorter<>(teacherTableModel);
        teacherTable.setRowSorter(rowSorter);
        scrollPane = new JScrollPane(teacherTable);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        add(scrollPane, BorderLayout.SOUTH);

        add(formPanel, BorderLayout.NORTH);
        add(searchPanel, BorderLayout.CENTER);

    }

    public void initializeController(Admin adminView) {
        new TeacherController(this, adminView);
    }
    public String getId() {
        return idField.getText();
    }

    public String getName() {
        return nameField.getText();
    }


    public String getClassIncharge() {
        return (String) classInchargeComboBox.getSelectedItem();
    }

    public String getContactInfo() {
        return contactField.getText();
    }

    public void addButtonListener(ActionListener listener) {
        save.setActionCommand("SAVE");
        save.addActionListener(listener);

        update.setActionCommand("UPDATE");
        update.addActionListener(listener);

        delete.setActionCommand("DELETE");
        delete.addActionListener(listener);

        back.setActionCommand("BACK");
        back.addActionListener(listener);
    }


    public int getSelectedRow() {
        return teacherTable.getSelectedRow();
    }

    public String getTeacherIdAt(int row) {
        return (String) teacherTable.getValueAt(row, 0);
    }
    public void populateTable(List<TeacherModel> teachers) {
        teacherTableModel.setTeachers(teachers);
    }

    private static class TeacherTableModel extends AbstractTableModel {
        private final String[] columnNames = {"ID", "Name", "Subject", "Contact Info"};
        private List<TeacherModel> teachers;

        public TeacherTableModel() {
            this.teachers = List.of(); // Initialize with an empty list
        }

        public void setTeachers(List<TeacherModel> teachers) {
            this.teachers = teachers;
            fireTableDataChanged();
        }

        @Override
        public int getRowCount() {
            return teachers.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            TeacherModel teacher = teachers.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return teacher.getId();
                case 1:
                    return teacher.getName();
                case 2:
                    return teacher.getSubject();
                case 3:
                    return teacher.getContactInfo();
                default:
                    return null;
            }
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }
    }
    public void clearFields() {
        idField.setText("");
        nameField.setText("");
        subjectField.setText("");
        contactField.setText("");
    }
}