package ch6.p3_entity_manager_operations.container_managed.transaction_scoped;

import ch6.entities.Department;
import ch6.entities.Employee;
import ch6.entities.cascade_demo.DepartmentCascadePersist;
import ch6.entities.cascade_demo.DepartmentNo;
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

@Component
public class CascadingDemo {
    public static final JdbcUtil jdbcUtil = new JdbcUtil("jdbc:h2:mem:testdb", "sa", "");
    private static final String DEPARTMENT_JOIN_EMPLOYEE = "select * from Department left join Employee";
    private static final String EMPLOYEE_JOIN_DEPARTMENT = "select * from Employee left join Department";
    private static final String ALL_EMPLOYEES = "select * from Employee";

    private CascadingDemo self;

    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    void initContext() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(P3TxScopedConfig.class);
        self = ctx.getBean(CascadingDemo.class);
    }

    @Test
    public void noCascadePersistDemo() {
        self.noCascadePersist();
        System.out.println(jdbcUtil.query(DEPARTMENT_JOIN_EMPLOYEE));
        System.out.println(jdbcUtil.query(EMPLOYEE_JOIN_DEPARTMENT));
    }

    @Transactional
    public void noCascadePersist() {
        Employee employee = new Employee(101L, "John Lennon");
        DepartmentNo department = new DepartmentNo(111L, Collections.singletonList(employee));
        em.persist(employee);
        em.persist(department);
    }

    @Test
    public void cascadePersistDemo() {
        self.cascadePersist();
        System.out.println(jdbcUtil.query(DEPARTMENT_JOIN_EMPLOYEE));
        System.out.println(jdbcUtil.query(EMPLOYEE_JOIN_DEPARTMENT));
    }

    @Transactional
    public void cascadePersist() {
        Employee employee = new Employee(101L, "John Lennon");
        DepartmentCascadePersist department = new DepartmentCascadePersist(111L, Collections.singletonList(employee));
        em.persist(department);
    }

    @Test
    public void cascadeRemoveDemo() {
        self.cascadePersist();
        System.out.println(jdbcUtil.query(ALL_EMPLOYEES));
        self.cascadeRemove();
        System.out.println(jdbcUtil.query(ALL_EMPLOYEES));
    }

    @Transactional
    public void cascadeRemove() {
        Department department = em.find(Department.class, 111L);
        em.remove(department);
    }
}
