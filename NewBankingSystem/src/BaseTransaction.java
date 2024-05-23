// Abstract parent class for transactions
abstract class BaseTransaction implements Transaction {
    protected int transactionId;
    protected static int idCounter = 1;
    protected Account account;
    protected double amount;
    

    public BaseTransaction(Account account, double amount) {
        this.transactionId = idCounter++;
        this.account = account;
        this.amount = amount;
    }

    @Override
    public int getId() {
        return transactionId;
    }

    @Override
    public double getAmount() {
        return amount;
    }
    public abstract void showDetails();
}
