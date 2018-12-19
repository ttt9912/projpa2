package ch6.p3_entity_manager_operations.container_managed.transaction_scoped;

import ch6.entities.Department;
import ch6.entities.Employee;
import ch6.entities.cascade_demo.DepartmentCascadePersist;
import ch6.entities.cascade_demo.DepartmentNo;
import ch6.entities.cascade_demo.EmployeeA;
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
        System.out.println(jdbcUtil.query("select * from EmployeeA join DepartmentNo"));
        System.out.println(jdbcUtil.query("select * from DepartmentNo join EmployeeA"));
    }

    @Transactional
    public void noCascadePersist() {
        EmployeeA employee = new EmployeeA(101L, "John Lennon");
        DepartmentNo department = new DepartmentNo(111L, Collections.singletonList(employee));
        em.persist(employee);
        em.persist(department);
    }

    @Test
    public void cascadePersistDemo() {
        self.cascadePersist();
        System.out.println(jdbcUtil.query("select * from EmployeeA join DepartmentCascadePersist"));
        System.out.println(jdbcUtil.query("select * from DepartmentCascadePersist join EmployeeA"));
    }

    @Transactional
    public void cascadePersist() {
        EmployeeA employee = new EmployeeA(101L, "John Lennon");
        DepartmentCascadePersist department = new DepartmentCascadePersist(111L, Collections.singletonList(employee));
        em.persist(department);
    }

    @Transactional
    public void cascadeAll() {
        Employee employee = new Employee(101L, "John Lennon");
        Department department = new Department(111L, Collections.singletonList(employee));
        em.persist(department);
    }

    @Test
    public void cascadeRemoveDemo() {
        self.cascadeAll();
        System.out.println(jdbcUtil.query("select * from Employee"));
        self.cascadeRemove();
        System.out.println(jdbcUtil.query("select * from Employee"));
    }

    @Transactional
    public void cascadeRemove() {
        Department department = em.find(Department.class, 111L);
        em.remove(department);
    }

    @Test
    public void noCascadeRemoveDemo() {
        self.cascadePersist();
        System.out.println(jdbcUtil.query("select * from EmployeeA"));
        self.noCascadeRemove();
        System.out.println(jdbcUtil.query("select * from EmployeeA"));
    }

    @Transactional
    public void noCascadeRemove() {
        EmployeeA employee = em.find(EmployeeA.class, 101L);
        DepartmentCascadePersist department = em.find(DepartmentCascadePersist.class, 111L);
        em.remove(department);
    }
}
