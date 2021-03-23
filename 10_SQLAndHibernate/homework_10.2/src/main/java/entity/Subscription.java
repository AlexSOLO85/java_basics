package entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "subscriptions")
public class Subscription {
    @EmbeddedId
    Id id;

    @ManyToOne
    @JoinColumn(name = "student_id", updatable = false, insertable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", updatable = false, insertable = false)
    private Course course;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public Subscription() {
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "-" + getId();
    }

    @Getter
    @Setter
    @EqualsAndHashCode
    @Embeddable
    public static class Id implements Serializable {
        @Column(name = "student_id")
        private int studentId;

        @Column(name = "course_id")
        private int courseId;

        public Id() {
        }

        @Override
        public String toString() {
            return this.getClass().getSimpleName();
        }
    }
}