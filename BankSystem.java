import java.util.ArrayList;
import java.util.Scanner;

/**
 * Simple Bank Account Management System (console based).
 * Demonstrates classes, objects, ArrayList, and a menu.
 * Compile: javac BankSystem.java
 * Run:     java BankSystem
 */
public class BankSystem {

    // ---------- Account class ----------
    static class Account {
        private int accountNumber;
        private String holderName;
        private double balance;
        private ArrayList<String> history = new ArrayList<>();

        Account(int accountNumber, String holderName, double initialDeposit) {
            this.accountNumber = accountNumber;
            this.holderName = holderName;
            this.balance = initialDeposit;
            history.add("Account opened with " + initialDeposit);
        }

        int getAccountNumber() { return accountNumber; }
        String getHolderName() { return holderName; }
        double getBalance() { return balance; }

        void deposit(double amount) {
            if (amount <= 0) {
                System.out.println("Amount must be greater than 0.");
                return;
            }
            balance += amount;
            history.add("Deposited " + amount);
            System.out.println("Deposited " + amount + ". New balance: " + balance);
        }

        void withdraw(double amount) {
            if (amount <= 0) {
                System.out.println("Amount must be greater than 0.");
            } else if (amount > balance) {
                System.out.println("Insufficient balance!");
            } else {
                balance -= amount;
                history.add("Withdrew " + amount);
                System.out.println("Withdrew " + amount + ". New balance: " + balance);
            }
        }

        void showHistory() {
            System.out.println("--- Transaction History ---");
            for (String h : history) System.out.println("  " + h);
        }

        void display() {
            System.out.println("Account No : " + accountNumber);
            System.out.println("Holder     : " + holderName);
            System.out.println("Balance    : " + balance);
        }
    }

    // ---------- Bank data ----------
    static ArrayList<Account> accounts = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int nextAccountNumber = 1001;

    static Account findAccount(int number) {
        for (Account a : accounts)
            if (a.getAccountNumber() == number) return a;
        return null;
    }

    static Account askForAccount() {
        System.out.print("Enter account number: ");
        int number = sc.nextInt();
        Account acc = findAccount(number);
        if (acc == null) System.out.println("Account not found!");
        return acc;
    }

    // ---------- Menu actions ----------
    static void createAccount() {
        sc.nextLine(); // clear leftover newline
        System.out.print("Enter holder name: ");
        String name = sc.nextLine();
        System.out.print("Enter initial deposit: ");
        double amount = sc.nextDouble();

        if (amount < 0) {
            System.out.println("Initial deposit cannot be negative.");
            return;
        }
        Account acc = new Account(nextAccountNumber++, name, amount);
        accounts.add(acc);
        System.out.println("Account created! Your account number is " + acc.getAccountNumber());
    }

    static void deposit() {
        Account acc = askForAccount();
        if (acc == null) return;
        System.out.print("Enter amount to deposit: ");
        acc.deposit(sc.nextDouble());
    }

    static void withdraw() {
        Account acc = askForAccount();
        if (acc == null) return;
        System.out.print("Enter amount to withdraw: ");
        acc.withdraw(sc.nextDouble());
    }

    static void transfer() {
        System.out.println("From account:");
        Account from = askForAccount();
        if (from == null) return;
        System.out.println("To account:");
        Account to = askForAccount();
        if (to == null) return;
        if (from == to) {
            System.out.println("Cannot transfer to the same account.");
            return;
        }
        System.out.print("Enter amount to transfer: ");
        double amount = sc.nextDouble();

        if (amount <= 0 || amount > from.getBalance()) {
            System.out.println("Invalid amount or insufficient balance.");
            return;
        }
        from.withdraw(amount);
        to.deposit(amount);
        System.out.println("Transfer successful!");
    }

    static void showAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts yet.");
            return;
        }
        for (Account a : accounts) {
            a.display();
            System.out.println("-------------------");
        }
    }

    // ---------- Main ----------
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== BANK MENU =====");
            System.out.println("1. Create account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer money");
            System.out.println("5. Check balance");
            System.out.println("6. Transaction history");
            System.out.println("7. Show all accounts");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: createAccount(); break;
                case 2: deposit(); break;
                case 3: withdraw(); break;
                case 4: transfer(); break;
                case 5: {
                    Account acc = askForAccount();
                    if (acc != null) acc.display();
                    break;
                }
                case 6: {
                    Account acc = askForAccount();
                    if (acc != null) acc.showHistory();
                    break;
                }
                case 7: showAllAccounts(); break;
                case 0:
                    System.out.println("Thank you for banking with us!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
