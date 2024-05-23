
// Child classes for Checking and Savings accounts
class CheckingAccount extends BaseAccount {
    public CheckingAccount(int accountId) {
        super(accountId, 500, 1000); // Default balance 500 and withdrawal limit 1000
    }
}