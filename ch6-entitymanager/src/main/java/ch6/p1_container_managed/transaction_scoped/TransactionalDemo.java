package ch6.p1_container_managed.transaction_scoped;

import ch6.p1_container_managed.transaction_scoped.config.P1TxScopedConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/*
 * EntityManager -> transaction
 */
@Component
public class TransactionalDemo {
    @PersistenceContext
    private EntityManager em;

    @Test
    void transactional() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(P1TxScopedConfig.class);

        // PersistenceContext only works on a bean within context
        TransactionalDemo self = ctx.getBean(TransactionalDemo.class);
        self.a(); // no transaction
        self.b(); // part of a transaction
        self.c(); // no transaction

        self.callTransactional();

        ctx.close();
    }

    public boolean a() {
        System.out.println("a: " + em.isJoinedToTransaction());
        return em.isJoinedToTransaction();
    }

    @Transactional
    public boolean b() {
        System.out.println("b: " + em.isJoinedToTransaction());
        return em.isJoinedToTransaction();

    }

    @Transactional(propagation = Propagation.NEVER)
    public boolean c() {
        System.out.println("c: " + em.isJoinedToTransaction());
        return em.isJoinedToTransaction();

    }

    @Transactional
    public void callTransactional() {
        a();
        b();
        c();
    }
}
