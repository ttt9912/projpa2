package ch6.p1_container_managed.transaction_scoped;

import ch6.p1_container_managed.transaction_scoped.config.JpaConfig;
import ch6.p1_container_managed.transaction_scoped.entity.Employee;
import ch6.util.jdbc.JdbcUtil;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class EntityManagerMethods_TransactionalDemo {
    public static final JdbcUtil jdbcUtil = new JdbcUtil("jdbc:h2:mem:testdb", "sa", "");
    private static final String FIND_ALL = "select * from Employee";

    @PersistenceContext
    private EntityManager em;

    @Test
    public void withTransaction() {
        EntityManagerMethods_TransactionalDemo self = initContext();
        self.persistTransactional();
        self.findTransactional();
    }

    @Transactional
    public void persistTransactional() {
        System.out.println("\n" + em.isJoinedToTransaction());
        Employee employee = new Employee(100L, "Paul");
        em.persist(employee);
        System.out.println("in db: " + jdbcUtil.query(FIND_ALL));
    }

    @Transactional
    public void findTransactional() {
        Employee employee1 = em.find(Employee.class, 101L);
        System.out.println(employee1);
        System.out.println("within persistence context: " + em.contains(employee1));
    }


    // -----setup-------
    private EntityManagerMethods_TransactionalDemo initContext() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(JpaConfig.class);
        EntityManagerMethods_TransactionalDemo self = ctx.getBean(EntityManagerMethods_TransactionalDemo.class);
        self.insertDummyEmployee();
        return self;
    }

    @Transactional
    public void insertDummyEmployee() {
        Employee employee = new Employee(101L, "John");
        em.persist(employee);
    }
}
