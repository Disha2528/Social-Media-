package exercise2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Inventory {
    private List<Product> products = new ArrayList<>();
    private Map<String, Double> categorySales = new HashMap<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getBestSellers() {
        return products.stream()
                .filter(product -> product.getSalesCount() > 5)
                .collect(Collectors.toList()); // Convert back to a List
    }


    public void updateCategorySales(String category, double amount) {
        categorySales.put(category, categorySales.getOrDefault(category, 0.0) + amount);

    }

    public void displayCategorySales() {
        categorySales.forEach((category, amount) ->
                System.out.println("Category: " + category + ", Total Sales: Rs " + amount));
    }
}
