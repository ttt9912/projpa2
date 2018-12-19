package ch6.p3_entity_manager_operations.container_managed.transaction_scoped;

import ch6.entities.Department;
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
import java.util.Collections;
import java.util.Random;

@Component
public class FlushDemo {
    public static final JdbcUtil jdbcUtil = new JdbcUtil("jdbc:h2:mem:testdb", "sa", "");
    private static final String FIND_ALL = "select * from EmployeeC";
    private final long dummyId = new Random().nextLong();

    private FlushDemo self;

    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    void initContext() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(P3TxScopedConfig.class);
        self = ctx.getBean(FlushDemo.class);
    }

    @Test
    public void flushTransactionalTest() {
        self.flushTransactional();
        System.out.println("in db: " + jdbcUtil.query(FIND_ALL));
    }

    @Transactional
    public void flushTransactional() {
        Employee employee = new Employee(100L, "Paul");
        em.persist(employee);
        em.clear(); // employee will not be saved

        Employee employee2 = new Employee(111L, "Peter");
        em.persist(employee2); // generate & execute sql
        em.flush(); // employee2 will be saved
        em.clear();
    }

    @Test
    public void flushWithIntegrityViolationsTest() {
        self.flushWithIntegrityViolations();
    }

    @Transactional
    public void flushWithIntegrityViolations() {
        Employee employee = new Employee(101L, "John Lennon");
        Department department = new Department(111L, Collections.singletonList(employee));
        em.persist(department);
        System.out.println("here");
        em.flush();
        System.out.println("there");
    }
}
