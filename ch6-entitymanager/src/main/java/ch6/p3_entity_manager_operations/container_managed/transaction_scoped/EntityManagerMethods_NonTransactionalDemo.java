package ch6.p3_entity_manager_operations.container_managed.transaction_scoped;

import ch6.entities.Employee;
import ch6.p3_entity_manager_operations.container_managed.transaction_scoped.config.P3TxScopedConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Random;

@Component
public class EntityManagerMethods_NonTransactionalDemo {
    private final long dummyId = new Random().nextLong();

    private EntityManagerMethods_NonTransactionalDemo self;

    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    void initContext() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(P3TxScopedConfig.class);
        self = ctx.getBean(EntityManagerMethods_NonTransactionalDemo.class);
        self.insertDummyEmployee();
    }

    @Transactional
    public void insertDummyEmployee() {
        Employee employee = new Employee(dummyId, "John");
        em.persist(employee);
    }


    @Test
    public void persistNonTransactionalTest() {
        self.persistNonTransactional();
    }

    public void persistNonTransactional() {
        System.out.println(em.isJoinedToTransaction());
        // Employee employee = new Employee(200L, "Paul");
        // em.persist(employee); // TransactionRequiredException
    }

    @Test
    public void findNonTransactionalTest() {
        self.findNonTransactional();
    }

    public void findNonTransactional() {
        Employee employee1 = em.find(Employee.class, dummyId);
        System.out.println(employee1);
        System.out.println("managed: " + em.contains(employee1));
    }

    @Test
    public void removeTransactionalTest() {
        self.removeTransactional();
    }

    public void removeTransactional() {
        // Employee employee = em.find(Employee.class, dummyId);
        // em.remove(employee); // TransactionRequiredException
    }
}
