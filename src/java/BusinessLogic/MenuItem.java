package BusinessLogic;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {
    private String title;

    public MenuItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public abstract float getRating();

    public abstract int getCalories();

    public abstract int getProtein();

    public abstract int getFat();

    public abstract int getSodium();

    public abstract int getPrice();

    @Override
    public String toString() {
        return "MenuItem [title=" + title + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MenuItem other = (MenuItem) obj;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }
}
