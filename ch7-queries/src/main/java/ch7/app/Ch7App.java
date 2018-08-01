package ch7.app;

import ch7.data.Employee;
import ch7.query.QueryServiceJPQL;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.stream.Collectors;

@SuppressWarnings("JpaQueryApiInspection")
class Ch7App {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
    private static final EntityManager em = emf.createEntityManager();
    private final QueryServiceJPQL queryService = new QueryServiceJPQL(em);

    @BeforeAll
    static void initData() {
        new DefaultDataCreator(em).insertDefaultData();
    }


    @Test
    void jpql_queries() {
        System.out.println("JPQL Query 1:\t" + queryService.findEmployeeNames());
        System.out.println("JPQL Query 2:\t" + queryService.findEmployeeNamesTypeSafe());
        System.out.println("JPQL Query 3:\t" + queryService.findFilteredEmployees());

        System.out.println("JPQL Query 4:\t" + queryService.findEmployeesInDepartment().stream()
                .map(Arrays::toString)
                .collect(Collectors.joining(", ")));

        System.out.println("JPQL Query 5:\t" + queryService.summarizeDepartment().stream()
                .map(Arrays::toString)
                .collect(Collectors.joining(", ")));
    }

    @Test
    void dynamic_queries() {
        System.out.println("Dynamic Query 1:\t" + queryService.findByDepartmentAndEmployee("Dept-1a", "John"));
        System.out.println("Dynamic Query 2:\t" + queryService.findByDepartmentAndEmployee2("Dept-1a", "John"));
    }

    @Test
    void named_queries() {
        /*
         * Named Queries are defined on entity level with @NamedQuery
         */
        Employee result7 = em.createNamedQuery("Employee.findByName", Employee.class)
                .setParameter("empName", "Paul")
                .getSingleResult();

        System.out.println("Named Query 1:\t" + result7);
    }

    @Test
    void dynamic_named_queries() {
        /*
         *  Dynamic Named Queries
         *  create a query string and then add it as named query in the
         *  Entity Manager Factory (only useful in specific cases i.e. when
         *  a query is not known until runtime but then executed repeadetly)
         */
        final String query =
                "SELECT e.salary " +
                        "FROM Employee e " +
                        "WHERE e.department.name = :deptName AND " +
                        "e.name = :empName";

        TypedQuery<Long> namedQuery = em.createQuery(query, Long.class);
        emf.addNamedQuery("findSalaryForNameAndDepartment", namedQuery);

        // execute
        Long res = em.createNamedQuery("findSalaryForNameAndDepartment", Long.class)
                .setParameter("deptName", "Dept-1a")
                .setParameter("empName", "Paul")
                .getSingleResult();

        System.out.println("Dynamic Named Query 1:\t" + res);
    }

}
