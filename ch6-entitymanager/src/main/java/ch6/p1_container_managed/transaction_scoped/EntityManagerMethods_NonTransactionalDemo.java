package ch6.p1_container_managed.transaction_scoped;

import ch6.entities.Employee;
import ch6.p1_container_managed.transaction_scoped.config.JpaConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class EntityManagerMethods_NonTransactionalDemo {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void withoutTransaction() {
        EntityManagerMethods_NonTransactionalDemo self = initContext();
        self.persistNonTransactional();
        self.findNonTransactional();
    }

    public void persistNonTransactional() {
        System.out.println(em.isJoinedToTransaction());

        // Employee employee = new Employee(200L, "Paul");
        // em.persist(employee); // throws TransactionRequiredException
    }

    public void findNonTransactional() {
        Employee employee1 = em.find(Employee.class, 1L);
        System.out.println(employee1);
        System.out.println("within persistence context: " + em.contains(employee1));
    }


    // -----setup-------
    private EntityManagerMethods_NonTransactionalDemo initContext() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(JpaConfig.class);
        EntityManagerMethods_NonTransactionalDemo self = ctx.getBean(EntityManagerMethods_NonTransactionalDemo.class);
        self.insertDummyEmployee();
        return self;
    }

    @Transactional
    public void insertDummyEmployee() {
        Employee employee = new Employee(101L, "John");
        em.persist(employee);
    }
}
