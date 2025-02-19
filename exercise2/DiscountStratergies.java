package exercise2;

public class DiscountStratergies {
    public static final DiscountStratergy noDiscount = price -> price;
    public static final DiscountStratergy membershipDiscount = price -> price * 0.90;  // 10% extra off
    public static final DiscountStratergy blackFridayDiscount = price -> price * 0.80;  // 20% extra off
}
