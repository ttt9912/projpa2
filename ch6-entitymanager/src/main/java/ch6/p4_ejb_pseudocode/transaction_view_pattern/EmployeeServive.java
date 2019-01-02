package ch6.p4_ejb_pseudocode.transaction_view_pattern;

import ch6.entities.Employee;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

// with transaction scoped persistence context
@Stateless
public class EmployeeServive {
    @PersistenceContext(unitName = "EmployeePU")
    private EntityManager em;

    public void updateEmployee(Employee emp) {
        if (em.find(Employee.class, emp.getId()) == null) {
            throw new IllegalArgumentException();
        }
        em.merge(emp);
    }

    public List<Employee> findAll() {
        return Collections.emptyList();
    }
}
