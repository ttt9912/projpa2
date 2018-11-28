package ch6.p2_application_managed;

import ch6.p2_application_managed.entity.Person;
import ch6.util.jdbc.JdbcUtil;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerMethods_NonTransactionalDemo {
    private final JdbcUtil jdbcUtil = new JdbcUtil("jdbc:h2:~/dev/workspaces/projpa2/h2/ch6EmployeeDB", "sa", "");
    private final String FIND_ALL = "SELECT * FROM PERSON";

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch6EmployeePU");
    EntityManager em = emf.createEntityManager();


    @Test
    public void persistNonTransactional() {
        Person person = new Person(2L, "Peter");

        // persist without transaction - added to persistence ctx, but not saved to db
        em.persist(person);
        System.out.println("\nin persistence context: " + em.contains(person));
        System.out.println("saved to db: " + jdbcUtil.query(FIND_ALL));

        // new transaction  - persistence ctx is synchronized, entity is saved to db
        em.getTransaction().begin();
        em.getTransaction().commit();
        System.out.println("\nin persistence context: " + em.contains(person));
        System.out.println("saved to db: " + jdbcUtil.query(FIND_ALL));
    }
}
