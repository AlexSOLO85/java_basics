package utils;

import org.redisson.Redisson;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;

import java.util.Date;

public final class RedisStorage {

    private RedisStorage() {
    }

    private static RedissonClient redissonClient;
    private final static String KEY = "users";
    private final static String REDIS_ADDRESS_BASE = "redis://127.0.0.1:6379";
    private final static Double SCORE_INC = 0.1;
    private final static int ONE_SECOND = 1000;
    private final static int TIME_MS = 100;
    private final static int INITIAL_CAPACITY = 1;
    private final static int FINAL_CAPACITY = 20;

    private static RScoredSortedSet<String> users;

    private static double getTimeShift() {
        return ((double) new Date().getTime()) / ONE_SECOND;
    }

    public static void init() {
        Config config = new Config();
        config
                .useSingleServer()
                .setAddress(REDIS_ADDRESS_BASE);
        try {
            redissonClient = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            System.out.println("Не удалось подключиться к Redis");
            System.out.println(Exc.getMessage());
        }
        users = redissonClient.getScoredSortedSet(KEY, StringCodec.INSTANCE);
        addUsers();
    }

    public static void donate(String elem) {
        Double firstScore = users.firstScore();
        Double currentScore = users.getScore(elem);
        System.out.printf("> Пользователь %s оплатил платную услугу" + System.lineSeparator(), elem);
        users.addScore(elem, -currentScore + firstScore - SCORE_INC);
    }

    public static void listUsers() {
        try {
            for (String user : users) {
                Thread.sleep(TIME_MS);
                System.out.printf("- На главной странице показываем пользователя %s" + System.lineSeparator(), user);
                users.addScoreAsync(user, SCORE_INC);
            }
            Thread.sleep(ONE_SECOND);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void addUsers() {
        for (int i = INITIAL_CAPACITY; i <= FINAL_CAPACITY; i++) {
            users.add(getTimeShift(), String.valueOf(i));
        }
    }
}