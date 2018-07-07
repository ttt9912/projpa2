package ch5.repository;

import ch5.entity.Department;
import common.repository.BaseRepository;

import javax.persistence.EntityManager;

public class DepartmentRepository extends BaseRepository<Department> {
    public DepartmentRepository(final EntityManager em) {
        super(em);
    }
}
