package repository;

import entity.Department;

import javax.persistence.EntityManager;

public class DepartmentRepository extends BaseRepository<Department> {
    public DepartmentRepository(final EntityManager em) {
        super(em);
    }
}
