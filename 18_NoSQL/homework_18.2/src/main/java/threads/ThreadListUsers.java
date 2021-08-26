package threads;

import utils.RedisStorage;

public class ThreadListUsers extends Thread {

    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        while (true) {
            synchronized (RedisStorage.class) {
                RedisStorage.listUsers();
            }
        }
    }
}