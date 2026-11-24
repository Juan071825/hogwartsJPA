package hogwarts.repository;

import hogwarts.entities.Person;
import jakarta.persistence.EntityManager;

import java.util.List;


public class PersonRepository implements Repository<Person> {

    private final EntityManager entityManager;

    public PersonRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Person findById(int id) {
        return entityManager.find(Person.class, id);
    }
    public List<Person> findAll() {
        return entityManager.createQuery("SELECT p FROM Person p JOIN FETCH p.house", Person.class)
                .getResultList();
    }

    @Override
    public void save(Person person) {
        entityManager.getTransaction().begin();
        entityManager.persist(person);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Person person) {
        entityManager.getTransaction().begin();
        entityManager.remove(person);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Person person) {
        entityManager.getTransaction().begin();
        entityManager.merge(person);
        entityManager.getTransaction().commit();
    }

}
