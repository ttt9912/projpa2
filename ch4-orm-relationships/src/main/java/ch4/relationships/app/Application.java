package ch4.relationships.app;

import ch4.relationships.entity.*;
import ch4.relationships.repository.*;

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
        ProjectRepository projectRepository = new ProjectRepository(em);
        PhoneRepository phoneRepository = new PhoneRepository(em);
        CompanyRepository companyRepository = new CompanyRepository(em);
        BadgeRepository badgeRepository = new BadgeRepository(em);

        // create Employee
        em.getTransaction().begin();
        Address address1 = new Address("1st Street", "Sydney", "35000");
        Employee employee = employeeRepository.createAndSave("John Doe", address1);
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

        // create Project
        em.getTransaction().begin();
        Project project = projectRepository.createAndSave("King of Tokyo");
        System.out.println("Created Project: " + project);
        em.getTransaction().commit();

        // create Phone
        em.getTransaction().begin();
        Phone phone = phoneRepository.createAndSave("private", "0770010302");
        System.out.println("Created Phone: " + phone);
        em.getTransaction().commit();

        // create Company
        em.getTransaction().begin();
        Address address2 = new Address("Main Street", "Melbourne", "50000");
        Company company = companyRepository.createAndSave("UBS", address2);
        System.out.println("Created Company: " + company);
        em.getTransaction().commit();

        // create Badge (unidirectional OneToMany without jointable)
        em.getTransaction().begin();
        Badge badge = badgeRepository.createAndSave("HQ");
        System.out.println("Created Badge: " + badge);
        em.getTransaction().commit();

        // join Employee with Department, Parkingspace, Project
        em.getTransaction().begin();
        employee.setDepartment(department);
        department.getEmployees().add(employee);
        employee.setParkingSpace(parkingSpace);
        parkingSpace.setEmployee(employee);
        employee.getProjects().add(project);
        project.getEmployees().add(employee);
        employee.getPhones().add(phone);
        employee.getBadges().add(badge);
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

        List<Project> projects = projectRepository.findAll();
        System.out.println("Projects: " + projects);
        System.out.println(project.getEmployees());
    }
}
