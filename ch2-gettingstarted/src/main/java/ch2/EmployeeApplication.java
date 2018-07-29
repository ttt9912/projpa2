package ch2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeeApplication {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
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

        // use setter
        em.getTransaction().begin();
        employee = employeeService.raiseSalary(159, 15000);
        em.getTransaction().commit();
        System.out.println("Updated: " + employee);

        // remove
        em.getTransaction().begin();
        employeeService.deleteEmployee(employee);
        em.getTransaction().commit();
        System.out.println("Deleted ch2.Employee: " + employee.getId());

        // find all
        List<Employee> allEmployees = employeeService.findAllEmployees();
        System.out.println("All Employees: " + allEmployees);

        em.close();
        emf.close();
    }
}
