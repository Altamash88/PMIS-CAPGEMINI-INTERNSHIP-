package Day_6;

class Employee {
    double salary = 30000;
}

class Manager extends Employee {
    double salary = 60000;

    void displaySalary() {
        System.out.println("Manager salary: " + salary);
        System.out.println("Employee salary: " + super.salary);
    }
}

public class SuperKeyword {
    public static void main(String[] args) {
        Manager m = new Manager();
        m.displaySalary();
    }
}