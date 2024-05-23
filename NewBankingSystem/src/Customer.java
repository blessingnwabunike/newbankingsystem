import java.util.ArrayList;

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

    public static int getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(int idCounter) {
		Customer.idCounter = idCounter;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
        return name;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public void createTransaction(Transaction transaction) {
        transaction.execute();
    }

    public void flagTransactionForReview(int transactionId, String reason) {
        for (Account account : accounts) {
            for (Transaction transaction : ((BaseAccount) account).transactions) {
                if (transaction.getId() == transactionId) {
                    TransactionReview review = new TransactionReview(transactionId, reason, java.time.LocalDateTime.now().toString(),transaction);
                    Bank.getInstance().getAdmin().addReview(review);
                    System.out.println("Transaction flagged for review.");
                    return;
                }
            }
        }
        System.out.println("Transaction not found.");
    }

    @Override
    public String toString() {
        return "Customer ID: " + id + "\nName: " + name + "\nContact number: " + contact + "   Address: " + address + "\n";
    }
}