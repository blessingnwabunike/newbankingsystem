
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