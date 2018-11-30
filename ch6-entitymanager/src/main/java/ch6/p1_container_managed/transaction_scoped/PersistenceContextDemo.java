package ch6.p1_container_managed.transaction_scoped;

import ch6.entities.Employee;
import ch6.p1_container_managed.transaction_scoped.config.P1TxScopedConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
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
        System.out.println("managed: " + self.isManaged(employee));
    }

    @Transactional
    public void save(Employee employee) {
        em.persist(employee);
        System.out.println("managed: " + isManaged(employee));

    }

    @Transactional
    public boolean isManaged(Employee employee) {
        return em.contains(employee); // false - new persistence context
    }

}
