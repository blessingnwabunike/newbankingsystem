class Withdraw extends BaseTransaction {
    public Withdraw(Account account, double amount) {
        super(account, amount);
    }

    @Override
    public void execute() {
        BaseAccount baseAccount = (BaseAccount) account;

        if (baseAccount.balance >= amount) {
            if (amount <= baseAccount.withdrawalLimit) {
                baseAccount.balance -= amount;
                baseAccount.addTransaction(this);
                System.out.println("Withdrawal of " + amount + " executed.");
            } else {
                System.out.println("Withdrawal amount exceeds the withdrawal limit.");
            }
        } else {
            System.out.println("Insufficient balance for withdrawal.");
        }
    }

    @Override
    public void showDetails() {
        System.out.println("\nTransaction ID: " + this.transactionId);
        System.out.println("Transaction Type: Withdraw");
        System.out.println("Account ID: " + ((BaseAccount) account).getAccountId());
        System.out.println("Amount: " + amount);
    }

    @Override
    public String getType() {
        return "Withdraw";
    }
}