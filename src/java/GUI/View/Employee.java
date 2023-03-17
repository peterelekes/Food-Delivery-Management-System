package GUI.View;

import BusinessLogic.DeliveryService;
import BusinessLogic.MenuItem;
import BusinessLogic.Order;
import GUI.Controller.MenuController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class Employee extends JFrame implements Observer {
    private JFrame frame = this;
    private JPanel panel;
    private JTable table;
    private String[] columnNames = {"Order", "Client", "Date", "Price"};
    private DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    DeliveryService deliveryService;

    public Employee(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setTitle("Employee");
        setLocationRelativeTo(null);
        table = new JTable();
        table.setBounds(25, 50, 750, 400);
        table.setModel(model);
        JButton back = new JButton("Back");
        panel = new JPanel();
        panel.add(back);
        back.addActionListener(e -> {
            frame.dispose();
            new MenuController();
        });
        add(panel);
        setVisible(true);
        update(deliveryService, deliveryService.orders);
    }

    @Override
    public void update(Observable o, Object arg) {
        Map<Order, List<MenuItem>> orders = deliveryService.getOrders();
        model.setColumnIdentifiers(columnNames);
        for (Order ord : orders.keySet()) {
            model.addRow(new Object[]{ord.getOrderId(), ord.getCustomerId(), ord.getDate(), ord.getPrice()});
        }
        //update Jtable
        table.setModel(model);
        //add scrollpane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(25, 50, 750, 400);
        panel.add(scrollPane);
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

    public String[] getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }
}
