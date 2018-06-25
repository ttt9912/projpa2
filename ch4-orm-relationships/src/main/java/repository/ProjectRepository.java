package repository;

import entity.Project;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProjectRepository {
    protected EntityManager em;

    public ProjectRepository(EntityManager em) {
        this.em = em;
    }

    public Project createAndSave(String name) {
        Project project = new Project();
        project.setName(name);
        em.persist(project);
        return project;
    }

    public List<Project> findAll() {
        TypedQuery<Project> query = em.createQuery("SELECT p FROM Project p", Project.class);
        return query.getResultList();
    }
}
