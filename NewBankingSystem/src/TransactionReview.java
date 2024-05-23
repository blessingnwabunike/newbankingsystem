// TransactionReview class
class TransactionReview {
    private static int idCounter = 1;
    private int reviewId;
    private int transactionId;
    private String reason;
    private String timestamp;
    private String status;
    private Transaction transaction;

    public TransactionReview(int transactionId, String reason, String timestamp, Transaction transaction) {
        this.reviewId = idCounter++;
        this.transactionId = transactionId;
        this.reason = reason;
        this.timestamp = timestamp;
        this.status = "Pending";
        this.transaction = transaction;
    }

    public int getReviewId() {
        return reviewId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public String getReason() {
        return reason;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void showDetails() {
        System.out.println("\n---\nReview ID: " + reviewId + ", Transaction ID: " + transactionId + ", Reason: " + reason + ", Timestamp: " + timestamp + ", Status: " + status);
        System.out.println("\nTransaction Details:");
        transaction.showDetails();
    }
}