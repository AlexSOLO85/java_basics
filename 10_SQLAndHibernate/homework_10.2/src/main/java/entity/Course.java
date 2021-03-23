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
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer duration;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private CourseType type;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "students_count")
    private Integer studentsCount;

    private Integer price;

    @Column(name = "price_per_hour")
    private Float pricePerHour;

    @OneToMany(mappedBy = "course")
    private List<Subscription> subscriptions;

    public Course() {
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "-" + getId();
    }
}