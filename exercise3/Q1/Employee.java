package exercise3.Q1;

public class Employee {

    private int id;
    private String name;
    private String department;
    private int salary;
    private int age;

    public Employee() {
    }

    public Employee(int id, String name, String department, int salary, int age) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.age = age;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString(){
        return "Id: "+ id+ " Name: "+name+" Department: "+ department+" Salary: "+ salary + " Age: "+ age;
    }

}
