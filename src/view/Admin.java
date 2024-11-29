package view;

import util.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Admin extends JFrame {
    private final JPanel sidebar, mainpanel, contents, adminContents;
    private JButton student, classes, teacher, calender, btnStudent, btnTeacher, btnClass, btnCalender, logout;
    private CardLayout cardLayout;

    public Admin() {
        setTitle("Admin Dashboard");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Color myrtleGreen = new Color( 79, 151, 163);

        sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(75, 156, 211));
        sidebar.setPreferredSize(new Dimension(200, getHeight()));

        student = new CustomButton("STUDENT", myrtleGreen, Color.WHITE);
       // student.setText("STUDENT");
        //student.setPreferredSize(new Dimension(50, 50));
        //student.setBorderPainted(false);
        setButtonFullWidth(student, 200, 20);
        student.setFocusPainted(false);
        student.setToolTipText("Click to Register new Student");
        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(student);

        classes = new CustomButton("CLASSES", myrtleGreen, Color.WHITE);
        //classes.setText("CLASSES");
        //classes.setPreferredSize(new Dimension(50, 50));
        setButtonFullWidth(classes, 200, 20);
        classes.setFocusPainted(false);
        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(classes);

        teacher = new CustomButton("TEACHER", myrtleGreen, Color.WHITE);
        //teacher.setText("TEACHER");
        //teacher.setPreferredSize(new Dimension(50, 50));
        setButtonFullWidth(teacher, 200, 20);
        teacher.setFocusPainted(false);
        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(teacher);

        calender = new CustomButton("CALENDER", myrtleGreen, Color.WHITE);
        //calender.setText("CALENDER");
        //calender.setPreferredSize(new Dimension(50, 50));
        setButtonFullWidth(calender, 200, 20);
        calender.setFocusPainted(false);
        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(calender);


        logout = new CustomButton("LOGOUT", myrtleGreen, Color.WHITE);
        //logout.setText("LOGOUT");
        logout.setBackground(Color.MAGENTA);
        //logout.setPreferredSize(new Dimension(50, 50));
        logout.setFocusPainted(false);
        sidebar.add(Box.createVerticalStrut(50));
        setButtonFullWidth(logout, 200, 20);
        sidebar.add(logout);
        //sidebar.add(Box.createHorizontalGlue());
        sidebar.add(Box.createHorizontalStrut(50));

        cardLayout = new CardLayout();
        mainpanel = new JPanel(cardLayout);
        mainpanel.setBackground(Color.WHITE);

        contents = new JPanel();
        contents.setBackground(Color.WHITE);
        contents.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        ImageIcon originalIcon = new ImageIcon("C:/Users/Administrator/IdeaProjects/School Management System/src/resources/images/profile.png");
        ImageIcon iconStudent = resizeIcon(originalIcon, 75, 75);
        btnStudent = createStyledButton("STUDENT", iconStudent);
        contents.add(btnStudent);

        ImageIcon originalIcon1 = new ImageIcon("C:/Users/Administrator/IdeaProjects/School Management System/src/resources/images/teacher.png");
        ImageIcon iconTeacher = resizeIcon(originalIcon1, 75, 75);
        btnTeacher = createStyledButton("TEACHER", iconTeacher);
        contents.add(btnTeacher);

        ImageIcon originalIcon2 = new ImageIcon("C:/Users/Administrator/IdeaProjects/School Management System/src/resources/images/class.png");
        ImageIcon iconClasses = resizeIcon(originalIcon2, 75, 75);
        btnClass = createStyledButton("CLASSES", iconClasses);
        contents.add(btnClass);

        ImageIcon originalIcon3 = new ImageIcon("C:/Users/Administrator/IdeaProjects/School Management System/src/resources/images/calender.png");
        ImageIcon iconCalender = resizeIcon(originalIcon3, 75, 75);
        btnCalender = createStyledButton("CALENDER", iconCalender);
        contents.add(btnCalender);

        adminContents = new JPanel(new BorderLayout());
        adminContents.add(contents);

        mainpanel.add(adminContents, "AdminContents");
        Student studentView = new Student();
        studentView.initializeController(this);

        ClassesView classesView = new ClassesView();
        classesView.initializeController(this);

        TeacherView teacherView = new TeacherView();
        teacherView.initializeController(this);

        mainpanel.add(studentView, "Student");
        mainpanel.add(classesView, "Classes");
        mainpanel.add(new Calender(), "Calender");
        mainpanel.add(teacherView, "Teacher");

        add(sidebar, BorderLayout.WEST);
        add(mainpanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void addButtonListener(ActionListener listener) {
        btnStudent.setActionCommand("STUDENT");
        btnStudent.addActionListener(listener);
        student.setActionCommand("STUDENT");
        student.addActionListener(listener);

        btnClass.setActionCommand("CLASSES");
        btnClass.addActionListener(listener);
        classes.setActionCommand("CLASSES");
        classes.addActionListener(listener);

        btnTeacher.setActionCommand("TEACHER");
        btnTeacher.addActionListener(listener);
        teacher.setActionCommand("TEACHER");
        teacher.addActionListener(listener);

        btnCalender.setActionCommand("CALENDER");
        btnCalender.addActionListener(listener);
        calender.setActionCommand("CALENDER");
        calender.addActionListener(listener);
    }

    private static ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    private static JButton createStyledButton(String text, ImageIcon icon) {
        JButton button = new JButton(text, icon);
        styleButton(button);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        return button;
    }

    private static void styleButton(JButton button) {
        button.setPreferredSize(new Dimension(120, 120));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBackground(new Color(255, 255, 255));
        button.setForeground(Color.BLACK);
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

//    public void setCardLayout(CardLayout cardLayout) {
//        this.cardLayout = cardLayout;
//    }

    public Container getMainPanel() {
        return mainpanel;
    }
    private void setButtonFullWidth(JButton button, int width, int height) {
        button.setMaximumSize(new Dimension(width, height));
        button.setPreferredSize(new Dimension(width, height));
        button.setMinimumSize(new Dimension(width, height));
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Centers the button horizontally in BoxLayout

        button.setOpaque(true);
        button.setBackground(button.getParent() != null ? button.getParent().getBackground() : new Color(75, 156, 211)); // Default to gray if no parent
        button.setForeground(Color.WHITE); // Keep text white for contrast
        button.setBorderPainted(false); // Remove borders for a cleaner look
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Consistent font
    }

}