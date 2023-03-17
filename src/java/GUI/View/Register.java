package GUI.View;

import javax.swing.*;

public class Register extends JFrame {
    private JFrame frame = this;
    private JPanel panel;

    private JTextField username;
    private JPasswordField password;
    private JPasswordField confirmPassword;
    private JButton register;

    public Register() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setTitle("Register");
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel label1 = new JLabel("Username");
        JLabel label2 = new JLabel("Password");
        JLabel label3 = new JLabel("Confirm Password");
        username = new JTextField(20);
        password = new JPasswordField(20);
        confirmPassword = new JPasswordField(20);
        register = new JButton("Register");

        username.setBounds(150, 100, 200, 20);
        password.setBounds(150, 150, 200, 20);
        confirmPassword.setBounds(150, 200, 200, 20);
        label1.setBounds(50, 100, 100, 20);
        label2.setBounds(50, 150, 100, 20);
        label3.setBounds(10, 200, 140, 20);
        register.setBounds(150, 250, 100, 20);

        panel = new JPanel();
        panel.setLayout(null);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(username);
        panel.add(password);
        panel.add(confirmPassword);
        panel.add(register);

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

    public JPasswordField getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(JPasswordField confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public JButton getRegister() {
        return register;
    }

    public void setRegister(JButton register) {
        this.register = register;
    }
    //endregion
}
