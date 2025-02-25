package exercise3.Q1;

import java.util.Comparator;

public class AgeCompare implements Comparator<Employee> {
    Employee e;

    @Override
    public int compare(Employee e1, Employee e2){
        return Integer.compare(e1.getAge(),e2.getAge());
    }
}
