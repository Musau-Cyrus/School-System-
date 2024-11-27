package controller;

import view.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminController {
    private Admin adminview;

    public AdminController(Admin adminview) {
        this.adminview = adminview;
        this.adminview.addButtonListener(new ButtonActionListener());
    }

    class ButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            switch (actionCommand) {
                case "STUDENT":
                    handleStudent();
                    break;
                case "CLASSES":
                    // method to handle class registration
                    handleClasses();
                    break;
                case "TEACHER":
                    // method to handle teacher registration
                    handleTeacher();
                    break;
                case "CALENDER":
                    // display calendar
                    handleCalender();
                    break;
                default:
                    System.out.println("Unknown");
            }
        }

        private void handleStudent() {
            adminview.getCardLayout().show(adminview.getMainPanel(), "Student");
        }
        private void handleClasses() {
            // method to handle class registration
            adminview.getCardLayout().show(adminview.getMainPanel(), "Classes");
        }
        private void handleTeacher() {
            // method to handle teacher registration
            adminview.getCardLayout().show(adminview.getMainPanel(), "Teacher");
        }
        private void handleCalender() {
            adminview.getCardLayout().show(adminview.getMainPanel(), "Calender");
        }
    }
}