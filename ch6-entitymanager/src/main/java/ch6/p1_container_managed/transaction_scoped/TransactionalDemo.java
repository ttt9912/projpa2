package ch6.p1_container_managed.transaction_scoped;

import ch6.p1_container_managed.transaction_scoped.config.JpaConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

/*
 * EntityManager -> transaction
 */
@Component
public class TransactionalDemo {

    @Test
    void transactional() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(JpaConfig.class);

        // PersistenceContext only works on a bean within context
        TransactionalDemo self = ctx.getBean(TransactionalDemo.class);
        self.a(); // no transaction
        self.b(); // part of a transaction
        self.c(); // no transaction

        ctx.close();
    }

    @PersistenceContext
    private EntityManager em;

    public void a() {
        assertNotNull(em);
        assertFalse(em.isJoinedToTransaction());
    }

    @Transactional
    public void b() {
        assertNotNull(em);
        assertTrue(em.isJoinedToTransaction());
    }

    @Transactional(propagation = Propagation.NEVER)
    public void c() {
        assertNotNull(em);
        assertTrue(em.isJoinedToTransaction());
    }

}
