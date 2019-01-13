package ch7.entity;

import common.repository.BaseRepository;

import javax.persistence.EntityManager;

public class DepartmentRepository extends BaseRepository<Department> {
    public DepartmentRepository(final EntityManager em) {
        super(em);
    }

    public Department findByName(final String name) {
        return em.createQuery("select d from Department d where d.name = ?1", Department.class)
                .setParameter(1, name)
                .getResultStream()
                .findFirst().orElse(null);
    }
}
