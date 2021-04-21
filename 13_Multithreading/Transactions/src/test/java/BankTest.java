import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BankTest {

    private Bank bank;
    private final HashMap<String, Account> accounts = new HashMap<>();
    private Account a1;
    private Account a2;
    private Account a3;
    private Account a4;
    private Account a5;

    @Before
    public void setUp() {
        bank = new Bank();

        a1 = new Account("1", 50000);
        a2 = new Account("2", 50000);
        a3 = new Account("3", 50000);
        a4 = new Account("4", 60000);
        a5 = new Account("5", 60000);

        accounts.put("1", a1);
        accounts.put("2", a2);
        accounts.put("3", a3);
        accounts.put("4", a4);
        accounts.put("5", a5);

        bank.setAccounts(accounts);
    }

    @Test
    public void test_transfer_one_thread() {
        bank.transfer("1", "2", 1000);
        long actualFrom = a1.getMoney();
        long expectedFrom = 49000;
        long actualTo = a2.getMoney();
        long expectedTo = 51000;

        assertEquals(expectedFrom, actualFrom);
        assertEquals(expectedTo, actualTo);
    }

    @Test
    public void test_transfer_many_thread() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                bank.transfer("1", "2", 1000);
                bank.transfer("1", "3", 1000);
                bank.transfer("3", "1", 1000);
                bank.transfer("3", "2", 1000);
                bank.transfer("2", "1", 1000);
                bank.transfer("2", "3", 1000);
                bank.transfer("1", "3", 1000);
                bank.transfer("2", "3", 1000);
            }).start();
        }
        Thread.sleep(100);
        long actualA1 = a1.getMoney();
        long expectedA1 = 40000;
        long actualA2 = a2.getMoney();
        long expectedA2 = 40000;
        long actualA3 = a3.getMoney();
        long expectedA3 = 70000;

        assertEquals(expectedA1, actualA1);
        assertEquals(expectedA2, actualA2);
        assertEquals(expectedA3, actualA3);
    }

    @Test
    public void test_transfer_many_thread_revers() throws InterruptedException {
        for (int i = 0; i <= 30; i++) {
            Thread t = new Thread(() -> {
                bank.transfer("1", "2", 1000);
                bank.transfer("2", "1", 1000);
            });
            t.start();
            t.join();
        }
        long actualFrom = a1.getMoney();
        long expectedFrom = 50000;
        long actualTo = a2.getMoney();
        long expectedTo = 50000;

        assertEquals(expectedFrom, actualFrom);
        assertEquals(expectedTo, actualTo);
    }

    @Test
    public void test_transfer_block() {
        long balance = a1.getMoney();
        a1.setBlocked(true);
        bank.transfer("1", "2", 1000);
        bank.transfer("1", "2", 1000);
        bank.transfer("1", "2", 1000);
        long actualA1 = bank.getBalance("1");

        assertEquals(balance, actualA1);
    }

    @Test
    public void test_if_fraud() throws InterruptedException {
        for (int i = 0; i < 4; i++) {
            Thread t = new Thread(() -> {
                bank.transfer("4", "5", 51000);
                bank.transfer("5", "4", 51000);
            });
            t.start();
            t.join();
        }
        boolean actualFrom = a4.isBlocked();
        boolean actualTo = a5.isBlocked();

        assertTrue(actualFrom);
        assertTrue(actualTo);
    }

    @Test
    public void test_transfer_over_limit() {
        bank.transfer("1", "2", 100000);
        long actualFrom = a1.getMoney();
        long expectedFrom = 50000;
        long actualTo = a2.getMoney();
        long expectedTo = 50000;

        assertEquals(expectedFrom, actualFrom);
        assertEquals(expectedTo, actualTo);
    }

    @Test
    public void test_double_withdrawal() throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            Thread t = new Thread(() -> {
                try {
                    bank.transfer("1", "2", 50000);
                    Thread.sleep(100);
                    bank.transfer("1", "2", 50000);
                    Thread.sleep(100);
                    bank.transfer("1", "2", 50000);
                    Thread.sleep(100);
                    bank.transfer("1", "2", 50000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t.start();
            t.join();

            long expected = a1.getMoney();
            long actual = 0;

            assertEquals(expected, actual);
        }
    }
}