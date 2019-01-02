package ch6.p4_ejb_pseudocode.transaction_view_pattern;

import ch6.entities.Employee;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.SynchronizationType;

/*
 * with unsynchronized persistence context
 * @SessionScoped: specifies CDI beans maintained within a HTTP session
 */
@Stateful
@SessionScoped
public class EmployeeEdit2 {
    @PersistenceContext(type = PersistenceContextType.EXTENDED,
            synchronization = SynchronizationType.UNSYNCHRONIZED,
            unitName = "EmployeePU")
    EntityManager em;
    Employee currentEmpl;

    public Employee loadEmployee(long id) {
        Employee employee = em.find(Employee.class, id);
        this.currentEmpl = employee;
        return employee;
    }

    public void saveChanges() {
    }

    public void cancel() {
        em.detach(currentEmpl);
    }

    public void processAllChanges() {
        em.joinTransaction();
    }

    public void discardAllChanges() {
        em.clear();
    }
}
