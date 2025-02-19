package exercise2;

public class Buyer {
    private String name;
    private DiscountStratergy discountStratergy;
    private Inventory inventory;

    public Buyer(String name, DiscountStratergy discountStratergy, Inventory inventory) {
        this.name = name;
        this.discountStratergy = discountStratergy;
        this.inventory = inventory;
    }

    public void purchaseProduct(Product product, int quantity, String category) {
        try {
            product.reduceStock(quantity);
            double priceAfterProductDiscount = product.applyDiscount();
            double finalPrice = discountStratergy.applyDiscount(priceAfterProductDiscount);
            product.increaseSales(quantity);
            inventory.updateCategorySales(category, finalPrice * quantity);
            product.logSale(quantity);
        } catch (OutOfStockException e) {
            System.out.println("Purchase failed: " + e.getMessage());
        }
    }
}
