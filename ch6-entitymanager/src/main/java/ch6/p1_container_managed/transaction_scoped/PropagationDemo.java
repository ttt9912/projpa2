package ch6.p1_container_managed.transaction_scoped;

import ch6.entities.Employee;
import ch6.p1_container_managed.transaction_scoped.config.P1TxScopedConfig;
import ch6.p1_container_managed.transaction_scoped.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/*
 * Propagating transaction between different EntityManager instances
 */
@Component
public class PropagationDemo {

    @Test
    void demo() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(P1TxScopedConfig.class);

        EmployeeService employeeService = ctx.getBean(EmployeeService.class);
        Employee paul = employeeService.createAndLog("Paul", 30_000);
        ctx.close();
    }
}
