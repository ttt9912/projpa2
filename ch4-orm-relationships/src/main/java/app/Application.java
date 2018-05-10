package app;

import entity.Department;
import entity.Employee;
import repository.DepartmentRepository;
import repository.EmployeeRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
        EntityManager em = emf.createEntityManager();

        EmployeeRepository employeeRepository = new EmployeeRepository(em);
        DepartmentRepository departmentRepository = new DepartmentRepository(em);

        // create Department
        em.getTransaction().begin();
        Department department = departmentRepository.createAndSave("SWE");
        System.out.println("Created Department: " + department);
        em.getTransaction().commit();

        // create Employee
        em.getTransaction().begin();
        Employee employee = employeeRepository.createAndSave("John Doe", department);
        System.out.println("Created Employee: " + employee);
        em.getTransaction().commit();

        // select All Employees
        List<Employee> employeeList = employeeRepository.findAll();
        System.out.println("Employees: " + employeeList);
    }
}
