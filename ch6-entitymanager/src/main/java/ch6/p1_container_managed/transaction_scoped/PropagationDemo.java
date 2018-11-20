package ch6.p1_container_managed.transaction_scoped;

import ch6.p1_container_managed.transaction_scoped.config.JpaConfig;
import ch6.p1_container_managed.transaction_scoped.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
 * Propagating transaction between different EntityManager instances
 */
public class PropagationDemo {

    @Test
    void contextLoad() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(JpaConfig.class);

        EmployeeService employeeService = ctx.getBean(EmployeeService.class);

        employeeService.createEmployee(1, "Paul", 30_000);

        ctx.close();
    }
}
