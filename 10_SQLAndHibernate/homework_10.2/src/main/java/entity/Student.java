package entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer age;

    @Column(name = "registration_date")
    private Date registrationDate;

    @OneToMany(mappedBy = "student")
    private List<Subscription> subscriptions;

    public Student() {
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "-" + getId();
    }
}