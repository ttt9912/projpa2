package repository;

import entity.Department;

import javax.persistence.EntityManager;

public class DepartmentRepository {
    protected EntityManager em;

    public DepartmentRepository(EntityManager em) {
        this.em = em;
    }

    public Department createAndSave(String name){
        Department department = new Department();
        department.setName(name);
        em.persist(department);
        return department;
    }
}
