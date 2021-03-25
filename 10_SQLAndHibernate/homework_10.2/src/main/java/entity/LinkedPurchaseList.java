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
@Table(name = "linkedpurchaselist")
public class LinkedPurchaseList {
    @EmbeddedId
    private Id id;

    @ManyToOne
    @JoinColumn(name = "student_id", updatable = false, insertable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", updatable = false, insertable = false)
    private Course course;

    @Column(name = "price")
    private Integer price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public LinkedPurchaseList() {
    }

    public LinkedPurchaseList(Id id,
                              Student student,
                              Course course,
                              int price,
                              Date subscriptionDate) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.price = price;
        this.subscriptionDate = subscriptionDate;
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

        public Id(int studentId, int courseId) {
            this.studentId = studentId;
            this.courseId = courseId;
        }
    }
}