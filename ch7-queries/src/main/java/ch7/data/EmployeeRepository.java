package ch7.data;

import common.repository.BaseRepository;

import javax.persistence.EntityManager;

public class EmployeeRepository extends BaseRepository<Employee> {

    public EmployeeRepository(final EntityManager em) {
        super(em);
    }
}
