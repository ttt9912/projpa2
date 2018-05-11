package app;

import entity.Department;
import entity.Employee;
import entity.ParkingSpace;
import repository.DepartmentRepository;
import repository.EmployeeRepository;
import repository.ParkingSpaceRepository;

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
        ParkingSpaceRepository parkingSpaceRepository = new ParkingSpaceRepository(em);

        // create Employee
        em.getTransaction().begin();
        Employee employee = employeeRepository.createAndSave("John Doe");
        System.out.println("Created Employee: " + employee);
        em.getTransaction().commit();

        // create Department
        em.getTransaction().begin();
        Department department = departmentRepository.createAndSave("SWE");
        System.out.println("Created Department: " + department);
        em.getTransaction().commit();

        // create Parking space
        em.getTransaction().begin();
        ParkingSpace parkingSpace = parkingSpaceRepository.createAndSave("north");
        System.out.println("Created Parking Space: " + parkingSpace);
        em.getTransaction().commit();

        // join Employee with Department and Parkingspace
        em.getTransaction().begin();
        employee.setDepartment(department);
        department.getEmployees().add(employee);
        employee.setParkingSpace(parkingSpace);
        parkingSpace.setEmployee(employee);
        System.out.println("Updated Employee: " + employee);
        em.getTransaction().commit();

        // select All Employees
        List<Employee> employeeList = employeeRepository.findAll();
        System.out.println("Employees: " + employeeList);

        List<Department> departments = departmentRepository.findAll();
        System.out.println("Departments: " + departments);
        System.out.println(department.getEmployees());

        List<ParkingSpace> parkingSpaces = parkingSpaceRepository.findAll();
        System.out.println("Parking Spaces: " + parkingSpaces);
        System.out.println(parkingSpace.getEmployee());
    }
}
