package ch4.entitybasics;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersonPU");
        EntityManager em = emf.createEntityManager();

        PersonService personService = new PersonService(em);

        // Create and persist an Entity
        em.getTransaction().begin();
        Person person = personService.createEmployee(159, "John Doe", 45000, "456789");
        em.getTransaction().commit();
        System.out.println("Persisted: " + person);

        // find all
        List<Person> allPeople = personService.findAllEmployees();
        System.out.println("All Persons: " + allPeople);

        em.close();
        emf.close();
    }
}
