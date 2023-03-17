package BusinessLogic;

public class BaseProduct extends MenuItem {
    private float rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;

    public BaseProduct(String title, float rating, int calories, int protein, int fat, int sodium, int price) {
        super(title);
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    public BaseProduct(String title, double v, double parseDouble, double aDouble, double v1, double parseDouble1, double aDouble1) {
        super(title);
    }

    //region get
    @Override
    public float getRating() {
        return rating;
    }

    public int getCalories() {
        return calories;
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    public int getSodium() {
        return sodium;
    }

    public int getPrice() {
        return price;
    }
    //endregion

}
