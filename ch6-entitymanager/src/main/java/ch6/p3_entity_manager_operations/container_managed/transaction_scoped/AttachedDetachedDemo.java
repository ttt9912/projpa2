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
public class AttachedDetachedDemo {
    public static final JdbcUtil jdbcUtil = new JdbcUtil("jdbc:h2:mem:testdb", "sa", "");
    private static final String FIND_ALL = "select * from Employee";
    private final long dummyId = new Random().nextLong();

    private AttachedDetachedDemo self;

    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    void initContext() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(P3TxScopedConfig.class);
        self = ctx.getBean(AttachedDetachedDemo.class);
        self.insertDummyEmployee();
    }

    @Transactional
    public void insertDummyEmployee() {
        Employee employee = new Employee(dummyId, "John");
        em.persist(employee);
    }

    @Test
    public void attached() {
        self.attachedTransactional();
        System.out.println("in db: " + jdbcUtil.query(FIND_ALL));
    }

    @Transactional
    public void attachedTransactional() {
        Employee employee = new Employee(111L, "Peter");
        em.persist(employee); // put into persitence context
        employee.setName("Griffin"); // tracked
    }

}
