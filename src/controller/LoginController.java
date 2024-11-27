package controller;

import model.LoginModel;
import view.Admin;
import view.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private Login loginView;
    private LoginModel modelView;

    public LoginController(LoginModel modelView, Login loginView){
        this.modelView = modelView;
        this.loginView = loginView;

        loginView.getBtnLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
    }
    public void handleLogin(){
        String role = loginView.getSelectedRole();
        String user = loginView.getUsername();
        String password = loginView.getPassword();

        boolean isValid = modelView.validateCredentials(role, user, password);
        if(isValid){
            System.out.println("Login Successful!");
            if ("Admin".equals(role)) {
                //frame.dispose(); // Close the login frame
                //AdminDashboard.main(new String[]{}); // Open AdminDashboard
                Admin admin = new Admin();
                 new AdminController(admin);
            }
            //todo: ->
//            Admin admin = new Admin();
//            new AdminController(admin);
        }else{
            System.out.println("Invalid!");
        }
    }
}
