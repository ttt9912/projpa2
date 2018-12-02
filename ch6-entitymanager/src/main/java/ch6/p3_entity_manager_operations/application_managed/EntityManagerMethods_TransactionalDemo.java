package ch6.p3_entity_manager_operations.application_managed;

import ch6.entities.Employee;
import ch6.util.jdbc.JdbcUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Random;

public class EntityManagerMethods_TransactionalDemo {
    private final JdbcUtil jdbcUtil = new JdbcUtil("jdbc:h2:~/dev/workspaces/projpa2/h2/ch6EmployeeDB", "sa", "");
    private final String FIND_ALL = "SELECT * FROM EMPLOYEE";
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
    public void persistTransactional() {
        Employee employee = new Employee(1L, "Paul");

        em.getTransaction().begin();

        em.persist(employee); // put to persistence ctx, not yet in db
        System.out.println("\nmanaged: " + em.contains(employee));
        System.out.println("in db: " + jdbcUtil.query(FIND_ALL));

        em.getTransaction().commit(); // in db
        System.out.println("\nmanaged: " + em.contains(employee));
        System.out.println("in db: " + jdbcUtil.query(FIND_ALL));
    }

    @Test
    public void findTransactional() {
        em.getTransaction().begin();
        Employee employee = em.find(Employee.class, dummyId);
        em.getTransaction().commit();
        System.out.println("\nmanaged: " + em.contains(employee));
    }

    @Test
    public void removeTransactional() {
        Employee employee = em.find(Employee.class, dummyId);

        em.getTransaction().begin();

        em.remove(employee); // removed persistence ctx, still in db
        System.out.println("\nmanaged: " + em.contains(employee));
        System.out.println("in db: " + jdbcUtil.query(FIND_ALL));

        em.getTransaction().commit(); // removed from db
        System.out.println("\nmanaged: " + em.contains(employee));
        System.out.println("in db: " + jdbcUtil.query(FIND_ALL));
    }

    @Test
    public void flushTransactional() {
        em.getTransaction().begin();
        Employee employee = new Employee(100L, "Paul");
        em.persist(employee);
        em.flush();
        System.out.println("in db: " + jdbcUtil.query(FIND_ALL));
        em.getTransaction().commit();

        System.out.println("in db: " + jdbcUtil.query(FIND_ALL));
    }
}
