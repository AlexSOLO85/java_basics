import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Service {

    private static final int THREADS = 5;
    private static final int THREAD_COUNT = 10;
    private static final int MIN_TRANSFER_MONEY = 10000;
    private static final int MAX_TRANSFER_MONEY = 55000;
    private static final CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);

    public void getResult() {
        Bank bank = new Bank();
        System.out.printf("Начальный баланс: %s руб.\n", bank.getSumAllAccounts());

        ExecutorService service = Executors.newFixedThreadPool(THREADS);

        for (int i = 0; i < THREAD_COUNT; i++) {
            service.execute(() -> {
                for (int j = 1; j < 10; j++) {
                    int amount = ThreadLocalRandom.current().nextInt(MIN_TRANSFER_MONEY, MAX_TRANSFER_MONEY);
                    String accountNum = String.valueOf(Integer.parseInt(String.valueOf(j)));
                    bank.transfer(accountNum,
                            String.valueOf(Integer.parseInt(String.valueOf(j)) + 1), amount);
                    if (j == 9) {
                        break;
                    }
                    System.out.printf("Счет: %s, остаток на счете: %s руб.\n", accountNum, bank.getBalance(accountNum));
                }
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();
        System.out.printf("Баланс после всех транзакций: %s руб.\n", bank.getSumAllAccounts());
    }
}