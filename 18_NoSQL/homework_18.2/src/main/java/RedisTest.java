import threads.ThreadDonateUsers;
import threads.ThreadListUsers;
import utils.RedisStorage;

public class RedisTest {

    public static void main(String[] args) {
        RedisStorage.init();
        new ThreadListUsers().start();
        new ThreadDonateUsers().start();
    }
}