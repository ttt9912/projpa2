package ch6.p1_container_managed.transaction_scoped;

import ch6.p1_container_managed.transaction_scoped.config.JpaConfig;
import ch6.p1_container_managed.transaction_scoped.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class EntityManagerMethodsTransactionalDemo {

    @PersistenceContext
    private EntityManager em;


    @Test
    public void withTransaction() {
        EntityManagerMethodsTransactionalDemo self = initContext();
        self.emOperationsTransactional();
    }

    @Transactional
    public void emOperationsTransactional() {
        System.out.println(em.isJoinedToTransaction());

        // --- persist ---
        Employee employee = new Employee();
        employee.setName("Paul");
        em.persist(employee);

        // --- find ---
        Employee employee1 = em.find(Employee.class, 1);
        System.out.println(employee1);
        System.out.println("within persistence context: " + em.contains(employee1));
    }


    @Test
    public void withoutTransaction() {
        EntityManagerMethodsTransactionalDemo self = initContext();
        self.emOperationsNonTransactional();
    }

    public void emOperationsNonTransactional() {
        System.out.println(em.isJoinedToTransaction());

        // --- persist ---
        // Employee employee = new Employee();
        // employee.setName("Paul");
        // em.persist(employee); // throws TransactionRequiredException

        // --- find ---
        Employee employee1 = em.find(Employee.class, 1);
        System.out.println(employee1);
        System.out.println("within persistence context: " + em.contains(employee1));

    }

    // -----setup-------

    private EntityManagerMethodsTransactionalDemo initContext() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(JpaConfig.class);
        EntityManagerMethodsTransactionalDemo self = ctx.getBean(EntityManagerMethodsTransactionalDemo.class);
        self.insertDummyEmployee("John");
        return self;
    }

    @Transactional
    public void insertDummyEmployee(final String name) {
        Employee employee = new Employee();
        employee.setName(name);
        em.persist(employee);
    }
}
