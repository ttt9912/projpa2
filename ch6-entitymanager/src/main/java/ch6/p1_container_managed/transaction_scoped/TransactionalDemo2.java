package ch6.p1_container_managed.transaction_scoped;

import ch6.p1_container_managed.transaction_scoped.config.JpaConfig;
import ch6.p1_container_managed.transaction_scoped.entity.Department;
import ch6.p1_container_managed.transaction_scoped.entity.Employee;
import ch6.p1_container_managed.transaction_scoped.service.DepartmentService;
import ch6.p1_container_managed.transaction_scoped.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TransactionalDemo2 {


    @Test
    void transactional() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(JpaConfig.class);

        EmployeeService employeeService = ctx.getBean(EmployeeService.class);
        DepartmentService departmentService = ctx.getBean(DepartmentService.class);

        Employee employee = employeeService.createEmployee("Paul McCartney", 30_000);
        Department department = departmentService.createDepartment("Cavern");

        departmentService.assignEmployee(employee.getId(), department.getId());

        ctx.close();
    }

}
