import java.util.ArrayList;
import java.util.List;

class Book implements IStoreItem {
    private String isbn;
    private double price;
    private double tax;

    public Book(String isbn, double price, double tax) {
        this.isbn = isbn;
        this.price = price;
        this.tax = tax;
    }

    public String getIsbn() {
        return isbn;
    }

    public double getPrice() {
        return price;
    }

    public double getTax() {
        return tax;
    }

    @Override
    public double accept(IStoreVisitor visitor) {
        return visitor.visit(this);
    }
}
class Coffee implements IStoreItem {
    private String brand;
    private double price;
    private double tax;
    private boolean discount;

    public Coffee(String brand, double price, double tax, boolean discount) {
        this.brand = brand;
        this.price = price;
        this.tax = tax;
        this.discount = discount;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public double getTax() {
        return tax;
    }

    public boolean isDiscount() {
        return discount;
    }

    @Override
    public double accept(IStoreVisitor visitor) {
        return visitor.visit(this);
    }
}
class Tea implements IStoreItem {
    private String brand;
    private double price;
    private double tax;
    private boolean discount;

    public Tea(String brand, double price, double tax, boolean discount) {
        this.brand = brand;
        this.price = price;
        this.tax = tax;
        this.discount = discount;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public double getTax() {
        return tax;
    }

    public boolean isDiscount() {
        return discount;
    }

    @Override
    public double accept(IStoreVisitor visitor) {
       return visitor.visit(this);
    }
}

interface IStoreItem {
    double accept(IStoreVisitor visitor);
}

interface IStoreVisitor {
     double visit(Book book);
     double visit(Tea tea);
     double visit(Coffee coffee);
}

class StoreVisitor implements IStoreVisitor {
    public double calculateBookCost(Book book) {
        return book.getPrice() + book.getTax() * book.getPrice();
    }

    public double calculateTeaCost(Tea tea) {
        double cost = tea.getPrice() + tea.getTax() * tea.getPrice();
        if (tea.isDiscount()) {
            cost -= cost * 0.1;
        }
        return cost;
    }

    public double calculateCoffeeCost(Coffee coffee) {
        double cost = coffee.getPrice() + coffee.getTax() * coffee.getPrice();
        if (coffee.isDiscount()) {
            cost -= cost * 0.15;
        }
        return cost;
    }

    @Override
    public double visit(Book book) {
        return calculateBookCost(book);
    }

    @Override
    public double visit(Tea tea) {
        return calculateTeaCost(tea);
    }

    @Override
    public double visit(Coffee coffee) {
        return calculateCoffeeCost(coffee);
    }
}

public class Main {
    public static void main(String[] args) {
        List<IStoreItem> items = new ArrayList<>();
        items.add(new Book("1234", 20.01, 0.08));
        items.add(new Book("5678", 345, 0.08));
        items.add(new Coffee("Espresso", 300, 0.092, false));
        items.add(new Coffee("Starbucks", 400, 0.099, true));
        items.add(new Tea("Curtis", 50, 0.003, true));

        IStoreVisitor visitor = new StoreVisitor();
        double totalCost = 0;
        for (IStoreItem item : items) {
            totalCost += item.accept(visitor);
        }

        System.out.println("Total cost = " + totalCost);
    }
}
