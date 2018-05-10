package repository;

import entity.Department;
import entity.Employee;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeRepository {
    protected EntityManager em;

    public EmployeeRepository(EntityManager em) {
        this.em = em;
    }

    public Employee createAndSave(String name, Department department){
        Employee employee = new Employee();
        employee.setName(name);
        employee.setDepartment(department);
        em.persist(employee);
        return employee;
    }

    public List<Employee> findAll(){
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        return query.getResultList();
    }
}
