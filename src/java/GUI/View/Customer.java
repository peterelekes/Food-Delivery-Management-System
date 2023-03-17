package GUI.View;

import javax.swing.*;
import java.awt.*;

public class Customer extends JFrame {
    private JFrame frame = this;
    private JPanel panel;

    private JTable table;
    private JTable orderTable;
    private JButton add;
    private JButton clear;
    private JButton order;
    private JButton back;
    private JScrollPane scrollPane;
    private JScrollPane orderScrollPane;
    private JLabel price;
    private JTextField searchBar;
    private JButton search;
    private JComboBox<String> choice;

    public Customer() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setTitle("Customer");
        setLocationRelativeTo(null);
        JLabel label1 = new JLabel("Orders");
        JLabel label2 = new JLabel("Total price: ");
        price = new JLabel("0");
        label1.setBounds(25, 50, 100, 20);
        table = new JTable();
        table.setBounds(25, 50, 750, 400);
        orderTable = new JTable();
        orderTable.setBounds(25, 50, 750, 400);
        add = new JButton("Add");
        back = new JButton("Back");
        clear = new JButton("Clear");
        order = new JButton("Order");
        scrollPane = new JScrollPane(table);
        orderScrollPane = new JScrollPane(orderTable);
        searchBar = new JTextField();
        search = new JButton("Search");
        String[] choices = {"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
        choice = new JComboBox<>(choices);

        panel = new JPanel();
        panel.add(choice);
        panel.add(searchBar);
        panel.add(search);
        panel.add(add);
        panel.add(clear);
        panel.add(order);
        panel.add(back);
        panel.add(table);
        panel.add(scrollPane);
        panel.add(label1);
        panel.add(label2);
        panel.add(price);
        panel.add(orderTable);
        panel.add(orderScrollPane);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        add(panel);

        setVisible(true);
    }

    //region get&set


    public JComboBox<String> getChoice() {
        return choice;
    }

    public JTextField getSearchBar() {
        return searchBar;
    }

    public JButton getSearch() {
        return search;
    }

    public JButton getClear() {
        return clear;
    }

    public JLabel getPrice() {
        return price;
    }

    public JScrollPane getOrderScrollPane() {
        return orderScrollPane;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JTable getOrderTable() {
        return orderTable;
    }

    public void setOrderTable(JTable orderTable) {
        this.orderTable = orderTable;
    }

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

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JButton getAdd() {
        return add;
    }

    public void setAdd(JButton add) {
        this.add = add;
    }

    public JButton getOrder() {
        return order;
    }

    public void setOrder(JButton order) {
        this.order = order;
    }

    public JButton getBack() {
        return back;
    }

    public void setBack(JButton back) {
        this.back = back;
    }
    //endregion
}
