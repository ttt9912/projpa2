package p2_relationships;

import javax.persistence.EntityManager;

public class EmployeeRepository {
    protected EntityManager em;

    public EmployeeRepository(EntityManager em) {
        this.em = em;
    }
}
