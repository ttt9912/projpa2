package ch7.entity;

import common.repository.BaseRepository;

import javax.persistence.EntityManager;

public class DepartmentRepository extends BaseRepository<Department> {
    public DepartmentRepository(final EntityManager em) {
        super(em);
    }

    public Department findByName(String name) {
        final String qlString = "SELECT d FROM Department d WHERE d.name = :name";

        return em.createQuery(qlString, Department.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
