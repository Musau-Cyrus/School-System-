package view;

import controller.StudentController;
import model.Data.StudentDAO;
import model.StudentModel;
import util.DataFilter;
import util.CustomTextField;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.List;


public class Student extends JPanel {
    private JTable studentTable;
    private JScrollPane scrollPane;
    private TableRowSorter<StudentTableModel> rowSorter;
    private JPanel formPanel, buttonPanel, searchPanel;
    private JTextField idField, nameField, ageField, contactField;
    private JButton save, update, delete, back;
    private StudentTableModel studentTableModel;
    private SearchBar searchBar;
    private JComboBox<String> classComboBox;
    private CustomTextField searchField;
    private String idPlaceholder, namePlaceholder, agePlaceholder, contactPlaceholder, prefix;

    public Student() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        idPlaceholder = "Enter Student ID";
        namePlaceholder = "Enter Student Name";
        agePlaceholder = "Enter Student Age";
        formPanel.add(new JLabel("ID:"));
        idField = new CustomTextField(20, 15,15);
        idField.setText(idPlaceholder);
        idField.setForeground(Color.GRAY);
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
                if (idField.getText().equals(idPlaceholder)) {
                    idField.setText("");
                    idField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (idField.getText().isEmpty()) {
                    idField.setText(idPlaceholder);
                    idField.setForeground(Color.GRAY);
                    KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
                }
            }
        });
        idField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (idField.getText().equals(idPlaceholder)) {
                    idField.setText("");
                    idField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (idField.getText().isEmpty()) {
                    idField.setText(idPlaceholder);
                    idField.setForeground(Color.GRAY);
                }
            }
        });
        formPanel.add(idField);

        formPanel.add(new JLabel("Name:"));
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
                if (nameField.getText().equals(namePlaceholder)) {
                    nameField.setText("");
                    nameField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (nameField.getText().isEmpty()) {
                    nameField.setText(namePlaceholder);
                    nameField.setForeground(Color.GRAY);
                    KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
                }
            }
        });
        nameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nameField.getText().equals(namePlaceholder)) {
                    nameField.setText("");
                    nameField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nameField.getText().isEmpty()) {
                    nameField.setText(namePlaceholder);
                    nameField.setForeground(Color.GRAY);
                }
            }
        });
        formPanel.add(nameField);

        formPanel.add(new JLabel("Age:"));
        ageField = new CustomTextField(20, 15,15);
        ageField.setText(agePlaceholder);
        ageField.setForeground(Color.GRAY);
        ageField.addMouseListener(new MouseListener() {
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
                if (ageField.getText().equals(agePlaceholder)) {
                    ageField.setText("");
                    ageField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (ageField.getText().isEmpty()) {
                    ageField.setText(agePlaceholder);
                    ageField.setForeground(Color.GRAY);
                    KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
                }
            }
        });
        ageField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (ageField.getText().equals(agePlaceholder)) {
                    ageField.setText("");
                    ageField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (ageField.getText().isEmpty()) {
                    ageField.setText(agePlaceholder);
                    ageField.setForeground(Color.GRAY);
                }
            }
        });
        formPanel.add(ageField);

        formPanel.add(new JLabel("Class:"));
        classComboBox = new JComboBox<>();
        formPanel.add(classComboBox);

        formPanel.add(new JLabel("Contact Info:"));
        contactField = new CustomTextField(20, 15, 15);
        prefix = "+254";
        contactPlaceholder = "eg...798201461";
        contactField.setText(prefix + " " + contactPlaceholder);
        contactField.setForeground(Color.GRAY);

        contactField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                if (contactField.getText().equals(prefix + " " + contactPlaceholder)) {
                    contactField.setText(prefix + " ");
                    contactField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (contactField.getText().equals(prefix + " ")) {
                    contactField.setText(prefix + " " + contactPlaceholder);
                    contactField.setForeground(Color.GRAY);
                    KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
                }
            }
        });

        contactField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (contactField.getText().equals(prefix + " " + contactPlaceholder)) {
                    contactField.setText(prefix + " ");
                    contactField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (contactField.getText().equals(prefix + " ")) {
                    contactField.setText(prefix + " " + contactPlaceholder);
                    contactField.setForeground(Color.GRAY);
                }
            }
        });
        contactField.getDocument().addDocumentListener(new DocumentListener() {
            private boolean isAdjusting = false;

            @Override
            public void insertUpdate(DocumentEvent e) {
                enforcePrefix();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                enforcePrefix();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                enforcePrefix();
            }

            private void enforcePrefix() {
                if (isAdjusting) return;

                SwingUtilities.invokeLater(() -> {
                    String text = contactField.getText();
                    if (!text.startsWith(prefix)) {
                        isAdjusting = true;
                        contactField.setText(prefix + " ");
                        contactField.setCaretPosition(contactField.getText().length());
                        isAdjusting = false;
                    }
                });
            }
        });
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

        studentTableModel = new StudentTableModel();
        studentTable = new JTable(studentTableModel);
        rowSorter = new TableRowSorter<>(studentTableModel);
        studentTable.setRowSorter(rowSorter);
        scrollPane = new JScrollPane(studentTable);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        add(scrollPane, BorderLayout.SOUTH);

        searchPanel = new JPanel(new BorderLayout());
        searchBar  = new SearchBar(20);

        // Use DocumentListener to monitor text changes in the search bar
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
                if (query.isEmpty()) {
                    // When the search bar is empty, show all rows by resetting the filter
                    rowSorter.setRowFilter(null);
                } else {
                    // Filter rows based on the input query (case-insensitive search)
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
                }
            }
        });

        searchPanel.add(buttonPanel, BorderLayout.NORTH);
        searchPanel.add(searchBar, BorderLayout.SOUTH);

        add(searchPanel, BorderLayout.CENTER);
        add(formPanel, BorderLayout.NORTH);

        populateClassComboBox();
    }

    public void initializeController(Admin adminView) {
        new StudentController(this, adminView);
    }

    public String getId() {
        return idField.getText();
    }

    public String getName() {
        return nameField.getText();
    }

    public String getAge() {
        return ageField.getText();
    }


    public String getStudentClass() {
        return (String) classComboBox.getSelectedItem();
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

    public void populateTable(List<StudentModel> students) {
        studentTableModel.setStudents(students);
    }

    public int getSelectedRow() {
        return studentTable.getSelectedRow();
    }

    public String getStudentIdAt(int row) {
        return (String) studentTable.getValueAt(row, 0);
    }
    public void populateClassComboBox() {
        StudentDAO studentDAO = new StudentDAO();
        List<String> classes = studentDAO.getAllClasses();
        classComboBox.removeAllItems(); // Clear existing items
        for (String className : classes) {
            classComboBox.addItem(className);
        }
    }

    private static class StudentTableModel extends AbstractTableModel {
        private final String[] columnNames = {"ID", "Name", "Age", "Class", "Contact Info"};
        private List<StudentModel> students;

        public StudentTableModel() {
            this.students = List.of(); // Initialize with an empty list
        }

        public void setStudents(List<StudentModel> students) {
            this.students = students;
            fireTableDataChanged(); // Notify the table to refresh
        }

        @Override
        public int getRowCount() {
            return students.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            StudentModel student = students.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return student.getId();
                case 1:
                    return student.getName();
                case 2:
                    return student.getAge();
                case 3:
                    return student.getStudentClass();
                case 4:
                    return student.getContactInfo();
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
        idField.setText(idPlaceholder);
        idField.setForeground(Color.GRAY);
        nameField.setText(namePlaceholder);
        nameField.setForeground(Color.GRAY);
        ageField.setText(agePlaceholder);
        ageField.setForeground(Color.GRAY);
        classComboBox.setSelectedItem(-1);
        contactField.setText(prefix + " " + contactPlaceholder);
        contactField.setForeground(Color.GRAY);

    }
}
