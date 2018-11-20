package ch6.p1_container_managed.transaction_scoped.service;

import ch6.p1_container_managed.transaction_scoped.entity.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository

public class EmployeeService {

    @PersistenceContext
    private EntityManager em;


    @Transactional // required - provides Transaction for EntityManager
    public Employee createEmployee(int id, String name, long salary) {
        Employee employee = new Employee(id);
        employee.setName(name);
        employee.setSalary(salary);
        em.persist(employee);
        return employee;
    }
}
