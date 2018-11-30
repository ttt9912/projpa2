package ch6.p2_application_managed;

import ch6.entities.Employee;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/*
 * - Application Managed Entity Manager
 * - Application Managed Persistence Context
 * - Resource Local Transaction
 */
public class TransactionManagementDemo {

    @Test
    void demo() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch6EmployeePU");
        EntityManager em = emf.createEntityManager();

        // Create and persist an Entity
        em.getTransaction().begin();
        Employee employee = new Employee(11L, "John");
        em.persist(employee);
        em.getTransaction().commit();

        System.out.println("managed: " + em.contains(employee));

        // find all
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        System.out.println("All Employees: " + query.getResultList());

        em.close();
        emf.close();
    }
}
