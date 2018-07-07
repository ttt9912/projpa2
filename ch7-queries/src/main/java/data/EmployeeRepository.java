package data;

import repository.BaseRepository;

import javax.persistence.EntityManager;

public class EmployeeRepository extends BaseRepository<Employee> {

    public EmployeeRepository(final EntityManager em) {
        super(em);
    }
}
