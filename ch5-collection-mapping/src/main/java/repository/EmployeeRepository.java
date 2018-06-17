package repository;

import entity.Employee;
import entity.VacationEntry;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;

public class EmployeeRepository {
    protected EntityManager em;

    public EmployeeRepository(EntityManager em) {
        this.em = em;
    }

    public Employee createAndSave(String name, Collection<VacationEntry> vacationBookings, Collection<String> nickNames) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setVacationBookings(vacationBookings);
        employee.setNickNames(nickNames);
        em.persist(employee);
        return employee;
    }

    public List<Employee> findAll() {
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        return query.getResultList();
    }
}
