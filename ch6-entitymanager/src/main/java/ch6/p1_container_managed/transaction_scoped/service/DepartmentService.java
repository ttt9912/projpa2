package ch6.p1_container_managed.transaction_scoped.service;

import ch6.entities.Department;
import ch6.entities.Employee;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;

@Component
public class DepartmentService {

    @PersistenceContext
    private EntityManager em;

    public Employee createEmployee(String name, long salary) {
        Employee employee = new Employee();
        employee.setName(name);

        em.persist(employee);
        return employee;
    }

    @Transactional
    public Department createDepartment(String name) {
        Department department = new Department();
        department.setName(name);
        department.setEmployees(Collections.emptyList());
        em.persist(department);
        return em.find(Department.class, department.getId());
    }

    @Transactional // creates transaction
    public void assignEmployee(long emplId, int projId) {
        Employee employee = em.find(Employee.class, emplId);
        Department department = em.find(Department.class, projId);
        department.getEmployees().add(employee);
    }
}
