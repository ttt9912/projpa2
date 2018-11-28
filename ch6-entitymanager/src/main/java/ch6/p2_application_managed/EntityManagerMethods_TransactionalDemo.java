package ch6.p2_application_managed;

import ch6.p2_application_managed.entity.Person;
import ch6.util.jdbc.JdbcUtil;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerMethods_TransactionalDemo {
    private final JdbcUtil jdbcUtil = new JdbcUtil("jdbc:h2:~/dev/workspaces/projpa2/h2/ch6PersonDB", "sa", "");
    private final String FIND_ALL = "SELECT * FROM PERSON";

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch6PersonPU");
    EntityManager em = emf.createEntityManager();

    @Test
    public void persistTransactional() {
        Person person = new Person(1L, "Paul");

        em.getTransaction().begin();

        em.persist(person); // put to persistence ctx
        System.out.println("\nin persistence context: " + em.contains(person));
        System.out.println("saved to db: " + jdbcUtil.query("SELECT * FROM PERSON"));

        em.getTransaction().commit(); // saved to db

        System.out.println("\nin persistence context: " + em.contains(person));
        System.out.println("saved to db: " + jdbcUtil.query("SELECT * FROM PERSON"));
    }
}
