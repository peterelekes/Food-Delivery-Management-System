package GUI.Controller;

import BusinessLogic.DeliveryService;
import BusinessLogic.MenuItem;
import GUI.View.Customer;
import GUI.View.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerController {
    private Customer customer;
    private DeliveryService deliveryService;
    private List<MenuItem> products;
    private String username;

    public CustomerController(String userName) {
        customer = new Customer();
        customer.setVisible(true);
        deliveryService = new DeliveryService();
        products = new ArrayList<>();
        username = userName;
        listen();
        updateTable();
    }

    public void listen() {
        customer.getBack().addActionListener(e -> {
            customer.setVisible(false);
            customer.dispose();
            MenuController menuController = new MenuController();
        });

        customer.getAdd().addActionListener(e -> {
            int row = customer.getTable().getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Please select a row");
                return;
            } else {
                String title = (String) customer.getTable().getValueAt(row, 0);
                products.add(deliveryService.getProduct(title));
                int price = 0;
                for (MenuItem product : products) {
                    price += product.getPrice();
                }
                customer.getPrice().setText(String.valueOf(price));
                updateOrderTable();
            }
        });

        customer.getOrder().addActionListener(e -> {
            if (products.size() == 0) {
                JOptionPane.showMessageDialog(null, "Please add items to order");
                return;
            } else {
                deliveryService.createOrder(deliveryService.getUser(username), products);
                JOptionPane.showMessageDialog(null, "Order placed");
                products.clear();
                customer.getPrice().setText("0");
                updateOrderTable();

            }
        });
        customer.getClear().addActionListener(e -> {
            products.clear();
            customer.getPrice().setText("0");
            updateOrderTable();
        });
        customer.getSearch().addActionListener(e -> {
            int choice = customer.getChoice().getSelectedIndex();
            switch (choice) {
                case 0:
                    String title = customer.getSearchBar().getText();
                    updateTable(deliveryService.searchProductsTitle(title));
                    break;
                case 1:
                    Float rating;
                    try {
                        rating = Float.parseFloat(customer.getSearchBar().getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid number");
                        return;
                    }
                    updateTable(deliveryService.searchProductsRating(rating));
                    break;
                case 2:
                    int calories;
                    try {
                        calories = Integer.parseInt(customer.getSearchBar().getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid number");
                        return;
                    }
                    updateTable(deliveryService.searchProductsCalories(calories));
                    break;
                case 3:
                    int protein;
                    try {
                        protein = Integer.parseInt(customer.getSearchBar().getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid number");
                        return;
                    }
                    updateTable(deliveryService.searchProductsProtein(protein));
                    break;
                case 4:
                    int fat;
                    try {
                        fat = Integer.parseInt(customer.getSearchBar().getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid number");
                        return;
                    }
                    updateTable(deliveryService.searchProductsFat(fat));
                    break;
                case 5:
                    int sodium;
                    try {
                        sodium = Integer.parseInt(customer.getSearchBar().getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid number");
                        return;
                    }
                    updateTable(deliveryService.searchProductsSodium(sodium));
                    break;
                case 6:
                    int price;
                    try {
                        price = Integer.parseInt(customer.getSearchBar().getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid number");
                        return;
                    }
                    updateTable(deliveryService.searchProductsPrice(price));
                    break;
                default:
                    break;
            }
        });
    }

    public void updateTable(List<MenuItem> products) {
        DefaultTableModel model = new DefaultTableModel();
        String[] columnNames = {"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
        model.setColumnIdentifiers(columnNames);
        for (int i = 0; i < products.size(); i++) {
            MenuItem product = products.get(i);
            Object[] row = {product.getTitle(), product.getRating(), product.getCalories(),
                    product.getProtein(), product.getFat(), product.getSodium(), product.getPrice()};
            model.addRow(row);
        }
        customer.getTable().setModel(model);
        customer.getScrollPane().setViewportView(customer.getTable());
        customer.getPanel().revalidate();
    }

    public void updateTable() {
        DefaultTableModel model = new DefaultTableModel();
        String[] columnNames = {"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
        model.setColumnIdentifiers(columnNames);
        for (int i = 0; i < deliveryService.products.size(); i++) {
            model.addRow(new Object[]{deliveryService.products.get(i).getTitle(),
                    deliveryService.products.get(i).getRating(), deliveryService.products.get(i).getCalories(),
                    deliveryService.products.get(i).getProtein(), deliveryService.products.get(i).getFat(),
                    deliveryService.products.get(i).getSodium(), deliveryService.products.get(i).getPrice()});
        }
        customer.getTable().setModel(model);
        customer.getScrollPane().setViewportView(customer.getTable());
        customer.getPanel().revalidate();
    }

    public void updateOrderTable() {
        DefaultTableModel model = new DefaultTableModel();
        String[] columnNames = {"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
        model.setColumnIdentifiers(columnNames);
        for (int i = 0; i < products.size(); i++) {
            model.addRow(new Object[]{products.get(i).getTitle(),
                    products.get(i).getRating(), products.get(i).getCalories(),
                    products.get(i).getProtein(), products.get(i).getFat(),
                    products.get(i).getSodium(), products.get(i).getPrice()});
        }
        customer.getOrderTable().setModel(model);
        customer.getOrderScrollPane().setViewportView(customer.getOrderTable());
        customer.getPanel().revalidate();
    }
}
