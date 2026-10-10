package Day_5;

class BankAccount {

    private String holderName;
    private double balance;

    public BankAccount(String holderName, double balance) {
        this.holderName = holderName;
        setBalance(balance);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double amount) {

        if (amount >= 0) {
            this.balance = amount;
        } else {
            System.out.println("Invalid balance: cannot be negative!");
        }
    }
}

public class Encapsulation {

    public static void main(String[] args) {

        BankAccount account = new BankAccount("Alex", 500);

        account.setBalance(-100);

        System.out.println(account.getBalance());
    }
}