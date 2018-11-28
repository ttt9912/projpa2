package ch6.p1_container_managed.transaction_scoped.service;

import ch6.entities.Employee;
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
    public Employee createEmployee(String name, long salary) {
        Employee employee = new Employee();
        employee.setName(name);
        em.persist(employee);
        return employee;
    }

    @Transactional // provides Transaction for EntityManager
    public Employee createAndLog(String name, long salary) {
        Employee employee = new Employee();
        employee.setName(name);
        em.persist(employee);

        // propagate transaction
        logEmployee(employee.getId()); // same em
        auditService.logPropagationRequired(employee.getId()); // propagation required
        auditService.logPropagationNew(employee.getId()); // propagation new

        return employee;
    }

    // transaction is active, uses this em with active persistence context
    private void logEmployee(final long id) {
        System.out.println("Same EntityManager: " + em.find(Employee.class, id));
    }
}
