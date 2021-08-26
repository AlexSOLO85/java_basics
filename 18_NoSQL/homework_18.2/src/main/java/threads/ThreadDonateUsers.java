package threads;

import utils.RedisStorage;

import java.util.Random;

public class ThreadDonateUsers extends Thread {
    private final static int INITIAL_BOUNDS = 1;
    private final static int FINAL_BOUNDS = 20;

    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        while (true) {
            Random random = new Random();
            synchronized (RedisStorage.class) {
                RedisStorage.donate(String.valueOf(random.nextInt(FINAL_BOUNDS) + INITIAL_BOUNDS));
            }
        }
    }
}