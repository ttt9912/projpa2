import javax.persistence.EntityManager;

public class DepartmentRepository {
    protected EntityManager em;

    public DepartmentRepository(EntityManager em) {
        this.em = em;
    }
}
