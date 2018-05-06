import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeService");
        EntityManager em = emf.createEntityManager();

        EmployeeService employeeService = new EmployeeService(em);

        // Create and persist an Entity
        em.getTransaction().begin();
        Employee employee = employeeService.createEmployee(159, "John Doe", 45000, "456789");
        em.getTransaction().commit();
        System.out.println("Persisted: " + employee);

        // find all
        List<Employee> allEmployees = employeeService.findAllEmployees();
        System.out.println("All Employees: " + allEmployees);

        em.close();
        emf.close();
    }
}
