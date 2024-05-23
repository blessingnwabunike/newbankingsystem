import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class BankingSystemTest {
    private Bank bank;
    private Customer customer1;
    private Customer customer2;

    @BeforeEach
    public void setup() {
        bank = Bank.getInstance();
        bank.addCustomer(new Customer("TestUser1", "test123", "1234567890", "Test Address 1"));
        customer1 = bank.findCustomerById(Customer.getIdCounter() - 1);

        bank.addCustomer(new Customer("TestUser2", "test456", "0987654321", "Test Address 2"));
        customer2 = bank.findCustomerById(Customer.getIdCounter() - 1);

        customer1.addAccount(new CheckingAccount(201));
        customer1.addAccount(new SavingsAccount(202));

        customer2.addAccount(new CheckingAccount(203));
    }

    @Test
    public void testCustomerCreation() {
        Customer customer = new Customer("John Doe", "password", "1112223333", "123 Elm Street");
        assertNotNull(customer);
        assertEquals("John Doe", customer.getName());
        assertEquals("password", customer.getPassword());
        assertEquals("1112223333", customer.getContact());
        assertEquals("123 Elm Street", customer.getAddress());
    }

    @Test
    public void testAccountCreation() {
        Account checkingAccount = new CheckingAccount(301);
        assertNotNull(checkingAccount);
        assertEquals(500, ((BaseAccount) checkingAccount).balance);
        assertEquals(1000, ((BaseAccount) checkingAccount).withdrawalLimit);

        Account savingsAccount = new SavingsAccount(302);
        assertNotNull(savingsAccount);
        assertEquals(100, ((BaseAccount) savingsAccount).balance);
        assertEquals(500, ((BaseAccount) savingsAccount).withdrawalLimit);
    }

    @Test
    public void testCustomerLogin() {
        assertTrue(customer1.authenticate("test123"));
        assertFalse(customer1.authenticate("wrongpassword"));
    }

    @Test
    public void testChangeWithdrawalLimit() {
        Account account = customer1.getAccountById(201);
        account.changeWithdrawalLimit(2000);
        assertEquals(2000, ((BaseAccount) account).withdrawalLimit);
    }

    @Test
    public void testCloseAccount() {
        Account account = customer1.getAccountById(201);
        account.closeAccount();
        assertEquals(((BaseAccount) account).closedState, ((BaseAccount) account).state);
    }
}
