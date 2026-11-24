package hogwarts.repository;

import jakarta.persistence.EntityManager;

import hogwarts.entities.House;

import java.util.List;

public class HouseRepository implements Repository<House>{

    private final EntityManager entityManager;

    public HouseRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public House findById(int id) {
        return entityManager.find(House.class, id);
    }

    public House findByName(String name) {
        return entityManager.createQuery("SELECT h FROM House h WHERE h.name = :name", House.class)
                .setParameter("name", name)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    public List<House> findAll() {
        return entityManager.createQuery("SELECT h FROM House h JOIN FETCH h.headTeacher", House.class)
                .getResultList();
    }

    @Override
    public void save(House house) {
        entityManager.getTransaction().begin();
        entityManager.persist(house);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(House house) {
        entityManager.getTransaction().begin();
        entityManager.remove(house);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(House house) {
        entityManager.getTransaction().begin();
        entityManager.merge(house);
        entityManager.getTransaction().commit();
    }

}
