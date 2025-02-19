package exercise2;

public class Main {
    public static void main(String[] args) {

        Inventory inventory = new Inventory();


        Product laptop = new Electronics(1, "Laptop", 70000, 5);
        Product tshirt = new Clothing(2, "T-Shirt", 800, 100);
        Product apple = new Groceries(3, "Apple", 15, 200);
        inventory.addProduct(laptop);
        inventory.addProduct(tshirt);
        inventory.addProduct(apple);

        Buyer regularBuyer = new Buyer("Alice", DiscountStratergies.noDiscount, inventory);
        Buyer memberBuyer = new Buyer("Bob", DiscountStratergies.membershipDiscount, inventory);
        Buyer blackFridayBuyer = new Buyer("Charlie", DiscountStratergies.blackFridayDiscount, inventory);

        regularBuyer.purchaseProduct(laptop, 3, "exercise2.Electronics");
        memberBuyer.purchaseProduct(tshirt, 10, "exercise2.Clothing");
        blackFridayBuyer.purchaseProduct(apple, 100, "exercise2.Groceries");



        System.out.println("\nBest Selling Products:");
        inventory.getBestSellers().forEach(p ->
                System.out.println(p.getName() + " - Sales: " + p.getSalesCount()));


        System.out.println("\nCategory-wise Sales:");
        inventory.displayCategorySales();
    }
}
