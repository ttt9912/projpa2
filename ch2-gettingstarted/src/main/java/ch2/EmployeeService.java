package ch2;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeService {
    protected EntityManager em;

    public EmployeeService(EntityManager em) {
        this.em = em;
    }

    public Employee createEmployee(int id, String name, long salary) {
        Employee employee = new Employee(id);
        employee.setName(name);
        employee.setSalary(salary);

        em.persist(employee);
        return employee;
    }

    public Employee findEmployee(int id) {
        return em.find(Employee.class, id);
    }

    public List<Employee> findAllEmployees() {
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        return query.getResultList();
    }

    public void deleteEmployee(Employee employee) {
        em.remove(employee);
    }

    public void deleteEmployee(int id) {
        Employee employee = findEmployee(id);
        if (employee != null) {
            em.remove(employee);
        }
    }

    public Employee raiseSalary(int id, long amount) {
        Employee employee = em.find(Employee.class, id);
        if (employee != null) {
            employee.setSalary(employee.getSalary() + amount);
        }
        return employee;
    }
}
