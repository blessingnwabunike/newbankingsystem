
class Deposit extends BaseTransaction {
    public Deposit(Account account, double amount) {
        super(account, amount);
    }

    @Override
    public void execute() {
        ((BaseAccount) account).balance += amount;
        account.addTransaction(this);
        System.out.println("Deposit of " + amount + " executed.");
    }

    @Override
    public String getType() {
        return "Deposit";
    }
    
    @Override
    public void showDetails() {
    	System.out.println("\nTransaction ID: "+this.transactionId);
        System.out.println("Transaction Type: Deposit");
        System.out.println("Account ID: " + ((BaseAccount) account).getAccountId());
        System.out.println("Amount: " + amount);
    }
}