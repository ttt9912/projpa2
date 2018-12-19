package ch6.p3_entity_manager_operations.application_managed;

import ch6.entities.Employee;
import ch6.util.jdbc.JdbcUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Random;

public class EntityManagerMethods_NonTransactionalDemo {
    private final JdbcUtil jdbcUtil = new JdbcUtil("jdbc:h2:~/dev/workspaces/projpa2/h2/ch6EmployeeDB", "sa", "");
    private final String FIND_ALL = "select * from EmployeeC";
    private final long dummyId = new Random().nextLong();

    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    public void init() {
        emf = Persistence.createEntityManagerFactory("ch6EmployeePU");
        em = emf.createEntityManager();
        insertDummyEmployee();
    }

    private void insertDummyEmployee() {
        em.getTransaction().begin();
        em.persist(new Employee(dummyId, "George"));
        em.getTransaction().commit();
    }


    @Test
    public void persistNonTransactional() {

        Employee employee = new Employee(2L, "Peter");

        // persist without transaction - added to persistence ctx, but not in db
        em.persist(employee);
        System.out.println("\nmanaged: " + em.contains(employee));
        System.out.println("in db: " + jdbcUtil.query(FIND_ALL));

        // new transaction  - persistence ctx is synchronized, entity is in db
        em.getTransaction().begin();
        em.getTransaction().commit();
        System.out.println("\nmanaged: " + em.contains(employee));
        System.out.println("in db: " + jdbcUtil.query(FIND_ALL));
    }

    @Test
    public void findNonTransactional() {
        Employee employee = em.find(Employee.class, dummyId);
        System.out.println("\nmanaged: " + em.contains(employee));
    }

    @Test
    public void removeNonTransactional() {
        Employee employee = em.find(Employee.class, dummyId);

        em.remove(employee); // removed persistence ctx, still in db
        System.out.println("\nmanaged: " + em.contains(employee));
        System.out.println("in db: " + jdbcUtil.query(FIND_ALL));

        em.getTransaction().begin(); // new transaction
        em.getTransaction().commit(); // removed from db
        System.out.println("\nmanaged: " + em.contains(employee));
        System.out.println("in db: " + jdbcUtil.query(FIND_ALL));
    }

    @Test
    public void flushNonTransactional() {
        Employee employee = new Employee(100L, "Paul");
        em.persist(employee);
        // em.flush(); // TransactionRequiredException
        System.out.println("in db: " + jdbcUtil.query(FIND_ALL));
    }
}
