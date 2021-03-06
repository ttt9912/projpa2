package ch4.relationships.repository;

import ch4.relationships.entity.Address;
import ch4.relationships.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeRepository {
    protected EntityManager em;

    public EmployeeRepository(EntityManager em) {
        this.em = em;
    }

    public Employee createAndSave(String name, Address address) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setAddress(address);
        em.persist(employee);
        return employee;
    }

    public List<Employee> findAll(){
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        return query.getResultList();
    }
}
