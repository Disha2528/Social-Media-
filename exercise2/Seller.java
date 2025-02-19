package exercise2;

public class Seller {
    private String name;
    private Inventory inventory;

    public Seller(String name, Inventory inventory) {
        this.name = name;
        this.inventory = inventory;
    }


    public void addProduct(Product product) {
        inventory.addProduct(product);
        System.out.println(name + " added " + product.getName() + " to inventory.");

    }
}