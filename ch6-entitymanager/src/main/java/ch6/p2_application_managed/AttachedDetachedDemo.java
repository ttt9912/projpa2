package ch6.p2_application_managed;

import ch6.entities.Employee;
import ch6.util.jdbc.JdbcUtil;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AttachedDetachedDemo {
    private final JdbcUtil jdbcUtil = new JdbcUtil("jdbc:h2:~/dev/workspaces/projpa2/h2/ch6EmployeeDB", "sa", "");
    private final String FIND_ALL = "select * from EMPLOYEE";

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch6EmployeePU");
    EntityManager em = emf.createEntityManager();

    @Test
    public void demo() {
        Employee employee = new Employee(1L, "Paul");

        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit(); // saved to db

        employee.setName("George");

        System.out.println("db: " + jdbcUtil.query(FIND_ALL));

        em.getTransaction().begin();
        em.getTransaction().commit();


        System.out.println("db: " + jdbcUtil.query(FIND_ALL));


    }
}
