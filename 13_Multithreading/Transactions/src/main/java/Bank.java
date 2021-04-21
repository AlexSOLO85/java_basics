import lombok.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
@EqualsAndHashCode
@ToString
public class Bank {

    private static final int CHECK_LIMIT_MONEY = 50000;
    private static final int CHECK_TIME = 1000;
    private static final int MIN_MONEY_ACCOUNT = 100000;
    private static final int MAX_MONEY_ACCOUNT = 200000;
    private static final int QTY_ACCOUNT = 100;

    private Map<String, Account> accounts;
    private final Random random = new Random();

    public Bank() {
        this.accounts = fillAccounts();
    }

    private synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount) {
        if (amount < CHECK_LIMIT_MONEY) {
            accounts.get(fromAccountNum).setBlocked(false);
            accounts.get(toAccountNum).setBlocked(false);
        }
        try {
            Thread.sleep(CHECK_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return random.nextBoolean();
    }

    protected void transfer(String fromAccountNum, String toAccountNum, long amount) {
        Account fromAccount = accounts.get(fromAccountNum);
        Account toAccount = accounts.get(toAccountNum);

        if (fromAccount.isBlocked() || toAccount.isBlocked()) {
            return;
        }

        transaction(amount, fromAccount, toAccount);

        if (amount > CHECK_LIMIT_MONEY) {
            if (isFraud(fromAccountNum, toAccountNum, amount)) {
                fromAccount.blockAccount();
                toAccount.blockAccount();
                System.out.printf("Счет отправителя: %s и счет получателя: %s заблокированы!\n",
                        fromAccountNum, toAccountNum);
            }
        }
    }

    private void transaction(long amount, Account fromAccount, Account toAccount) {
        if (fromAccount.withdrawMoney(amount)) {
            toAccount.putMoney(amount);
        }
    }

    protected long getBalance(String accountNum) {
        Account account = accounts.get(accountNum);
        return account.getMoney();
    }

    protected long getSumAllAccounts() {
        return accounts.values()
                .stream()
                .mapToLong(Account::getMoney)
                .sum();
    }

    private Map<String, Account> fillAccounts() {
        HashMap<String, Account> accounts = new HashMap<>();
        for (int i = 1; i <= QTY_ACCOUNT; i++) {
            int initialValue = ThreadLocalRandom.current().nextInt(MIN_MONEY_ACCOUNT, MAX_MONEY_ACCOUNT);
            Account account = new Account(String.valueOf(i), initialValue);
            accounts.put(String.valueOf(i), account);
        }
        return accounts;
    }
}