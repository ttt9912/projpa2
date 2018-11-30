package ch6.p1_container_managed.transaction_scoped;

import ch6.entities.Employee;
import ch6.p1_container_managed.transaction_scoped.config.JpaConfig;
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
public class EntityManagerMethods_TransactionalDemo {
    public static final JdbcUtil jdbcUtil = new JdbcUtil("jdbc:h2:mem:testdb", "sa", "");
    private static final String FIND_ALL = "select * from Employee";
    private final long dummyId = new Random().nextLong();

    private EntityManagerMethods_TransactionalDemo self;

    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    void initContext() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(JpaConfig.class);
        self = ctx.getBean(EntityManagerMethods_TransactionalDemo.class);
        self.insertDummyEmployee();
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
        Employee employee = em.find(Employee.class, dummyId);
        em.remove(employee);
        System.out.println("managed: " + em.contains(employee));
        System.out.println("in db: " + jdbcUtil.query(FIND_ALL)); // not removed from db yet
    }
}
