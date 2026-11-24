package hogwarts.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EnrollmentId implements Serializable {
    @Serial
    private static final long serialVersionUID = 6629075015160312808L;
    @Column(name = "person_enrollment", nullable = false)
    private Integer personEnrollment;

    @Column(name = "course_enrollment", nullable = false)
    private Integer courseEnrollment;

    public Integer getPersonEnrollment() {
        return personEnrollment;
    }

    public void setPersonEnrollment(Integer personEnrollment) {
        this.personEnrollment = personEnrollment;
    }

    public Integer getCourseEnrollment() {
        return courseEnrollment;
    }

    public void setCourseEnrollment(Integer courseEnrollment) {
        this.courseEnrollment = courseEnrollment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrollmentId entity = (EnrollmentId) o;
        return Objects.equals(this.personEnrollment, entity.personEnrollment) &&
                Objects.equals(this.courseEnrollment, entity.courseEnrollment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personEnrollment, courseEnrollment);
    }


}