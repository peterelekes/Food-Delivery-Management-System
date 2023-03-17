package DataAccess;

import BusinessLogic.DeliveryService;
import BusinessLogic.MenuItem;
import BusinessLogic.Order;
import BusinessLogic.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class FileWriter {

    public static void createBill(Order order, DeliveryService deliveryService) {
        String report = "";
        report += "Order ID: " + order.getOrderId() + "\nClient ID: " + order.getCustomerId() + "\nPrice: "
                + order.getPrice() + "\n";
        report += "On date: " + order.getDate() + "\n";
        report += "Products:\n";
        for (MenuItem product : deliveryService.getOrders().get(order)) {
            report += product.getTitle() + "+PRICE: " + product.getPrice() + "\n";
        }
        report += "==========================================================\n";
        String fileName = "src\\main\\resources\\bill_" + order.getDate().getTime() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(fileName))) {
            writer.write(report);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateReport1(int start, int finish, List<Order> existing) {
        String report = "";
        report += "Orders from " + start + " to " + finish + "\n";
        for (Order order : existing) {
            report += "Order ID: " + order.getOrderId() + "\nClient ID: " + order.getCustomerId() + "\nPrice: "
                    + order.getPrice() + "\n";
            report += "==========================================================\n";
        }
        Date date = new Date();
        String fileName = "src\\main\\resources\\report1_" + date.getTime() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(fileName))) {
            writer.write(report);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateReport2(int specifiedNr, List<MenuItem> existing) {
        String report = "";
        report += "Products ordered more than " + specifiedNr + " times:\n";
        for (MenuItem product : existing) {
            report += product.getTitle() + "\n";
        }
        report += "==========================================================\n";
        String fileName = "src\\main\\resources\\report2_" + new Date().getTime() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(fileName))) {
            writer.write(report);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateReport3(int specifiedNrOrderInt, int specifiedPriceInt, List<User> existing) {
        String report = "";
        report += "Users with orders with more than " + specifiedNrOrderInt + " orders and more than " + specifiedPriceInt + " sum:\n";
        for (User user : existing) {
            report += user.getUsername() + "\n";
        }
        report += "==========================================================\n";
        String fileName = "src\\main\\resources\\report3_" + new Date().getTime() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(fileName))) {
            writer.write(report);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateReport4(int yearInt, int monthInt, int dayInt, Map<MenuItem, Long> map) {
        String report = "";
        report += "Orders for the day " + yearInt + "-" + monthInt + "-" + dayInt + ":\n";
        for (Map.Entry<MenuItem, Long> entry : map.entrySet()) {
            report += entry.getKey().getTitle() + ": " + entry.getValue() + "\n";
        }
        report += "==========================================================\n";
        String fileName = "src\\main\\resources\\report4_" + new Date().getTime() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(fileName))) {
            writer.write(report);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
