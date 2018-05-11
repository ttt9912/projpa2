package repository;

import entity.Department;
import entity.Employee;
import entity.ParkingSpace;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeRepository {
    protected EntityManager em;

    public EmployeeRepository(EntityManager em) {
        this.em = em;
    }

    public Employee createAndSave(String name){
        Employee employee = new Employee();
        employee.setName(name);
        em.persist(employee);
        return employee;
    }

    public Employee setDepartment(int employeeId, int departmentId){
        Employee employee = em.find(Employee.class, employeeId);
        Department department = em.find(Department.class, departmentId);
        employee.setDepartment(department);
        return employee;
    }

    public Employee setParkingSpace(int employeeId, int parkingSpaceId){
        Employee employee = em.find(Employee.class, employeeId);
        ParkingSpace parkingSpace = em.find(ParkingSpace.class, parkingSpaceId);
        employee.setParkingSpace(parkingSpace);
        return employee;
    }

    public List<Employee> findAll(){
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        return query.getResultList();
    }
}
