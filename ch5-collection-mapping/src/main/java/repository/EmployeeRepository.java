package repository;

import entity.Employee;

import javax.persistence.EntityManager;

public class EmployeeRepository extends BaseRepository<Employee> {
    public EmployeeRepository(final EntityManager em) {
        super(em);
    }
}
