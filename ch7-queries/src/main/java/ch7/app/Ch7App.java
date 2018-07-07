package ch7.app;

import ch7.query.QueryServiceJPQL;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Ch7App {

    public static void main(String[] arg) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
        EntityManager em = emf.createEntityManager();
        new BulkRunner(em).insertDefaultData();

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

        System.out.println("Query 6:\t" + jpql.findByDepartmentAndEmployee("Dept-1a", "John"));
    }
}
