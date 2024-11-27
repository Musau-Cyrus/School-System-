import controller.AdminController;
import controller.LoginController;
import model.LoginModel;
import util.DatabaseConnection;
import view.Admin;
import view.Login;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {


        Admin admin = new Admin();

        new AdminController(admin);
//        LoginModel model = new LoginModel();
//        Login login = new Login();
//        new LoginController(model, login);

        DatabaseConnection dbConnection = new DatabaseConnection();
        dbConnection.initializeDatabase();
    }
}