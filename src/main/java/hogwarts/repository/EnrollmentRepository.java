package hogwarts.repository;

import hogwarts.entities.Course;
import hogwarts.entities.Enrollment;
import jakarta.persistence.EntityManager;

import java.util.List;

public class EnrollmentRepository implements Repository<Enrollment> {

    private final EntityManager entityManager;

    public EnrollmentRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Enrollment findById(int id) {
        return entityManager.find(Enrollment.class, id);
    }
    public List<Enrollment> findAll() {
        return entityManager.createQuery(
                "SELECT e FROM Enrollment e " +
                "JOIN FETCH e.personEnrollment " +
                "JOIN FETCH e.courseEnrollment", Enrollment.class)
                .getResultList();
    }

    @Override
    public void save(Enrollment enrollment) {
        entityManager.getTransaction().begin();
        entityManager.persist(enrollment);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Enrollment enrollment) {
        entityManager.getTransaction().begin();
        entityManager.remove(enrollment);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Enrollment enrollment) {
        entityManager.getTransaction().begin();
        entityManager.merge(enrollment);
        entityManager.getTransaction().commit();
    }

}
