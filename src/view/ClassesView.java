package view;

import controller.ClassController;
import controller.TeacherController;
import model.Class;
import model.Data.TeacherDAO;
import util.CustomTextField;
import util.DataFilter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ClassesView extends JPanel {
    private SearchBar searchBar;
    //private TextField nameField, idField;
    private JComboBox<String> teacherInchargeComboBox;
    private JButton save, update, delete, back;
    private JPanel formPanel, buttonPanel, searchPanel;
    private JTable classTable;
    private DefaultTableModel tableModel;
    private CustomTextField nameField, idField;
    private String idPlaceholder, namePlaceholder;

    public ClassesView() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        idPlaceholder = "Enter Class ID";
        namePlaceholder = "Enter Class Name";

        formPanel.add(new JLabel("Class ID:"));
        idField = new CustomTextField(20, 15,15);
        idField.setText(idPlaceholder);
        idField.setForeground(Color.GRAY);
        idField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(idField.getText().equals(idPlaceholder)) {
                    idField.setText("");
                    idField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(idField.getText().isEmpty()) {
                    idField.setText(idPlaceholder);
                    idField.setForeground(Color.GRAY);
                }
            }
        });
        idField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {


            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(idField.getText().equals(idPlaceholder)) {
                    idField.setText("");
                    idField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(idField.getText().isEmpty()) {
                    idField.setText(idPlaceholder);
                    idField.setForeground(Color.GRAY);
                    KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
                }
            }
        });
        formPanel.add(idField);

        formPanel.add(new JLabel("Class Name:"));
        nameField = new CustomTextField(20, 15,15);
        nameField.setText(namePlaceholder);
        nameField.setForeground(Color.GRAY);
        nameField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(nameField.getText().equals(namePlaceholder)) {
                    nameField.setText("");
                    nameField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(nameField.getText().isEmpty()) {
                    nameField.setText(namePlaceholder);
                    nameField.setForeground(Color.GRAY);
                    KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
                }
            }
        });
        nameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(nameField.getText().equals(namePlaceholder)) {
                    nameField.setText("");
                    nameField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(nameField.getText().isEmpty()) {
                    nameField.setText(namePlaceholder);
                    nameField.setForeground(Color.GRAY);
                }
            }
        });
        formPanel.add(nameField);

        formPanel.add(new JLabel("Teacher InCharge:"));
        teacherInchargeComboBox = new JComboBox<>();
        formPanel.add(teacherInchargeComboBox);

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
        searchBar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable();
            }

            private void filterTable() {
                String query = searchBar.getText().trim();
                DataFilter.filterTable(classTable, query);
            }
        });

        searchPanel.add(searchBar, BorderLayout.SOUTH);
        searchPanel.add(buttonPanel, BorderLayout.NORTH);

        add(formPanel, BorderLayout.NORTH);
        add(searchPanel, BorderLayout.CENTER);

        tableModel = new DefaultTableModel(new String[]{"Class ID", "Class Name", "Class Teacher"}, 0);
        classTable = new JTable(tableModel);
        add(new JScrollPane(classTable), BorderLayout.SOUTH);

        populateTeacherInchargeComboBox();
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
        return (String) teacherInchargeComboBox.getSelectedItem();
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
        idField.setText(idPlaceholder);
        idField.setForeground(Color.GRAY);
        nameField.setText(namePlaceholder);
        nameField.setForeground(Color.GRAY);
        teacherInchargeComboBox.setSelectedItem(null);
    }

    private void populateTeacherInchargeComboBox() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "");
            TeacherDAO teacherDAO = new TeacherDAO(connection);
            List<String> teachers = teacherDAO.getAllTeacherNames();
            teacherInchargeComboBox.removeAllItems(); // Clear existing items
            for (String teacherName : teachers) {
                teacherInchargeComboBox.addItem(teacherName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Unable to load teachers.");
        }
    }
}