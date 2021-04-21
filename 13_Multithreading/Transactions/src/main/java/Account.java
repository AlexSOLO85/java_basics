import lombok.*;

@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
@EqualsAndHashCode
@ToString
public class Account {

    private long money;
    private String accNumber;
    private boolean isBlocked = false;

    public Account(String accNumber, long money) {
        this.accNumber = accNumber;
        this.money = money;
    }

    protected synchronized boolean withdrawMoney(long money) {
        if (this.money >= money) {
            this.money -= money;
            return true;
        }
        return false;
    }

    protected synchronized void putMoney(long money) {
        this.money += money;
    }

    protected synchronized void blockAccount() {
        isBlocked = true;
    }
}