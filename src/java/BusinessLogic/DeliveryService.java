package BusinessLogic;

import DataAccess.FileWriter;
import DataAccess.Serializator;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;

/**
 * @invariant wellFormed()
 */

public class DeliveryService extends Observable implements IDeliveryServiceProcessing {
    public List<MenuItem> products;
    public List<User> users;
    public Map<Order, List<MenuItem>> orders;
    Serializator serializator = new Serializator();


    public DeliveryService() {
        //check if deserialized file exists
        Path path = Paths.get("src\\main\\resources\\users.ser");
        System.out.println(path.toAbsolutePath());
        if (Files.exists(path)) {
            users = (List<User>) serializator.deserialize("src\\main\\resources\\users.ser");
            products = (List<MenuItem>) serializator.deserialize("src\\main\\resources\\products.ser");
            orders = (Map<Order, List<MenuItem>>) serializator.deserialize("src\\main\\resources\\orders.ser");
        } else {
            users = new ArrayList<>();
            products = new ArrayList<>();
            orders = new HashMap<>();
            User admin = new User(users.size() + 1, "admin", "admin", User.UserType.ADMIN);
            users.add(admin);
            User employee = new User(users.size() + 1, "employee", "employee", User.UserType.EMPLOYEE);
            users.add(employee);
            importFromFile();
            serializator.serialize(users, "src\\main\\resources\\users.ser");
            serializator.serialize(products, "src\\main\\resources\\products.ser");
            serializator.serialize(orders, "src\\main\\resources\\orders.ser");
        }

    }


    public void importFromFile() {
        Path path = Paths.get("src\\main\\resources\\products.csv").toAbsolutePath();
        products = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                String title = attributes[0];
                float rating = Float.parseFloat(attributes[1]);
                int calories = Integer.parseInt(attributes[2]);
                int protein = Integer.parseInt(attributes[3]);
                int fat = Integer.parseInt(attributes[4]);
                int sodium = Integer.parseInt(attributes[5]);
                int price = Integer.parseInt(attributes[6]);
                //check for duplicate title
                if (products.stream().anyMatch(p -> p.getTitle().equals(title))) {
                    line = br.readLine();
                } else {
                    MenuItem product = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
                    products.add(product);
                    line = br.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void register(String username, String password) {
        User user = new User(users.size() + 1, username, password, User.UserType.CUSTOMER);
        users.add(user);
        serializator.serialize(users, "src\\main\\resources\\users.ser");
    }


    public int login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user.getUserType().ordinal();
            }
        }
        return -1;
    }

    public MenuItem getProduct(String title) {
        for (MenuItem product : products) {
            if (product.getTitle().equals(title)) {
                return product;
            }
        }
        return null;
    }

    public boolean wellFormed() {
        return products != null && users != null && orders != null;
    }

    @Override
    public void generateReport1(int start, int finish) {
        assert start >= 0 && finish >= 0;
        assert wellFormed();
        List<Order> existing = orders.keySet().stream().filter(o -> o.getDate().getHours() >= start
                && o.getDate().getHours() <= finish).collect(Collectors.toList());
        FileWriter.generateReport1(start, finish, existing);
    }

    @Override
    public void generateReport2(int specifiedNr) {
        assert specifiedNr >= 0;
        assert wellFormed();
        List<MenuItem> existing = products.stream()
                .filter(x -> orders.values().stream()
                        .flatMapToLong(list -> LongStream.of(list.stream()
                                .filter(item -> x.getTitle().equals(item.getTitle()))
                                .count()))
                        .sum() >= specifiedNr)
                .collect(Collectors.toList());
        FileWriter.generateReport2(specifiedNr, existing);
    }

    @Override
    public void generateReport3(int specifiedNrOrderInt, int specifiedPriceInt) {
        assert specifiedNrOrderInt >= 0 && specifiedPriceInt >= 0;
        assert wellFormed();
        List<User> existing = users.stream()
                .filter(user -> orders.keySet().stream()
                        .filter(order -> order.getCustomerId() == user.getId())
                        .filter(order -> orders.get(order).stream()
                                .flatMapToDouble(menuItem -> DoubleStream.of(menuItem.getPrice()))
                                .sum() >= specifiedPriceInt)
                        .count() >= specifiedNrOrderInt)
                .collect(Collectors.toList());
        FileWriter.generateReport3(specifiedNrOrderInt, specifiedPriceInt, existing);
    }

    @Override
    public void generateReport4(int yearInt, int monthInt, int dayInt) {
        assert yearInt >= 0 && monthInt >= 0 && dayInt >= 0;
        assert wellFormed();
        //the products ordered within a specified day with the number of times they have
        //been ordered.
        List<MenuItem> existing = products.stream()
                .filter(x -> orders.values().stream()
                        .flatMapToLong(list -> LongStream.of(list.stream()
                                .filter(item -> x.getTitle().equals(item.getTitle()))
                                .count()))
                        .sum() > 0)
                .collect(Collectors.toList());
        //save the number of times each product has been ordered
        Map<MenuItem, Long> productsOrdered = new HashMap<>();
        for (MenuItem product : existing) {
            productsOrdered.put(product, orders.values().stream()
                    .flatMapToLong(list -> LongStream.of(list.stream()
                            .filter(item -> product.getTitle().equals(item.getTitle()))
                            .count()))
                    .sum());
        }
        FileWriter.generateReport4(yearInt, monthInt, dayInt, productsOrdered);
    }

    public List<MenuItem> searchProductsTitle(String title) {
        return products.stream()
                .filter(x -> x.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<MenuItem> searchProductsRating(float rating) {
        return products.stream()
                .filter(x -> x.getRating() >= rating)
                .collect(Collectors.toList());
    }

    public List<MenuItem> searchProductsCalories(int calories) {
        return products.stream()
                .filter(x -> x.getCalories() >= calories)
                .collect(Collectors.toList());
    }

    public List<MenuItem> searchProductsPrice(int price) {
        return products.stream()
                .filter(x -> x.getPrice() >= price)
                .collect(Collectors.toList());
    }

    public List<MenuItem> searchProductsProtein(int protein) {
        return products.stream()
                .filter(x -> x.getProtein() >= protein)
                .collect(Collectors.toList());
    }

    public List<MenuItem> searchProductsFat(int fat) {
        return products.stream()
                .filter(x -> x.getFat() >= fat)
                .collect(Collectors.toList());
    }

    public List<MenuItem> searchProductsSodium(int sodium) {
        return products.stream()
                .filter(x -> x.getSodium() >= sodium)
                .collect(Collectors.toList());
    }

    @Override
    public void importProducts() {
        importFromFile();
        serializator.serialize(products, "src\\main\\resources\\products.ser");
    }

    @Override
    public void addProduct(MenuItem product) {
        assert product != null;
        assert wellFormed();
        products.add(product);
        serializator.serialize(products, "src\\main\\resources\\products.ser");
        assert products.contains(product);
    }

    @Override
    public void deleteProduct(MenuItem product) {
        assert products.contains(product);
        assert wellFormed();
        products.remove(product);
        serializator.serialize(products, "src\\main\\resources\\products.ser");
        assert !products.contains(product);
    }

    @Override
    public void updateProduct(MenuItem product, MenuItem newProduct) {
        assert products.contains(product);
        assert wellFormed();
        products.remove(product);
        products.add(newProduct);
        serializator.serialize(products, "src\\main\\resources\\products.ser");
        assert !products.contains(product);
        assert products.contains(newProduct);
    }

    @Override
    public void createOrder(User user, List<MenuItem> products) {
        assert products != null && user != null;
        assert wellFormed();
        int price = 0;
        Date date = new Date();
        for (MenuItem product : products) {
            price += product.getPrice();
        }
        Order order = new Order(orders.size() + 1, user.getId(), price, new Date());
        orders.put(order, products);

        FileWriter.createBill(order, this);

        serializator.serialize(orders, "src\\main\\resources\\orders.ser");
        notifyObservers(order);
        assert orders.containsKey(order);
    }

    //region get&set


    public List<MenuItem> getProducts() {
        return products;
    }

    public void setProducts(List<MenuItem> products) {
        this.products = products;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Map<Order, List<MenuItem>> getOrders() {
        return orders;
    }

    public void setOrders(Map<Order, List<MenuItem>> orders) {
        this.orders = orders;
    }

    public Serializator getSerializator() {
        return serializator;
    }

    public void setSerializator(Serializator serializator) {
        this.serializator = serializator;
    }

    //endregion
}
