package BusinessLogic;

import java.util.List;

public class CompositeProduct extends MenuItem {
    private List<MenuItem> products;

    public CompositeProduct(String title, List<MenuItem> products) {
        super(title);
        this.products = products;
    }

    public String getTitle() {
        return super.getTitle();
    }

    //region get&set
    public List<MenuItem> getProducts() {
        return products;
    }

    public void setProducts(List<MenuItem> products) {
        this.products = products;
    }

    @Override
    public float getRating() {
        float rating = 0;
        for (MenuItem product : products) {
            rating += product.getRating();
        }
        return rating / products.size();
    }

    @Override
    public int getCalories() {
        int calories = 0;
        for (MenuItem product : products) {
            calories += product.getCalories();
        }
        return calories;
    }

    @Override
    public int getProtein() {
        int protein = 0;
        for (MenuItem product : products) {
            protein += product.getProtein();
        }
        return protein;
    }

    @Override
    public int getFat() {
        int fat = 0;
        for (MenuItem product : products) {
            fat += product.getFat();
        }
        return fat;
    }

    @Override
    public int getSodium() {
        int sodium = 0;
        for (MenuItem product : products) {
            sodium += product.getSodium();
        }
        return sodium;
    }

    @Override
    public int getPrice() {
        int price = 0;
        for (MenuItem product : products) {
            price += product.getPrice();
        }
        return price;
    }
    //endregion

}
