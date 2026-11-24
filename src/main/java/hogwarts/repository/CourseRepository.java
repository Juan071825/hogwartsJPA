package hogwarts.repository;

import hogwarts.entities.Course;
import hogwarts.entities.House;
import hogwarts.entities.Person;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CourseRepository implements Repository<Course>{

    private final EntityManager entityManager;

    public CourseRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Course findById(int id) {
        return entityManager.find(Course.class, id);
    }
    public List<Course> findAll() {
        return entityManager.createQuery("SELECT c FROM Course c JOIN FETCH c.teacher", Course.class)
                .getResultList();
    }

    public Course findByName(String name) {
        return entityManager.createQuery("SELECT c FROM Course c WHERE c.name = :name", Course.class)
                .setParameter("name", name)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Course course) {
        entityManager.getTransaction().begin();
        entityManager.persist(course);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Course course) {
        entityManager.getTransaction().begin();
        entityManager.remove(course);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Course course) {
        entityManager.getTransaction().begin();
        entityManager.merge(course);
        entityManager.getTransaction().commit();
    }

}
