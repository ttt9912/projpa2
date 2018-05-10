import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class PersonService {
    protected EntityManager em;

    public PersonService(EntityManager em) {
        this.em = em;
    }

    public Person createEmployee(int id, String name, long salary, String phoneNum) {
        Person person = new Person(id);
        person.setName(name);
        person.setSalary(salary);
        person.setPhoneNum(phoneNum);

        em.persist(person);
        return person;
    }

    public Person findEmployee(int id) {
        return em.find(Person.class, id);
    }

    public List<Person> findAllEmployees() {
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
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
