package hogwarts.view;

import hogwarts.entities.Person;
import java.io.PrintStream;
import java.util.List;

public class PersonView {

    private final PrintStream out;

    public PersonView(PrintStream out) {
        this.out = out;
    }

    public void showAll(List<Person> persons) {
        if (persons.isEmpty()) {
            out.println("No hay personajes");
            return;
        }
        for (Person p : persons) {
            out.println(p.getId() + " | " + p.getFirstName());
        }
    }

    public void showError(String message) {
        out.println("Error: " + message);
    }

}
