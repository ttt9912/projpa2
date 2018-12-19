package ch6.p2_application_managed;

import ch6.entities.Employee;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceContextDemo {

    @Test
    void attachedDetached() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch6EmployeePU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Employee employee = new Employee(11L, "John");
        em.persist(employee);
        em.getTransaction().commit();

        System.out.println(em.contains(employee)); // true - employee is still managed

        em.close();
        emf.close();
    }
}
