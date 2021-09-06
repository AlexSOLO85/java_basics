package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Voter {

    private final String name;
    private final Date birthDay;

    public Voter(String name, Date birthDay) {
        this.name = name;
        this.birthDay = birthDay;
    }

    public String toString() {
        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy.MM.dd");
        return name + " (" + dayFormat.format(birthDay) + ")";
    }

    public String getName() {
        return name;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Voter)) {
            return false;
        }

        Voter voter = (Voter) o;

        if (!getName().equals(voter.getName())) {
            return false;
        }
        return getBirthDay().equals(voter.getBirthDay());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getBirthDay().hashCode();
        return result;
    }
}