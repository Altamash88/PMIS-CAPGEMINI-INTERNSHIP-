// Car class.

package Oops;

/*class Car{
    String brand;
    String color;
    int speed;

    Car(String brand, String color, int speed){
        this.brand = brand;
        this.color = color;
        this.speed = speed;
    }

    void accelerate(int increase){
        speed += increase;
        System.out.println(brand + " accelerated to "+ speed+" km/hr.");

    }

    void displayInfo(){
        System.out.println("Brand: "+brand+"\n"+"Color: " + color+"\n"+"Speed: "+speed);

    }
}

public class constructor_questions {
    public static void main(String[] args){
            Car c1 = new Car("BMW M5","Maroon",200);
    Car c2 = new Car("BMW i5","Red",250);

    c1.accelerate(35);
    c2.accelerate(40);
    }

}


// Bank Account management system.

/*class BankAccount {

    // Attributes
    String accountHolder;
    double balance;

    // Constructor
    BankAccount(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // Deposit method
    void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount);
        System.out.println("Updated balance: " + balance);
    }

    // Withdraw method
    void withdraw(double amount) {

        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
            System.out.println("Updated balance: " + balance);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    // Display account summary
    void displaySummary() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Current Balance: " + balance);
    }
}


public class constructor_questions {

    public static void main(String[] args) {

        // Creating an object
        BankAccount account = new BankAccount("Anish", 1000.0);

        // Display initial details
        account.displaySummary();

        // Deposit
        account.deposit(500.0);

        // Successful withdrawal
        account.withdraw(300.0);

        // Withdrawal exceeding balance
        account.withdraw(2000.0);

        // Final summary
        account.displaySummary();
    }
}
*/
// Problem 1: The Campus Coffee Cart Wallet

class CoffeeWallet {

    // Attributes
    String customerName;
    double balance;

    // Constructor
    CoffeeWallet(String customerName, double balance) {
        this.customerName = customerName;
        this.balance = balance;
    }

    // Add funds
    void addFunds(double amount) {
        balance += amount;
        System.out.println("Added ₹" + amount);
        System.out.println("Current balance: ₹" + balance);
    }

    // Purchase
    void purchase(double amount) {

        if (amount <= balance) {
            balance -= amount;
            System.out.println("Purchase successful!");
            System.out.println("Amount spent: Rs." + amount);
            System.out.println("Current balance: Rs." + balance);
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    // Account overview
    void displayOverview() {
        System.out.println("Customer: " + customerName);
        System.out.println("Balance: Rs." + balance);
    }
}


class CoffeeWalletDemo {

    public static void main(String[] args) {

        // Opening wallet with ₹500
        CoffeeWallet wallet = new CoffeeWallet("Anish", 500);

        // Account overview
        wallet.displayOverview();

        // Top up with ₹200
        wallet.addFunds(200);

        // Buy ₹150 snack
        wallet.purchase(150);

        // Attempt to buy ₹800 item
        wallet.purchase(800);

        // Final overview
        wallet.displayOverview();
    }
}


// Problem 2: The Academy Admissions Portal
class StudentProfile {

    // Attributes
    String fullName;
    int studentID;
    double score;

    // Constructor 1: Exam Taker
    StudentProfile(String fullName, int studentID, double score) {
        this.fullName = fullName;
        this.studentID = studentID;
        this.score = score;
    }

    // Constructor 2: Direct Walk-in
    StudentProfile(String fullName, int studentID) {
        this.fullName = fullName;
        this.studentID = studentID;
        this.score = 0.0;
    }

    // Determine grade
    char getGrade() {

        if (score >= 90) {
            return 'A';
        } else if (score >= 75) {
            return 'B';
        } else if (score >= 50) {
            return 'C';
        } else {
            return 'F';
        }
    }

    // Print report card
    void printReportCard() {
        System.out.println("Name: " + fullName);
        System.out.println("Student ID: " + studentID);
        System.out.println("Score: " + score);
        System.out.println("Grade: " + getGrade());
        System.out.println("--------------------");
    }
}


public class constructor_questions {

    public static void main(String[] args) {

        // Exam taker
        StudentProfile student1 =
                new StudentProfile("Anish", 101, 82.5);

        // Direct walk-in
        StudentProfile student2 =
                new StudentProfile("Rahul", 102);

        // Print report cards
        student1.printReportCard();
        student2.printReportCard();
    }
}
