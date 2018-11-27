package ch6.p2_application_managed.service;

import ch6.p2_application_managed.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeService {
    protected EntityManager em;

    public EmployeeService(EntityManager em) {
        this.em = em;
    }

    public Person createEmployee(String name) {
        Person person = new Person();
        person.setName(name);

        em.persist(person);
        return person;
    }

    public Person findEmployee(int id) {
        return em.find(Person.class, id);
    }

    public List<Person> findAllEmployees() {
        TypedQuery<Person> query = em.createQuery("SELECT e FROM Person e", Person.class);
        return query.getResultList();
    }

    public void deleteEmployee(Person person) {
        em.remove(person);
    }

    public void deleteEmployee(int id) {
        Person person = findEmployee(id);
        if (person != null) {
            em.remove(person);
        }
    }
}
