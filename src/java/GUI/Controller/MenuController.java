package GUI.Controller;

import BusinessLogic.DeliveryService;
import GUI.View.Employee;
import GUI.View.LogIn;
import GUI.View.Register;

import javax.swing.*;

public class MenuController {
    private LogIn logIn;
    DeliveryService deliveryService;

    public MenuController() {
        logIn = new LogIn();
        logIn.setVisible(true);
        deliveryService = new DeliveryService();
        listen();
    }

    public void listen() {
        logIn.getLogin().addActionListener(e -> {
            String username = logIn.getUsername().getText();
            String password = logIn.getPassword().getText();
            if (username.equals("") || password.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter correct username and password");
                return;
            }
            if (deliveryService.login(username, password) == -1) {
                JOptionPane.showMessageDialog(null, "Please enter correct username and password");
                return;
            }
            if (deliveryService.login(username, password) == 0) {
                JOptionPane.showMessageDialog(null, "Admin login successful");
                logIn.setVisible(false);
                logIn.dispose();
                new AdminController();
                return;
            }
            if (deliveryService.login(username, password) == 1) {
                JOptionPane.showMessageDialog(null, "Employee login successful");
                logIn.setVisible(false);
                logIn.dispose();
                new Employee(deliveryService);
                return;
            }
            if (deliveryService.login(username, password) == 2) {
                JOptionPane.showMessageDialog(null, "Customer login successful");
                logIn.setVisible(false);
                logIn.dispose();
                new CustomerController(username);
                return;
            }
        });
        logIn.getRegister().addActionListener(e -> {
            logIn.setVisible(false);
            logIn.dispose();
            Register register = new Register();
            register.getRegister().addActionListener(e1 -> {
                String username = register.getUsername().getText();
                String password = register.getPassword().getText();
                String confirmPassword = register.getConfirmPassword().getText();
                if (username.equals("") || password.equals("") || confirmPassword.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter correct username and password");
                    return;
                }
                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Passwords don't match");
                    return;
                }
                if (deliveryService.getUser(username) != null) {
                    JOptionPane.showMessageDialog(null, "Username already exists");
                    return;
                }
                deliveryService.register(username, password);
                JOptionPane.showMessageDialog(null, "Registered successfully");
                register.setVisible(false);
                register.dispose();
                MenuController menuController = new MenuController();
            });
        });
    }

}
