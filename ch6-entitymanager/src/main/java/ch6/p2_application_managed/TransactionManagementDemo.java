package ch6.p2_application_managed;

import ch6.p2_application_managed.entity.Person;
import ch6.p2_application_managed.service.EmployeeService;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

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

        EmployeeService employeeService = new EmployeeService(em);

        // Create and persist an Entity
        em.getTransaction().begin();
        Person person = employeeService.createEmployee("John Doe");
        em.getTransaction().commit();

        // find all
        List<Person> allPeople = employeeService.findAllEmployees();
        System.out.println("All Persons: " + allPeople);

        em.close();
        emf.close();
    }
}
