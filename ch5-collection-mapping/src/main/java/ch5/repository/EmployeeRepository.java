package ch5.repository;

import ch5.entity.Employee;
import common.repository.BaseRepository;

import javax.persistence.EntityManager;

public class EmployeeRepository extends BaseRepository<Employee> {
    public EmployeeRepository(final EntityManager em) {
        super(em);
    }
}
