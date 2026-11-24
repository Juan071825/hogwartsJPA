package hogwarts.controllers;

import hogwarts.entities.Person;
import hogwarts.repository.Repository;
import hogwarts.view.PersonView;

public class PersonController {

    private final Repository<Person> repository;
    private final PersonView view;

    public PersonController(Repository<Person> repository, PersonView view) {
        this.repository = repository;
        this.view = view;
    }

    public void showAll() {
        view.showAll(repository.findAll());
    }

}
