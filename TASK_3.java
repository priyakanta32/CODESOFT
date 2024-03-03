package intern_java_development;
import java.util.*;
class BankAccount {
    private double balance;

    public BankAccount(double Balance) {
        this.balance = Balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }
}

class ATM {
    private BankAccount bankAccount;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void displayMenu() {
        System.out.println("ATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    public void processOption(int option, Scanner scanner) {
        switch (option) {
            case 1:
                checkBalance();
                break;
            case 2:
                deposit(scanner);
                break;
            case 3:
                withdraw(scanner);
                break;
            case 4:
                System.out.println("Exiting the ATM. Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid option. Please choose a number between 1 and 4.");
        }
    }

    private void checkBalance() {
        System.out.println("Current Balance: $" + bankAccount.getBalance());
    }

    private void deposit(Scanner sc) {
        System.out.print("Enter deposit amount: $");
        double amount = sc.nextDouble();
        bankAccount.deposit(amount);
    }

    private void withdraw(Scanner sc) {
        System.out.print("Enter withdrawal amount: $");
        double amount = sc.nextDouble();
        bankAccount.withdraw(amount);
    }
}

public class TASK_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount userAccount = new BankAccount(1000.0);

        ATM atm = new ATM(userAccount);

        while (true) {
            atm.displayMenu();

            System.out.print("Enter your choice: ");
            int option = scanner.nextInt();
            atm.processOption(option, scanner);
        }
    }
}
