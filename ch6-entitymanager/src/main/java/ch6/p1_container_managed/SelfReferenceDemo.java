package ch6.p1_container_managed;

import ch6.p1_container_managed.transaction_scoped.config.JpaConfig;
import ch6.p1_container_managed.transaction_scoped.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/*
 * TODO
 */
@Component
public class SelfReferenceDemo {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void demo() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(JpaConfig.class);
        SelfReferenceDemo self = ctx.getBean(SelfReferenceDemo.class);
//        insertDummyEmployee("John");
        self.insert();

        ctx.close();
    }

    public void insert() {
        this.insertDummyEmployee("John");
    }

    @Transactional
    public void insertDummyEmployee(final String name) {
        Employee employee = new Employee();
        employee.setName(name);
        em.persist(employee);
    }
}
