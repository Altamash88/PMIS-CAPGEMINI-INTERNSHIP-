/*Problem 1: The Campus Coffee Cart Wallet
A local campus coffee shop wants to eliminate physical punch cards and implement a digital prepaid wallet system for students.
When a student signs up, the system must set up their wallet with their name and an initial opening deposit.
Throughout the week, students perform transactions:
1.
They can add funds to their wallet at any time, which should immediately reflect in their total balance.
2.
When making a purchase, the system checks whether the wallet has enough funds. If it does, the amount is deducted and the new balance is shown. However, if a student tries to buy a latte that costs more than what is left in their wallet, the transaction must be blocked with an "Insufficient funds!" notice, leaving the balance untouched.
3.
At any point, a student can request a quick account overview showing their name and current balance.

Your Task:
•
Design the CoffeeWallet class to track a customer's wallet.
•
In your program's runner (main), simulate a customer opening an account with ₹500, topping up with ₹200, successfully buying a ₹150 snack, and finally attempting an invalid purchase of ₹800.

*/

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

public class constructor_questions {

    public static void main(String[] args) {

        // Opening wallet with ₹500
        CoffeeWallet wallet = new CoffeeWallet("Ankit", 500);

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
