import model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmployeeApplication {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeService");
        EntityManager em = emf.createEntityManager();

        EmployeeService employeeService = new EmployeeService(em);

        // Create and persist an Entity
        em.getTransaction().begin();
        Employee employee = employeeService.createEmployee(159, "John Doe", 45000);
        em.getTransaction().commit();
        System.out.println("Persisted: " + employee);

        // find by id
        employee = employeeService.findEmployee(159);
        System.out.println("Found: " + employee);

        em.close();
        emf.close();
    }
}
