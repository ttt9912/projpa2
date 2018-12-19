package ch6.p3_entity_manager_operations.container_managed.transaction_scoped;

import ch6.entities.Employee;
import ch6.p3_entity_manager_operations.container_managed.transaction_scoped.config.P3TxScopedConfig;
import ch6.util.jdbc.JdbcUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Random;

@Component
public class TransactionalDemo {
    public static final JdbcUtil jdbcUtil = new JdbcUtil("jdbc:h2:mem:testdb", "sa", "");
    private static final String FIND_ALL = "select * from EmployeeC";
    private final long dummyId = new Random().nextLong();

    private TransactionalDemo self;

    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    void initContext() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(P3TxScopedConfig.class);
        self = ctx.getBean(TransactionalDemo.class);
    }

    @Transactional
    public void insertDummyEmployee() {
        Employee employee = new Employee(dummyId, "John");
        em.persist(employee);
    }

    @Test
    public void persistTransactionalTest() {
        self.persistTransactional();
        System.out.println("in db: " + jdbcUtil.query(FIND_ALL));  // now saved to db
    }

    @Transactional
    public void persistTransactional() {
        System.out.println("\n transaction: " + em.isJoinedToTransaction());
        Employee employee = new Employee(100L, "Paul");
        em.persist(employee);
        System.out.println("in db: " + jdbcUtil.query(FIND_ALL));  // not saved to db yet
    }

    @Test
    public void findTransactionalTest() {
        self.findTransactional();
    }

    @Transactional
    public void findTransactional() {
        insertDummyEmployee();

        Employee employee1 = em.find(Employee.class, dummyId);
        System.out.println(employee1);
        System.out.println("managed: " + em.contains(employee1));
    }

    @Test
    public void removeTransactionalTest() {
        self.removeTransactional();
        System.out.println("in db: " + jdbcUtil.query(FIND_ALL)); // now removed
    }

    @Transactional
    public void removeTransactional() {
        insertDummyEmployee();

        Employee employee = em.find(Employee.class, dummyId);
        em.remove(employee);
        System.out.println("managed: " + em.contains(employee));
        System.out.println("in db: " + jdbcUtil.query(FIND_ALL)); // not removed from db yet
    }



}
