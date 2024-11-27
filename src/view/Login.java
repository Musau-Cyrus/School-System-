package view;

import javax.swing.*;
import java.awt.*;

public class Login  {
    private  JFrame frame;
    private JPanel login;
    private JButton btnLogin;
    private JTextField  usernameTextField;
    private JPasswordField pass;
    private JLabel userType, username, password;
    private JComboBox<String> roleComboBox;

    public Login(){
        frame = new JFrame();
        frame.setTitle("Login");
        frame.setSize(400, 400);
        login = new JPanel();
        login.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0,0,10,10);

        userType = new JLabel("ROLE");
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.ipadx = 10;
        gbc.ipady = 10;
        login.add(userType, gbc);
        String[] roles = {"--Select Role--", "Teacher", "Admin"};
        roleComboBox = new JComboBox<>(roles);
        gbc.gridy = 0;
        gbc.gridx = 1;
        gbc.ipadx = 10;
        gbc.ipady = 10;
        login.add(roleComboBox, gbc);

        username = new JLabel("USERNAME");
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.ipadx = 10;
        gbc.ipady = 10;
        login.add(username, gbc);
        usernameTextField = new JTextField();
        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.ipadx = 10;
        gbc.ipady = 10;
        login.add(usernameTextField, gbc);

        password = new JLabel("PASSWORD");
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.ipadx = 10;
        gbc.ipady = 10;
        login.add(password, gbc);
        pass = new JPasswordField();
        gbc.gridy = 2;
        gbc.gridx = 1;
        gbc.ipadx = 10;
        gbc.ipady = 10;
        login.add(pass, gbc);

        btnLogin = new JButton("LOGIN");
        gbc.gridy = 3;
        gbc.gridx = 1;
        gbc.ipadx = 10;
        gbc.ipady = 10;
        login.add(btnLogin, gbc);





        frame.add(login);
        frame.setVisible(true);
    }
    //Getters
    public String getSelectedRole(){
        return (String) roleComboBox.getSelectedItem();
    }

    public String getUsername(){
        return usernameTextField.getText();
    }

    public String getPassword(){
        return new String(pass.getPassword());
    }

    public JButton getBtnLogin(){
        return btnLogin;
    }
}
