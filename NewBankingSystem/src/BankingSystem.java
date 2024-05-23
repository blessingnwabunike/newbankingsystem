import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Main class with menu-driven interface
public class BankingSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Bank bank = Bank.getInstance();

    public static void main(String[] args) {
        addDummyData();
        int choice;

        do {
            System.out.println("\nWelcome to the Banking System\n----------------------------\n");
            System.out.println("1. Create New Customer");
            System.out.println("2. Customer Login");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createNewCustomer();
                    break;
                case 2:
                    customerLogin();
                    break;
                case 3:
                    adminLogin();
                    break;
                case 4:
                    System.out.println("Thank you for using the Banking System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 4);
    }

    private static void addDummyData() {
        Customer customer1 = new Customer("Jason", "asd123", "0123 4567", "1 Royal Street");
        customer1.addAccount(new CheckingAccount(101));
        customer1.addAccount(new SavingsAccount(102));
        bank.addCustomer(customer1);

        Customer customer2 = new Customer("Bobby", "qwe123", "4567 1234", "1/8 Edmilson Avenue");
        customer2.addAccount(new CheckingAccount(103));
        bank.addCustomer(customer2);
    }

    private static void createNewCustomer() {
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        String password = "";
        while (password.length() < 6) {
            System.out.println("Set your password (6 characters minimum): ");
            password = scanner.nextLine();
        }
        System.out.println("Enter your contact number: ");
        String contact = scanner.nextLine();
        System.out.println("Set your address: ");
        String address = scanner.nextLine();
        Customer customer = new Customer(name, password, contact, address);
        bank.addCustomer(customer);
        System.out.println("\nCustomer created successfully! Your Customer ID is: " + customer.getId());
    }

    private static void customerLogin() {
        System.out.println("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Password: ");
        String password = scanner.nextLine();

        Customer customer = bank.findCustomerById(customerId);
        if (customer != null && customer.authenticate(password)) {
            System.out.println("Login successful! Welcome " + customer.getName());
            customerMenu(customer);
        } else {
            System.out.println("Invalid Customer ID or Password. Please try again.");
        }
    }

    private static void adminLogin() {
        System.out.println("Enter Admin Name: ");
        String adminName = scanner.nextLine();
        System.out.println("Enter Password: ");
        String password = scanner.nextLine();

        Administrator admin = bank.getAdmin();
        if (admin != null && admin.authenticate(password)) {
            System.out.println("Admin login successful!");
            adminMenu(admin);
        } else {
            System.out.println("Invalid Admin Name or Password. Please try again.");
        }
    }

    private static void customerMenu(Customer customer) {
        int choice;

        do {
            System.out.println("\nCustomer Menu\n---");
            System.out.println("1. View Account Details");
            System.out.println("2. Create new Account");
            System.out.println("3. Set Daily Spending Limit");
            System.out.println("4. Change Withdrawal Limit");
            System.out.println("5. Close Account");
            System.out.println("6. Create Transaction");
            System.out.println("7. Flag Transaction for Review");
            System.out.println("8. Logout");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAccountDetails(customer);
                    break;
                case 2:
                    createNewAccount(customer);
                    break;
                case 3:
                    setDailySpendingLimit(customer);
                    break;
                case 4:
                    changeWithdrawalLimit(customer);
                    break;
                case 5:
                    closeAccount(customer);
                    break;
                case 6:
                    createTransaction(customer);
                    break;
                case 7:
                    flagTransactionForReview(customer);
                    break;
                case 8:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 8);
    }

    private static void adminMenu(Administrator admin) {
        int choice;

        do {
            System.out.println("\nAdmin Menu\n---");
            System.out.println("1. View Transaction Reviews");
            System.out.println("2. Change Review Status");
            System.out.println("3. Logout");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    admin.viewReviews();
                    break;
                case 2:
                    changeReviewStatus(admin);
                    break;
                case 3:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 3);
    }

    private static void viewAccountDetails(Customer customer) {
        System.out.println("Customer Details:\n" + customer);
        System.out.println("Enter Account ID to view details: ");
        int accountId = scanner.nextInt();
        scanner.nextLine();

        Account account = customer.getAccountById(accountId);
        if (account != null) {
            account.showDetails();
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void changeWithdrawalLimit(Customer customer) {
        System.out.println("Enter Account ID: ");
        int accountId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter new withdrawal limit: ");
        double newLimit = scanner.nextDouble();
        scanner.nextLine();

        Account account = customer.getAccountById(accountId);
        if (account != null) {
            account.changeWithdrawalLimit(newLimit);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void setDailySpendingLimit(Customer customer) {
        System.out.println("Enter Account ID: ");
        int accountId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter new daily spending limit: ");
        double newLimit = scanner.nextDouble();
        scanner.nextLine();

        Account account = customer.getAccountById(accountId);
        if (account != null) {
            account.setDailySpendingLimit(newLimit);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void closeAccount(Customer customer) {
        System.out.println("Enter Account ID: ");
        int accountId = scanner.nextInt();
        scanner.nextLine();

        Account account = customer.getAccountById(accountId);
        if (account != null) {
            account.closeAccount();
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void createNewAccount(Customer customer) {
        System.out.println("Enter Account Type (1 for Checking, 2 for Savings): ");
        int accountType = scanner.nextInt();
        scanner.nextLine();

        int newAccountId = generateNewAccountId();

        switch (accountType) {
            case 1:
                customer.addAccount(new CheckingAccount(newAccountId));
                System.out.println("Checking account created with Account ID: " + newAccountId);
                break;
            case 2:
                customer.addAccount(new SavingsAccount(newAccountId));
                System.out.println("Savings account created with Account ID: " + newAccountId);
                break;
            default:
                System.out.println("Invalid account type selected.");
                break;
        }
    }

    private static int generateNewAccountId() {
        // ID generation logic for demonstration.
        return (int) (Math.random() * 1000) + 104; // Starting from 104 to avoid conflict with dummy data
    }

    private static void createTransaction(Customer customer) {
        System.out.println("Enter Transaction Type (1 for Deposit, 2 for Withdraw, 3 for Transfer): ");
        int transactionType = scanner.nextInt();
        scanner.nextLine();

        switch (transactionType) {
            case 1:
                createDepositTransaction(customer);
                break;
            case 2:
                createWithdrawTransaction(customer);
                break;
            case 3:
                createTransferTransaction(customer);
                break;
            default:
                System.out.println("Invalid transaction type selected.");
                break;
        }
    }

    private static void createDepositTransaction(Customer customer) {
        System.out.println("Enter Account ID: ");
        int accountId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        Account account = customer.getAccountById(accountId);
        if (account != null) {
            customer.createTransaction(new Deposit(account, amount));
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void createWithdrawTransaction(Customer customer) {
        System.out.println("Enter Account ID: ");
        int accountId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        Account account = customer.getAccountById(accountId);
        if (account != null) {
            customer.createTransaction(new Withdraw(account, amount));
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void createTransferTransaction(Customer customer) {
        System.out.println("Enter Source Account ID: ");
        int sourceAccountId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Destination Account ID: ");
        int destinationAccountId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        Account sourceAccount = customer.getAccountById(sourceAccountId);
        Account destinationAccount = bank.findAccountById(destinationAccountId); // find the destination account

        if (sourceAccount != null && destinationAccount != null) {
            customer.createTransaction(new Transfer(sourceAccount, destinationAccount, amount));
            System.out.println("Transfer successful.");
        } else {
            System.out.println("One or both accounts not found.");
        }
    }


    private static void flagTransactionForReview(Customer customer) {
        System.out.println("Enter Transaction ID to flag for review: ");
        int transactionId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter reason for review: ");
        String reason = scanner.nextLine();

        customer.flagTransactionForReview(transactionId, reason);
    }

    private static void changeReviewStatus(Administrator admin) {
        System.out.println("Enter Review ID: ");
        int reviewId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter new status: ");
        String status = scanner.nextLine();

        admin.changeReviewStatus(reviewId, status);
    }
}
