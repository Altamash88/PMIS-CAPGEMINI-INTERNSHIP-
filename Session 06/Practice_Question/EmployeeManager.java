// Practice Question: Employee and Manager 

// Problem Statement:

// Create a Java program with a parent class Employee and a child class Manager.

// Employee Class: Declare name and salary variables, initialize them using a constructor, and create a displayDetails() method to print them.
// Manager Class: Inherit Employee, add a department variable, and create a constructor using super(). Override displayDetails() using super.displayDetails() to print the employee details, department, and "Role: Manager".
// Main Class: Create a Manager object with name "Anish", salary 50000, and department "IT". Call displayDetails().

// Expected Output:

// Name: Ankit
// Salary: 50000.0
// Department: IT
// Role: Manager


package Day_6.Practice_Question;

class Employee {
    String name;
    double salary;

    // Parent class constructor
    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    // Display employee details
    void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Salary: " + salary);
    }
}

class Manager extends Employee {
    String department;

    // Child class constructor
    Manager(String name, double salary, String department) {
        super(name, salary);
        this.department = department;
    }

    // Method overriding
    @Override
    void displayDetails() {
        super.displayDetails();
        System.out.println("Department: " + department);
        System.out.println("Role: Manager");
    }
}

public class EmployeeManager {
    public static void main(String[] args) {
        Manager m = new Manager("Jhon", 100000, "CS");

        m.displayDetails();
    }
}