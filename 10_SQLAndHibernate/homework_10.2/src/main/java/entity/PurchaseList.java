package entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class PurchaseList {
    @EmbeddedId
    Id id;

    @Column(name = "student_name", updatable = false, insertable = false)
    private String studentName;

    @Column(name = "course_name", updatable = false, insertable = false)
    private String courseName;

    private Integer price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public PurchaseList() {
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
        @Column(name = "student_name")
        private String studentName;

        @Column(name = "course_name")
        private String courseName;

        public Id() {
        }

        @Override
        public String toString() {
            return this.getClass().getSimpleName();
        }
    }
}