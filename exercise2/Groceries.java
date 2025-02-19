package exercise2;

public class Groceries extends Product{

    public Groceries(int id, String name, float price, int stock) {
        super(id, name, price, stock);
    }

    public double applyDiscount() {
        double finalPrice = getPrice() - (0.20f * getPrice());
        System.out.println(getName()+ " Price After Discount: " + finalPrice);
        return finalPrice;
    }
}
