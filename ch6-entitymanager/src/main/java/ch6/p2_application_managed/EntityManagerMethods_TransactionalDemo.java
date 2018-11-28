package ch6.p2_application_managed;

import ch6.entities.Employee;
import ch6.util.jdbc.JdbcUtil;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerMethods_TransactionalDemo {
    private final JdbcUtil jdbcUtil = new JdbcUtil("jdbc:h2:~/dev/workspaces/projpa2/h2/ch6EmployeeDB", "sa", "");
    private final String FIND_ALL = "SELECT * FROM EMPLOYEE";

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch6EmployeePU");
    EntityManager em = emf.createEntityManager();

    @Test
    public void persistTransactional() {
        Employee employee = new Employee(1L, "Paul");

        em.getTransaction().begin();

        em.persist(employee); // put to persistence ctx
        System.out.println("\nin persistence context: " + em.contains(employee));
        System.out.println("saved to db: " + jdbcUtil.query(FIND_ALL));

        em.getTransaction().commit(); // saved to db

        System.out.println("\nin persistence context: " + em.contains(employee));
        System.out.println("saved to db: " + jdbcUtil.query(FIND_ALL));
    }
}
