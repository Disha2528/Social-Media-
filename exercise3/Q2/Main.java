package exercise3.Q2;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc= new Scanner(System.in);
        System.out.println("Enter Number of Students");
        int n= sc.nextInt();

        Student[] students= new Student[n];

        for(int i=0;i<n;i++){
            Student s= new Student();
            System.out.println("Enter Student Id");
            s.setId(sc.nextInt());
            System.out.println("Enter Name");
            s.setName(sc.next());
            System.out.println("enter Marks");
            s.setMarks(sc.nextInt());

            students[i]=s;
        }

        System.out.println("Descending Order of Marks");

        Arrays.sort(students,new MarksCompare().reversed());

        for(int i=0;i<n;i++){
            System.out.println(students[i]);
        }

        System.out.println("Alphabetical Order");

        Arrays.sort(students,Comparator.comparing(s->s.getName()));

        for(int i=0;i<n;i++){
            System.out.println(students[i]);
        }

        System.out.println("Top 3");

        Arrays.stream(students).sorted(new MarksCompare().reversed()).limit(3).forEach(System.out::println);



    }


}
