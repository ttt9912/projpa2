package ch6.p1_container_managed.transaction_scoped;

import ch6.entities.Employee;
import ch6.p1_container_managed.transaction_scoped.config.P1TxScopedConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/*
 * Persistence context is created and closed for each transaction
 */
@Component
public class PersistenceContextDemo {

    @PersistenceContext
    private EntityManager em;

    @Test
    void transactional() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(P1TxScopedConfig.class);
        PersistenceContextDemo self = ctx.getBean(PersistenceContextDemo.class);

        Employee employee = new Employee();

        self.save(employee);

        System.out.println("\n--- from outside ---");
        System.out.println("managed: " + self.isManaged(employee)); // false - new persistence context
        System.out.println("managed: " + self.isManagedRequiresNew(employee)); // false - new persistence context
        System.out.println("managed: " + self.isManagedNever(employee)); // false - new persistence context
    }

    @Transactional
    public void save(Employee employee) {
        em.persist(employee);

        System.out.println("\n--- within transaction ---");
        System.out.println("managed: " + isManaged(employee)); // true - same persistence context
        System.out.println("managed: " + isManagedRequiresNew(employee)); // true(!)
        System.out.println("managed: " + isManagedNever(employee)); // true(!)
    }

    public boolean isManaged(Employee employee) {
        System.out.println("joined: " + em.isJoinedToTransaction());
        return em.contains(employee);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean isManagedRequiresNew(Employee employee) {
        System.out.println("joined: " + em.isJoinedToTransaction());
        return em.contains(employee);
    }

    @Transactional(propagation = Propagation.NEVER)
    public boolean isManagedNever(Employee employee) {
        System.out.println("joined: " + em.isJoinedToTransaction());
        return em.contains(employee);
    }
}
