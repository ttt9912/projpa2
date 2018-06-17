package p1_element_collections.repository;

import p1_element_collections.entity.Department;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

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

    public List<Department> findAll(){
        TypedQuery<Department> query = em.createQuery("SELECT d FROM Department d", Department.class);
        return query.getResultList();
    }
}
