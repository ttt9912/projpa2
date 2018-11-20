package ch6.p1_container_managed.transaction_scoped.service;

import ch6.p1_container_managed.transaction_scoped.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class EmployeeService {

    @Autowired
    private AuditService auditService;

    @PersistenceContext
    private EntityManager em;


    @Transactional // provides Transaction for EntityManager
    public Employee createEmployee(int id, String name, long salary) {
        Employee employee = new Employee(id);
        employee.setName(name);
        employee.setSalary(salary);
        em.persist(employee);

        // propagate transaction
        logEmployee(id); // same em
        auditService.logPropagationRequired(id); // propagation required
        auditService.logPropagationNew(id); // propagation new

        return employee;
    }

    // transaction is active, uses this em with active persistence context
    private void logEmployee(final int id) {
        System.out.println("Same EntityManager: " + em.find(Employee.class, id));
    }
}
