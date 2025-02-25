package exercise3.Q1;

import java.util.*;
import java.util.Scanner;
import java.util.stream.Collectors;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of Employee");
        int n= sc.nextInt();
        Employee []arr= new Employee[n];

        for(int i=0;i<n;i++){

            Employee e= new Employee();
            System.out.println("Employee Id");
            e.setId(sc.nextInt());
            System.out.println("Employee name");
            e.setName(sc.next());
            System.out.println("Employee Department");
            e.setDepartment(sc.next());
            System.out.println("Employee salary");
            e.setSalary(sc.nextInt());
            System.out.println("Employee age");
            e.setAge(sc.nextInt());

            arr[i]=e;
        }

        System.out.println("Employees with salary > 50000");
        Arrays.stream(arr).filter(e->e.getSalary()>50000).forEach(System.out::println);




        List<Integer> salaries= new ArrayList<>();
        for(int i=0;i<n;i++){
            salaries.add(arr[i].getSalary());
        }

        System.out.println("Distinct Salaries");
        salaries.stream().distinct().forEach(System.out::println);

        Map<String,Double> Avg= Arrays.stream(arr).collect(Collectors.groupingBy(e->e.getDepartment(),
                                                                Collectors.averagingInt(e->e.getSalary())));

        Avg.entrySet().stream()
                .forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));



        System.out.println("Youngest Employee");

        Employee empY = Arrays.stream(arr)
                .sorted(new AgeCompare())
                .findFirst().orElse(null);

        System.out.println(empY.toString());


        System.out.println("Oldest Employee");

        List<Employee> emp = Arrays.stream(arr)
                .sorted(new AgeCompare())
                .collect(Collectors.toList());

        System.out.println(emp.get(emp.size()-1).toString());

    }

}
