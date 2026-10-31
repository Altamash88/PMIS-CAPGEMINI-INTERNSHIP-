package Oops;
import java.util.*;
// Bank Account management system.

class BankAccount {

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


public class test {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: " );
        String name = sc.nextLine();
        System.out.print("Enter your balance: ");
        double balance = sc.nextInt();
     

        // Creating an object
        BankAccount account = new BankAccount(name , balance);

        // Final summary
        account.displaySummary();
    }
}




