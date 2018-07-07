package ch7.data;

import common.repository.BaseRepository;

import javax.persistence.EntityManager;

public class DepartmentRepository extends BaseRepository<Department> {
    public DepartmentRepository(final EntityManager em) {
        super(em);
    }
}
