package exercise2;

public class Electronics extends Product {

    public Electronics(int id, String name, float price, int stock) {
        super(id, name, price, stock);
    }

    @Override
    public double applyDiscount() {
        double finalPrice = getPrice() - (0.10f * getPrice());
        System.out.println(getName()+ " Price After Discount: " + finalPrice);
        return finalPrice;
    }
}
