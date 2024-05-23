// Transaction interface
interface Transaction {
    void execute();
    void showDetails();
    int getId();
    String getType();
    double getAmount();
    
}