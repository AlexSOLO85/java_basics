package entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer salary;

    private Integer age;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

    public Teacher() {
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "-" + getId();
    }
}