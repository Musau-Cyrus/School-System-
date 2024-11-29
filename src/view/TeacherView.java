package view;

import controller.TeacherController;
import model.Data.StudentDAO;
import model.Data.TeacherDAO;
import model.TeacherModel;
import util.CustomTextField;
import util.DataFilter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class TeacherView extends JPanel {
    private JTable teacherTable;
    private JScrollPane scrollPane;
    private TableRowSorter<TeacherTableModel> rowSorter;
    private JPanel formPanel, buttonPanel, searchPanel;
    private JTextField idField, nameField, contactField;
    private JButton save, update, delete, back;
    private TeacherTableModel teacherTableModel;
    private SearchBar searchBar;
    private JComboBox<String> classInchargeComboBox;
    private String idPlaceholder, namePlaceholder, contactPlaceholder, prefix;

    public TeacherView() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        namePlaceholder = "Enter Name";
        idPlaceholder = "Enter ID";
        contactPlaceholder = "eg...798201461";

        formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

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


        formPanel.add(new JLabel("Class Incharge:"));
        classInchargeComboBox = new JComboBox<>();
        formPanel.add(classInchargeComboBox);

        formPanel.add(new JLabel("Contact Info:"));
        contactField = new CustomTextField(20, 15,15);
        prefix = "+254";
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
        populateclassInchargeComboBox();

    }
    private void populateclassInchargeComboBox() {
        StudentDAO studentDAO = new StudentDAO();
        List<String> classes = studentDAO.getAllClasses();
        classInchargeComboBox.removeAllItems(); // Clear existing items
        classInchargeComboBox.addItem("None"); // Add "None" option
        for (String className : classes) {
            classInchargeComboBox.addItem(className);
        }
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
        private final String[] columnNames = {"ID", "Name", "Contact Info", "Class Incharge"};
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
        classInchargeComboBox.setSelectedItem(-1);
        contactField.setText("");
    }
}