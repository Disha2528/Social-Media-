package exercise2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

abstract public class Product {

    private int id;
    private String name;
    private float price;
    private int stock;
    private int salesCount = 0;

    public Product() {
    }

    public Product(int id, String name, float price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;

    }

    public void reduceStock(int quantity) throws OutOfStockException {
        if (quantity > stock) {
            throw new OutOfStockException(name + " is out of stock!");
        }
        stock -= quantity;
    }

    public int getSalesCount() {
        return salesCount;
    }

    public void increaseSales(int quantity) {
        this.salesCount += quantity;
    }

    public void logSale(int quantity) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("sales_data.txt", true))) {
            writer.write("Sold: " + quantity + " x " + name + " @ Rs" + price + " each\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing sales log: " + e.getMessage());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


    public abstract double applyDiscount();  // Subclasses must override

}
