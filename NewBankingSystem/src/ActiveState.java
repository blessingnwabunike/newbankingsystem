
class ActiveState implements AccountState {
    private Account account;

    public ActiveState(Account account) {
        this.account = account;
    }

    @Override
    public void viewDetails() {
        account.showDetails();
    }

    @Override
    public void closeAccount() {
        account.setState(account.getClosedState());
        System.out.println("Account has been closed.");
    }
}