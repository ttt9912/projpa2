package ch7.app;

import ch7.data.Employee;
import ch7.query.QueryServiceJPQL;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Ch7App {

    public static void main(String[] arg) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
        EntityManager em = emf.createEntityManager();
        new BulkRunner(em).insertDefaultData();


        // JPQL Queries
        QueryServiceJPQL jpql = new QueryServiceJPQL(em);
        System.out.println("Query 1:\t" + jpql.findEmployeeNames());
        System.out.println("Query 2:\t" + jpql.findEmployeeNamesTypeSafe());
        System.out.println("Query 3:\t" + jpql.findFilteredEmployees());
        System.out.println("Query 4:\t" + jpql.findEmployeesInDepartment().stream()
                .map(Arrays::toString)
                .collect(Collectors.joining(", ")));
        System.out.println("Query 5:\t" + jpql.summarizeDepartment().stream()
                .map(Arrays::toString)
                .collect(Collectors.joining(", ")));

        // Dynamic query
        System.out.println("Query 6.1:\t" + jpql.findByDepartmentAndEmployee("Dept-1a", "John"));
        System.out.println("Query 6.2:\t" + jpql.findByDepartmentAndEmployee2("Dept-1a", "John"));

        // Named Queries
        Employee result7 = em.createNamedQuery("Employee.findByName", Employee.class)
                .setParameter("empName", "Paul")
                .getSingleResult();
        System.out.println("Query 7:\t" + result7);

        /*
         *  Dynamic named Queries
         *  create a query string and then add it as named query in the
         *  Entity Manager Factory (only useful in specific cases i.e. when
         *  a query is not known until runtime but then executed repeadetly)
         */
        final String query = "SELECT e.salary FROM Employee e WHERE e.name = :empName";
        TypedQuery<Long> namedQuery = em.createQuery(query, Long.class);
        emf.addNamedQuery("findSalaryForEmplName", namedQuery);

        // execute
        Long res = em.createNamedQuery("findSalaryForEmplName", Long.class)
                .setParameter("empName", "Paul")
                .getSingleResult();
        System.out.println("Query 8:\t" + res);
    }
}
