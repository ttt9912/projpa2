package ch6.p4_ejb_pseudocode.transaction_view_pattern;

import ch6.entities.Employee;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

// with extended scoped persistence context
@Stateful
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class EmployeeQuery {
    @PersistenceContext(type = PersistenceContextType.EXTENDED, unitName = "DepartmentPU")
    EntityManager em;

    public List<Employee> findAll() {
        return em.createQuery("select e from Employee e").getResultList();
    }

    @Remove
    public void finished() {
    }
}
