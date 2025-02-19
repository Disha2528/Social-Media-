package exercise2;

public class Clothing extends Product {
    public Clothing(int id, String name, float price, int stock) {
        super(id, name, price, stock);
    }

    public double applyDiscount() {
        double finalPrice = getPrice() - (0.30f * getPrice());
        System.out.println(getName()+ " Price After Discount: " + finalPrice);
        return finalPrice;
    }
}
