package ch3.j2ee.ejb;

import ch3.j2ee.entity.Employee;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class EmployeeEJB {

    @PersistenceContext(unitName = "defaultPersistenceUnit")
    private EntityManager em;

    public Employee save(Employee employee) {
        em.persist(employee);
        return employee;
    }

    public List<Employee> findAll() {
        final Query query = em.createQuery("SELECT e FROM Employee e ORDER BY e.created DESC");
        List<Employee> entries = query.getResultList();
        if (entries == null) {
            entries = new ArrayList<Employee>();
        }
        return entries;
    }
}
