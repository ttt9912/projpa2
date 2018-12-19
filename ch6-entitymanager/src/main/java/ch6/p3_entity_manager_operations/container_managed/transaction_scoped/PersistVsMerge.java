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
public class PersistVsMerge {
    public static final JdbcUtil jdbcUtil = new JdbcUtil("jdbc:h2:mem:testdb", "sa", "");
    private final long dummyId = new Random().nextLong();

    private PersistVsMerge self;

    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    void initContext() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(P3TxScopedConfig.class);
        self = ctx.getBean(PersistVsMerge.class);
    }

    @Test
    public void test() {
        self.persist();
        self.merge();
    }

    /*
     * - persist throws exception if other entity instance or db entry with same id already exists
     * - entity becomes managed
     */
    @Transactional
    public void persist() {
        System.out.println("\n --- persist ---");
        Employee employee = new Employee(1L, "Paul");
        em.persist(employee);
        System.out.println(em.contains(employee));

        Employee employee1 = new Employee(1L, "Paul");
        // em.persist(employee1); // EntityExistsException
        System.out.println(em.contains(employee1));
    }

    /*
     * - merge looks for other entity instance or db entry with same id
     *      - merges state if exists
     *      - creates new if not
     * - entity does not become managed
     */
    @Transactional
    public void merge() {
        System.out.println("\n --- merge ---");
        Employee employee2 = new Employee(2L, "John");
        System.out.println(em.contains(employee2)); // false
        em.merge(employee2); // looks for employee with same id
        System.out.println(em.contains(employee2)); // false
        em.flush();
        em.clear();

        Employee employee3 = new Employee(2L, "John");
        System.out.println(em.contains(employee3)); // false
        em.merge(employee3);
        System.out.println(em.contains(employee3)); // false
    }
}
