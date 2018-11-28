package ch6.p2_application_managed;

import ch6.entities.Employee;
import ch6.util.jdbc.JdbcUtil;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerMethods_NonTransactionalDemo {
    private final JdbcUtil jdbcUtil = new JdbcUtil("jdbc:h2:~/dev/workspaces/projpa2/h2/ch6EmployeeDB", "sa", "");
    private final String FIND_ALL = "select * from Employee";

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch6EmployeePU");
    EntityManager em = emf.createEntityManager();


    @Test
    public void persistNonTransactional() {

        Employee em = new Employee(2L, "Peter");

        // persist without transaction - added to persistence ctx, but not saved to db
        this.em.persist(em);
        System.out.println("\nin persistence context: " + this.em.contains(em));
        System.out.println("saved to db: " + jdbcUtil.query(FIND_ALL));

        // new transaction  - persistence ctx is synchronized, entity is saved to db
        this.em.getTransaction().begin();
        this.em.getTransaction().commit();
        System.out.println("\nin persistence context: " + this.em.contains(em));
        System.out.println("saved to db: " + jdbcUtil.query(FIND_ALL));
    }
}
