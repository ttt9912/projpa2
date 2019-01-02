package ch6.p4_ejb_pseudocode.transaction_view_pattern;

import ch6.entities.Employee;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

// with extended scoped persistence context
@Stateful
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class EmployeeEdit {
    @PersistenceContext(type = PersistenceContextType.EXTENDED, unitName = "EmployeePU")
    EntityManager em;
    Employee empl; // managed employee

    public void begin(long id) {
        this.empl = em.find(Employee.class, id);
    }

    @Remove
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void save() {
    }

    @Remove
    public void cancel() {
    }
}
