package hogwarts;

import hogwarts.entities.*;
import hogwarts.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import hogwarts.controllers.PersonController;
import hogwarts.view.PersonView;

public class App {

    public static void main(String[] args) {

        try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
             EntityManager entityManager = entityManagerFactory.createEntityManager()) {

            Repository<Person> personRepository = new PersonRepository(entityManager);
            PersonView view = new PersonView(System.out);
            PersonController controller = new PersonController(personRepository, view);


                Person james = new Person();
                james.setId(300);
                james.setFirstName("James Sirius");
                james.setLastName("Potter");
                Person albus = new Person();
                albus.setId(301);
                albus.setFirstName("ALbus Severus");
                albus.setLastName("Potter");
                Person lily = new Person();
                lily.setId(303);
                lily.setFirstName("Lily Luna");
                lily.setLastName("Potter");

                HouseRepository houseRepository = new HouseRepository(entityManager);
                House slytherin = houseRepository.findByName("Slytherin");
                james.setHouse(slytherin);
                albus.setHouse(slytherin);
                lily.setHouse(slytherin);

            if (personRepository.findById(300) == null
               || personRepository.findById(301) == null
               || personRepository.findById(303) == null) {
                    personRepository.save(james);
                    personRepository.save(albus);
                    personRepository.save(lily);
            }

            CourseRepository courseRepository = new CourseRepository(entityManager);
            Course astronomy = courseRepository.findByName("Astronomy");

            EnrollmentId id_enrollment = new EnrollmentId();
            id_enrollment.setPersonEnrollment(lily.getId());
            id_enrollment.setCourseEnrollment(astronomy.getId());

            Enrollment lily_astronomy = new Enrollment();
            lily_astronomy.setId(id_enrollment);
            lily_astronomy.setPersonEnrollment(lily);
            lily_astronomy.setCourseEnrollment(astronomy);



            EnrollmentRepository enrollmentRepository = new EnrollmentRepository(entityManager);
            enrollmentRepository.save(lily_astronomy);

            controller.showAll();


        }
    }

}
