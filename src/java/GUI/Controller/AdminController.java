package GUI.Controller;

import BusinessLogic.BaseProduct;
import BusinessLogic.CompositeProduct;
import BusinessLogic.DeliveryService;
import BusinessLogic.MenuItem;
import GUI.View.Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AdminController {
    private Admin admin;
    private DeliveryService deliveryService;
    private List<MenuItem> products;

    public AdminController() {
        admin = new Admin();
        admin.setVisible(true);
        deliveryService = new DeliveryService();
        listen();
        updateTable();
    }

    public void listen() {
        admin.getDelete().addActionListener(e -> {
            int row = admin.getTable().getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Please select a row");
                return;
            }
            String title = (String) admin.getTable().getValueAt(row, 0);
            deliveryService.deleteProduct(deliveryService.getProduct(title));
            updateTable();
        });
        admin.getBack().addActionListener(e -> {
            admin.setVisible(false);
            admin.dispose();
            new MenuController();
        });
        admin.getGenerateReport().addActionListener(e -> {
            String[] list = {"Report 1", "Report 2", "Report 3", "Report 4"};
            JComboBox<String> comboBox = new JComboBox<>(list);
            JOptionPane.showMessageDialog(null, comboBox, "Select a report",
                    JOptionPane.QUESTION_MESSAGE);
            if (comboBox.getSelectedIndex() == 0) {
                //create new frame
                JFrame frame1 = new JFrame("Report 1");
                JLabel start = new JLabel("Start hour");
                JLabel end = new JLabel("End hour");
                JTextField startHour = new JTextField();
                JTextField endHour = new JTextField();
                JButton generate = new JButton("Generate");
                JButton back = new JButton("Back");
                JPanel panel1 = new JPanel();
                panel1.setLayout(new GridLayout(3, 2));
                panel1.add(start);
                panel1.add(startHour);
                panel1.add(end);
                panel1.add(endHour);
                panel1.add(generate);
                panel1.add(back);
                frame1.add(panel1);
                frame1.setSize(400, 200);
                frame1.setLocationRelativeTo(null);
                frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame1.setVisible(true);
                generate.addActionListener(e1 -> {
                    int startHourInt;
                    int endHourInt;
                    try {
                        startHourInt = Integer.parseInt(startHour.getText());
                        endHourInt = Integer.parseInt(endHour.getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid number");
                        return;
                    }
                    if (startHourInt > endHourInt) {
                        JOptionPane.showMessageDialog(null, "Start hour cannot be greater than end hour");
                        return;
                    }
                    if (startHourInt < 0 || startHourInt > 23 || endHourInt < 0 || endHourInt > 23) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid hour");
                        return;
                    }
                    deliveryService.generateReport1(startHourInt, endHourInt);
                    JOptionPane.showMessageDialog(null, "Report generated");
                    frame1.dispose();
                });
                back.addActionListener(e1 -> {
                    frame1.dispose();
                });
            } else if (comboBox.getSelectedIndex() == 1) {
                JFrame frame2 = new JFrame("Report 2");
                JLabel specifiedNr = new JLabel("Specified number");
                JTextField specifiedNrField = new JTextField();
                JButton generate = new JButton("Generate");
                JButton back = new JButton("Back");
                JPanel panel2 = new JPanel();
                panel2.setLayout(new GridLayout(2, 2));
                panel2.add(specifiedNr);
                panel2.add(specifiedNrField);
                panel2.add(generate);
                panel2.add(back);
                frame2.add(panel2);
                frame2.setSize(400, 200);
                frame2.setLocationRelativeTo(null);
                frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame2.setVisible(true);
                generate.addActionListener(e1 -> {
                    int specifiedNrInt;
                    try {
                        specifiedNrInt = Integer.parseInt(specifiedNrField.getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid number");
                        return;
                    }
                    if (specifiedNrInt < 0) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid number");
                        return;
                    }
                    deliveryService.generateReport2(specifiedNrInt);
                    JOptionPane.showMessageDialog(null, "Report generated");
                    frame2.dispose();
                });
                //listen to back button
                back.addActionListener(e1 -> {
                    frame2.dispose();
                });
            } else if (comboBox.getSelectedIndex() == 2) {
                JFrame frame3 = new JFrame("Report 3");
                JLabel specifiedNrOrder = new JLabel("Specified number of orders");
                JTextField specifiedNrOrderField = new JTextField();
                JLabel specifiedPrice = new JLabel("Specified price");
                JTextField specifiedPriceField = new JTextField();
                JButton generate = new JButton("Generate");
                JButton back = new JButton("Back");
                JPanel panel3 = new JPanel();
                panel3.setLayout(new GridLayout(3, 2));
                panel3.add(specifiedNrOrder);
                panel3.add(specifiedNrOrderField);
                panel3.add(specifiedPrice);
                panel3.add(specifiedPriceField);
                panel3.add(generate);
                panel3.add(back);
                frame3.add(panel3);
                frame3.setSize(400, 200);
                frame3.setLocationRelativeTo(null);
                frame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame3.setVisible(true);
                //listen to generate button
                generate.addActionListener(e1 -> {
                    int specifiedNrOrderInt;
                    int specifiedPriceInt;
                    try {
                        specifiedNrOrderInt = Integer.parseInt(specifiedNrOrderField.getText());
                        specifiedPriceInt = Integer.parseInt(specifiedPriceField.getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid number");
                        return;
                    }
                    if (specifiedNrOrderInt < 0) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid number");
                        return;
                    }
                    if (specifiedPriceInt < 0) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid number");
                        return;
                    }
                    deliveryService.generateReport3(specifiedNrOrderInt, specifiedPriceInt);
                    JOptionPane.showMessageDialog(null, "Report generated");
                    frame3.dispose();
                });
                back.addActionListener(e1 -> {
                    frame3.dispose();
                });
            } else if (comboBox.getSelectedIndex() == 3) {
                JFrame frame4 = new JFrame("Report 4");
                JLabel Year = new JLabel("Year");
                JTextField YearField = new JTextField();
                JLabel Month = new JLabel("Month");
                JTextField MonthField = new JTextField();
                JLabel Day = new JLabel("Day");
                JTextField DayField = new JTextField();
                JButton generate = new JButton("Generate");
                JButton back = new JButton("Back");
                JPanel panel4 = new JPanel();
                panel4.setLayout(new GridLayout(4, 2));
                panel4.add(Year);
                panel4.add(YearField);
                panel4.add(Month);
                panel4.add(MonthField);
                panel4.add(Day);
                panel4.add(DayField);
                panel4.add(generate);
                panel4.add(back);
                frame4.add(panel4);
                frame4.setSize(400, 200);
                frame4.setLocationRelativeTo(null);
                frame4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame4.setVisible(true);
                generate.addActionListener(e1 -> {
                    int yearInt;
                    int monthInt;
                    int dayInt;
                    try {
                        yearInt = Integer.parseInt(YearField.getText());
                        monthInt = Integer.parseInt(MonthField.getText());
                        dayInt = Integer.parseInt(DayField.getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid number");
                        return;
                    }
                    if (yearInt < 0) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid number");
                        return;
                    }
                    if (monthInt < 0 || monthInt > 12) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid number");
                        return;
                    }
                    if (dayInt < 0 || dayInt > 31) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid number");
                        return;
                    }
                    deliveryService.generateReport4(yearInt, monthInt, dayInt);
                    JOptionPane.showMessageDialog(null, "Report generated");
                    frame4.dispose();
                });
                back.addActionListener(e1 -> {
                    frame4.dispose();
                });
            }
        });
        admin.getImportProducts().addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Importing products");
            deliveryService.importFromFile();
            updateTable();
        });
        admin.getAdd().addActionListener(e -> {
            JFrame frame = new JFrame("Add Product");
            frame.setSize(400, 400);
            JLabel label = new JLabel("Enter product details");
            JTextField title = new JTextField();
            JTextField rating = new JTextField();
            JTextField calories = new JTextField();
            JTextField protein = new JTextField();
            JTextField fat = new JTextField();
            JTextField sodium = new JTextField();
            JTextField price = new JTextField();
            JButton add = new JButton("Add");
            JButton cancel = new JButton("Cancel");
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(10, 2));
            panel.add(label);
            panel.add(new JLabel());
            panel.add(new JLabel("Title"));
            panel.add(title);
            panel.add(new JLabel("Rating"));
            panel.add(rating);
            panel.add(new JLabel("Calories"));
            panel.add(calories);
            panel.add(new JLabel("Protein"));
            panel.add(protein);
            panel.add(new JLabel("Fat"));
            panel.add(fat);
            panel.add(new JLabel("Sodium"));
            panel.add(sodium);
            panel.add(new JLabel("Price"));
            panel.add(price);
            panel.add(add);
            panel.add(cancel);
            frame.add(panel);
            frame.setVisible(true);
            add.addActionListener(e1 -> {
                if (title.getText().equals("") || rating.getText().equals("") || calories.getText().equals("") ||
                        protein.getText().equals("") || fat.getText().equals("") || sodium.getText().equals("") ||
                        price.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields");
                    return;
                } else {
                    BaseProduct product = new BaseProduct(title.getText(), Float.parseFloat(rating.getText()),
                            Integer.parseInt(calories.getText()), Integer.parseInt(protein.getText()),
                            Integer.parseInt(fat.getText()), Integer.parseInt(sodium.getText()),
                            Integer.parseInt(price.getText()));
                    deliveryService.addProduct(product);
                    updateTable();
                    JOptionPane.showMessageDialog(null, "Product added");
                    frame.setVisible(false);
                    frame.dispose();
                }
            });
            cancel.addActionListener(e1 -> {
                frame.setVisible(false);
                frame.dispose();
            });
        });
        admin.getEdit().addActionListener(e -> {
            int row = admin.getTable().getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Please select a row");
                return;
            }
            String title = (String) admin.getTable().getValueAt(row, 0);
            MenuItem product = deliveryService.getProduct(title);
            JFrame frame = new JFrame("Edit Product");
            frame.setSize(400, 400);
            JLabel label = new JLabel("Enter product details");
            JTextField title1 = new JTextField();
            JTextField rating = new JTextField();
            JTextField calories = new JTextField();
            JTextField protein = new JTextField();
            JTextField fat = new JTextField();
            JTextField sodium = new JTextField();
            JTextField price = new JTextField();
            JButton edit = new JButton("Edit");
            JButton cancel = new JButton("Cancel");
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(10, 2));
            panel.add(label);
            panel.add(new JLabel());
            panel.add(new JLabel("Title"));
            panel.add(title1);
            panel.add(new JLabel("Rating"));
            panel.add(rating);
            panel.add(new JLabel("Calories"));
            panel.add(calories);
            panel.add(new JLabel("Protein"));
            panel.add(protein);
            panel.add(new JLabel("Fat"));
            panel.add(fat);
            panel.add(new JLabel("Sodium"));
            panel.add(sodium);
            panel.add(new JLabel("Price"));
            panel.add(price);
            panel.add(edit);
            panel.add(cancel);
            frame.add(panel);
            frame.setVisible(true);
            //listen to edit button
            edit.addActionListener(e1 -> {
                if (title1.getText().equals("") || rating.getText().equals("") || calories.getText().equals("") ||
                        protein.getText().equals("") || fat.getText().equals("") || sodium.getText().equals("") ||
                        price.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields");
                    return;
                } else {
                    BaseProduct product1 = new BaseProduct(title1.getText(), Float.parseFloat(rating.getText()),
                            Integer.parseInt(calories.getText()), Integer.parseInt(protein.getText()),
                            Integer.parseInt(fat.getText()), Integer.parseInt(sodium.getText()),
                            Integer.parseInt(price.getText()));
                    deliveryService.updateProduct(product, product1);
                    updateTable();
                    JOptionPane.showMessageDialog(null, "Product edited");
                    frame.setVisible(false);
                    frame.dispose();
                }
            });
        });
        admin.getCompositeProduct().addActionListener(e -> {
            int[] rows = admin.getTable().getSelectedRows();
            if (rows.length == 0) {
                JOptionPane.showMessageDialog(null, "Please select at least one row");
                return;
            }
            JFrame frame = new JFrame("Composite Product");
            frame.setSize(400, 200);
            frame.setLocationRelativeTo(null);
            JLabel label = new JLabel("Enter composite product title");
            JTextField title = new JTextField();
            JButton add = new JButton("Add");
            JButton cancel = new JButton("Cancel");
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(2, 2));
            panel.add(label);
            panel.add(title);
            panel.add(add);
            panel.add(cancel);
            frame.add(panel);
            frame.setVisible(true);
            add.addActionListener(e1 -> {
                if (title.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please provide a title");
                    return;
                } else {
                    List<MenuItem> products = new ArrayList<>();
                    for (int i = 0; i < rows.length; i++) {
                        String title1 = (String) admin.getTable().getValueAt(rows[i], 0);
                        products.add(deliveryService.getProduct(title1));
                    }
                    CompositeProduct product = new CompositeProduct(title.getText(), products);
                    deliveryService.addProduct(product);
                    updateTable();
                    JOptionPane.showMessageDialog(null, "Product added");
                    frame.setVisible(false);
                    frame.dispose();
                }
            });
            cancel.addActionListener(e1 -> {
                frame.setVisible(false);
                frame.dispose();
            });
        });
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
        admin.getTable().setModel(model);
        admin.getScrollPane().setViewportView(admin.getTable());
        admin.getPanel().revalidate();
    }
}
