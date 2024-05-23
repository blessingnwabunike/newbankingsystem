import java.util.HashMap;
import java.util.Map;

// Admin class
class Administrator {
    private String username;
    private String password;
    private Map<Integer, TransactionReview> reviews;

    public Administrator(String name, String password) {
        this.username = name;
        this.password = password;
        this.reviews = new HashMap<>();
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public void addReview(TransactionReview review) {
        reviews.put(review.getReviewId(), review);
    }

    public void viewReviews() {
        for (TransactionReview review : reviews.values()) {
            review.showDetails();
        }
    }

    public void changeReviewStatus(int reviewId, String status) {
        if (reviews.containsKey(reviewId)) {
            reviews.get(reviewId).setStatus(status);
            System.out.println("Review status updated.");
        } else {
            System.out.println("Review ID not found.");
        }
    }
}