package exercise3.Q3;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Order> orders= new ArrayList<>();

        Scanner sc= new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("Enter number of Orders");
        int n=sc.nextInt();
        String input;

        for(int i=0;i<n;i++){
            Order order= new Order();

            System.out.println("Enter Order Id");
            order.setOrderId(sc.nextInt());

            System.out.println("Enter Customer Name");
            order.setCustomerName(sc.next());

            System.out.println("Enter Total amount");
            order.setTotalAmount(sc.nextInt());

            System.out.println("enter Order Date");
            input= sc.next();

            try{
                LocalDate Date = LocalDate.parse(input,formatter);
                order.setOrderDate(Date);
            }
            catch (DateTimeException e){
                System.out.println("Enter Valid Date");
            }

            System.out.println("What is the Order Status");
            order.setStatus(sc.next());


            orders.add(order);
        }

        System.out.println("Orders Placed in Last 7 Days");
        List<Order> orderList = orders.stream().filter(order -> (order.getOrderDate().isBefore(LocalDate.now())
                && order.getOrderDate().isAfter(LocalDate.now().minusDays(7)))).collect(Collectors.toList());

        for(int i=0;i<orderList.size();i++){
            System.out.println(orderList.get(i));
        }

        System.out.println("Total Revenue");
        System.out.println(orders.stream().filter(order -> order.getStatus().equals("Completed")).map(order->order.getTotalAmount())
                .reduce(0, (amount, total)->total+amount));


        List<String> Customers= orders.stream().filter(order -> order.getTotalAmount()>5000)
                .map(order -> order.getCustomerName()).collect(Collectors.toList());

        for(int i=0;i<Customers.size();i++){
            System.out.println(Customers.get(i));
        }




    }
}
