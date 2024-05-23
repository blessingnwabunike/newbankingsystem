// Interface for Account with common methods
interface Account {
    void showDetails();
    void changeWithdrawalLimit(double newLimit);
    void setDailySpendingLimit(double newLimit);
    void closeAccount();
    void setState(AccountState state);
    AccountState getActiveState();
    AccountState getClosedState();
    void addTransaction(Transaction transaction);
}