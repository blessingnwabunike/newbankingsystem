import java.util.ArrayList;
import java.util.Scanner;

// Singleton class to manage customers and accounts
class Bank {
    private static Bank instance;
    private ArrayList<Customer> customers;

    private Bank() {
        customers = new ArrayList<>();
    }

    public static Bank getInstance() {
        if (instance == null) {
            instance = new Bank();
        }
        return instance;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer findCustomerById(int customerId) {
        for (Customer customer : customers) {
            if (customer.getId() == customerId) {
                return customer;
            }
        }
        return null;
    }
}

// State pattern interface and concrete states
interface AccountState {
    void viewDetails();
    void closeAccount();
}

class ActiveState implements AccountState {
    private Account account;

    public ActiveState(Account account) {
        this.account = account;
    }

    @Override
    public void viewDetails() {
        account.showDetails();
    }

    @Override
    public void closeAccount() {
        account.setState(account.getClosedState());
        System.out.println("Account has been closed.");
    }
}

class ClosedState implements AccountState {
    private Account account;

    public ClosedState(Account account) {
        this.account = account;
    }

    @Override
    public void viewDetails() {
        System.out.println("This account is closed.");
    }

    @Override
    public void closeAccount() {
        System.out.println("This account is already closed.");
    }
}

// Interface for Account with common methods
interface Account {
    void showDetails();
    void changeWithdrawalLimit(double newLimit);
    void setDailySpendingLimit(double newLimit);
    void closeAccount();
    void setState(AccountState state);
    AccountState getActiveState();
    AccountState getClosedState();
}

// Abstract parent class for accounts
abstract class BaseAccount implements Account {
    protected int accountId;
    protected double balance;
    protected double withdrawalLimit;
    protected double dailySpendingLimit;
    protected AccountState activeState;
    protected AccountState closedState;
    protected AccountState state;

    public BaseAccount(int accountId, double balance, double withdrawalLimit) {
        this.accountId = accountId;
        this.balance = balance;
        this.withdrawalLimit = withdrawalLimit;
        this.dailySpendingLimit = withdrawalLimit; // Default to the same as withdrawal limit
        this.activeState = new ActiveState(this);
        this.closedState = new ClosedState(this);
        this.state = activeState;
    }

    @Override
    public void showDetails() {
        System.out.println("Account ID: " + accountId);
        System.out.println("Balance: " + balance);
        System.out.println("Withdrawal Limit: " + withdrawalLimit);
        System.out.println("Daily Spending Limit: " + dailySpendingLimit);
    }

    @Override
    public void changeWithdrawalLimit(double newLimit) {
        withdrawalLimit = newLimit;
        System.out.println("New withdrawal limit set to: " + withdrawalLimit);
    }

    @Override
    public void setDailySpendingLimit(double newLimit) {
        dailySpendingLimit = newLimit;
        System.out.println("New daily spending limit set to: " + dailySpendingLimit);
    }

    @Override
    public void closeAccount() {
        state.closeAccount();
    }

    @Override
    public void setState(AccountState state) {
        this.state = state;
    }

    @Override
    public AccountState getActiveState() {
        return activeState;
    }

    @Override
    public AccountState getClosedState() {
        return closedState;
    }
}

// Child classes for Checking and Savings accounts
class CheckingAccount extends BaseAccount {
    public CheckingAccount(int accountId) {
        super(accountId, 500, 1000); // Default balance 500 and withdrawal limit 1000
    }
}

class SavingsAccount extends BaseAccount {
    public SavingsAccount(int accountId) {
        super(accountId, 100, 500); // Default balance 100 and withdrawal limit 500
    }
}

// Customer class
class Customer {
    private static int idCounter = 1000;
    private int id;
    private String name;
    private String contact;
    private String address;
    private String password;
    private ArrayList<Account> accounts;

    public Customer(String name, String password, String contact, String address) {
        this.id = idCounter++;
        this.name = name;
        this.password = password;
        this.accounts = new ArrayList<>();
        this.contact = contact;
        this.address = address;
    }

    
    public static int getIdCounter() {
		return idCounter;
	}


	public String getContact() {
		return contact;
	}


	public String getAddress() {
		return address;
	}


	public String getPassword() {
		return password;
	}


	public ArrayList<Account> getAccounts() {
		return accounts;
	}


	public int getId() {
        return id;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void viewAccounts() {
        for (Account account : accounts) {
            account.showDetails();
        }
    }

    public Account getAccountById(int accountId) {
        for (Account account : accounts) {
            if (((BaseAccount) account).accountId == accountId) {
                return account;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }


	@Override
	public String toString() {
		return "Customer ID: " + id + "\nName: " + name + "\nContact number: " + contact + "   Address: " + address+"\n";
	}
    
}

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
            System.out.println("3. Exit");
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
                    System.out.println("Thank you for using the Banking System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 3);
    }

    private static void addDummyData() {
        Customer customer1 = new Customer("Jason", "asd123","0123 4567","1 Royal Street");
        customer1.addAccount(new CheckingAccount(101));
        customer1.addAccount(new SavingsAccount(102));
        bank.addCustomer(customer1);

        Customer customer2 = new Customer("Bobby", "qwe123","4567 1234","1/8 Edmilson Avenue");
        customer2.addAccount(new CheckingAccount(103));
        bank.addCustomer(customer2);
    }

    private static void createNewCustomer() {
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        String password="";
        while(password.length()<6) {
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

    private static void customerMenu(Customer customer) {
        int choice;

        do {
            System.out.println("\nCustomer Menu\n---");
            System.out.println("1. View Account Details");
            System.out.println("2. Creatte new Account");
            System.out.println("3. Set Daily Spending Limit");
            System.out.println("4. Change Withdrawal Limit");
            System.out.println("5. Close Account");
            System.out.println("6. Logout");
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
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 6);
    }

    private static void viewAccountDetails(Customer customer) {
    	System.out.println("Customer Details:\n"+customer);
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
}
