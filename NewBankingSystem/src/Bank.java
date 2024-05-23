import java.util.ArrayList;

// Singleton class to manage customers and accounts
class Bank {
    private static Bank instance;
    private ArrayList<Customer> customers;
    private Administrator admin;

    private Bank() {
        customers = new ArrayList<>();
        admin = new Administrator("admin", "admin123"); // Dummy admin
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

    public Administrator getAdmin() {
        return admin;
    }
    
    public Account findAccountById(int accountId) {
        for (Customer customer : customers) {
            Account account = customer.getAccountById(accountId);
            if (account != null) {
                return account;
            }
        }
        return null;
    }

}