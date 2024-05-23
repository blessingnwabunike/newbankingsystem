import java.util.ArrayList;

// Abstract parent class for accounts
abstract class BaseAccount implements Account {
    protected int accountId;
    protected double balance;
    protected double withdrawalLimit;
    protected double dailySpendingLimit;
    protected AccountState activeState;
    protected AccountState closedState;
    protected AccountState state;
    protected ArrayList<Transaction> transactions;

    public BaseAccount(int accountId, double balance, double withdrawalLimit) {
        this.accountId = accountId;
        this.balance = balance;
        this.withdrawalLimit = withdrawalLimit;
        this.dailySpendingLimit = withdrawalLimit; // Default to the same as withdrawal limit
        this.activeState = new ActiveState(this);
        this.closedState = new ClosedState(this);
        this.state = activeState;
        this.transactions = new ArrayList<>();
    }

    public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getWithdrawalLimit() {
		return withdrawalLimit;
	}

	public void setWithdrawalLimit(double withdrawalLimit) {
		this.withdrawalLimit = withdrawalLimit;
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}

	public double getDailySpendingLimit() {
		return dailySpendingLimit;
	}

	public AccountState getState() {
		return state;
	}

	public void setActiveState(AccountState activeState) {
		this.activeState = activeState;
	}

	public void setClosedState(AccountState closedState) {
		this.closedState = closedState;
	}

	@Override
    public void showDetails() {
        System.out.println("\n---\nAccount ID: " + accountId);
        System.out.println("Balance: " + balance);
        System.out.println("Withdrawal Limit: " + withdrawalLimit);
        System.out.println("Daily Spending Limit: " + dailySpendingLimit);
        System.out.println("Transactions:");
        for (Transaction transaction : transactions) {
            transaction.showDetails();
        }
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

    @Override
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}