class Transfer extends BaseTransaction {
    private Account destinationAccount;

    public Transfer(Account account, Account destinationAccount, double amount) {
        super(account, amount);
        this.destinationAccount = destinationAccount;
    }

    @Override
    public void execute() {
        BaseAccount sourceAccount = (BaseAccount) account;
        BaseAccount destAccount = (BaseAccount) destinationAccount;

        if (sourceAccount.balance >= amount) {
            if (amount <= sourceAccount.withdrawalLimit) {
                sourceAccount.balance -= amount;
                destAccount.balance += amount;
                sourceAccount.addTransaction(this);
                destAccount.addTransaction(this);
                System.out.println("Transfer of " + amount + " from Account ID " + sourceAccount.accountId + " to Account ID " + destAccount.accountId + " executed.");
            } else {
                System.out.println("Transfer amount exceeds the withdrawal limit.");
            }
        } else {
            System.out.println("Insufficient balance for transfer.");
        }
    }

    @Override
    public void showDetails() {
        System.out.println("\nTransaction ID: " + this.transactionId);
        System.out.println("Transaction Type: Transfer");
        System.out.println("Source Account ID: " + ((BaseAccount) account).getAccountId());
        System.out.println("Destination Account ID: " + ((BaseAccount) destinationAccount).getAccountId());
        System.out.println("Amount: " + amount);
    }

    @Override
    public String getType() {
        return "Transfer";
    }
}