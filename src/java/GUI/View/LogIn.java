package GUI.View;

import javax.swing.*;
import java.awt.*;

public class LogIn extends JFrame {
    private JFrame frame = this;
    private JPanel panel;

    private JTextField username;
    private JPasswordField password;
    private JButton login;
    private JButton register;

    public LogIn() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setTitle("Login");
        setLocationRelativeTo(null);
        setResizable(false);
        JLabel title = new JLabel("Log In");
        JLabel label1 = new JLabel("Username");
        JLabel label2 = new JLabel("Password");
        username = new JTextField(20);
        password = new JPasswordField(20);
        login = new JButton("Login");
        register = new JButton("Register");

        username.setBounds(150, 100, 200, 20);
        password.setBounds(150, 150, 200, 20);
        label1.setBounds(50, 100, 100, 20);
        label2.setBounds(50, 150, 100, 20);
        login.setBounds(150, 200, 100, 20);
        register.setBounds(150, 250, 100, 20);

        panel = new JPanel();
        panel.add(title);
        panel.add(new JLabel(""));
        panel.add(label1);
        panel.add(username);
        panel.add(label2);
        panel.add(password);
        panel.add(login);
        panel.add(register);
        panel.setLayout(new GridLayout(0, 2));

        add(panel);

        setVisible(true);
    }

    //region get&set

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JTextField getUsername() {
        return username;
    }

    public void setUsername(JTextField username) {
        this.username = username;
    }

    public JPasswordField getPassword() {
        return password;
    }

    public void setPassword(JPasswordField password) {
        this.password = password;
    }

    public JButton getLogin() {
        return login;
    }

    public void setLogin(JButton login) {
        this.login = login;
    }

    public JButton getRegister() {
        return register;
    }

    public void setRegister(JButton register) {
        this.register = register;
    }

    //endregion
}
