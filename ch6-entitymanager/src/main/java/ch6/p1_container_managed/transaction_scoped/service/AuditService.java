package ch6.p1_container_managed.transaction_scoped.service;

import ch6.p1_container_managed.transaction_scoped.entity.Employee;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Component
public class AuditService {

    @PersistenceContext
    private EntityManager em;

    // @Transactional // obsolete
    public void logPropagationRequired(final int id) {
        System.out.println("Propagation REQUIRED: " + em.find(Employee.class, id));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logPropagationNew(final int id) {
        System.out.println("Propagation REQUIRES_NEW: " + em.find(Employee.class, id));
    }
}
