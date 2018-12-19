package ch6.p3_entity_manager_operations.container_managed.transaction_scoped;

import ch6.entities.Department;
import ch6.entities.Employee;
import ch6.p3_entity_manager_operations.container_managed.transaction_scoped.config.P3TxScopedConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Component
public class HandleDetachmentDemo {
    private HandleDetachmentDemo self;

    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    void initContext() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(P3TxScopedConfig.class);
        self = ctx.getBean(HandleDetachmentDemo.class);
    }

    @Transactional
    public void insertDummy() {
        Employee employee = new Employee(1L, "John");
        Department department = new Department(101L, Collections.singletonList(employee));
        em.persist(department);
    }


    @Test
    public void triggeringLazyLoadingDemo() {
        self.insertDummy();
        self.triggeringLazyLoading();
    }

    @Transactional
    public void triggeringLazyLoading() {
        Department department = em.find(Department.class, 101L);
        List<Employee> employees = department.getEmployees();
    }

    public void triggeringLazyLoadingNonTransactional() {
        Department department = em.find(Department.class, 101L);
        List<Employee> employees = department.getEmployees();
    }
}
