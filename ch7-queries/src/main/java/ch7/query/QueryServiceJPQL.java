package ch7.query;

import ch7.data.Employee;

import javax.persistence.EntityManager;
import java.util.List;

/*
 * JPQL: query against application model instead of tables
 * Dynamic Query: pass a query String to the createQuery() Method
 */
public class QueryServiceJPQL {

    private final EntityManager em;

    public QueryServiceJPQL(final EntityManager em) {
        this.em = em;
    }

    // Query: untyped
    public List findEmployeeNames() {
        return em.createQuery("SELECT e.name FROM  Employee e")
                .getResultList();
    }

    // TypedQuery: typesafe
    public List<String> findEmployeeNamesTypeSafe() {
        return em.createQuery("SELECT e.name FROM  Employee e", String.class)
                .getResultList();
    }

    // Filtering
    public List<Employee> findFilteredEmployees() {
        return em.createQuery(
                "SELECT e FROM  Employee e " +
                        "WHERE e.department.name = 'Dept-1a' AND " +
                        "e.name IN ('John', 'Paul')", Employee.class)
                .getResultList();
    }

    // Join
    public List<Object[]> findEmployeesInDepartment() {
        return em.createQuery(
                "SELECT d, e " +
                        "FROM  Department d JOIN d.employees e " +
                        "WHERE e.name = 'John'", Object[].class)
                .getResultList();
    }

    // Aggregate
    public List<Object[]> summarizeDepartment() {
        return em.createQuery(
                "SELECT d, COUNT(e), MAX(e.salary), AVG(e.salary) " +
                        "FROM  Department d JOIN d.employees e " +
                        "GROUP BY d " +
                        "HAVING COUNT(e) > 0", Object[].class)
                .getResultList();
    }

    // ---------------------------------------------------------
    // Dynamic Queries
    // ---------------------------------------------------------

    // Parametrizing with '?'
    public Employee findByDepartmentAndEmployee(final String deptName, final String empName) {
        String queryString = "SELECT e " +
                "FROM  Employee e " +
                "WHERE e.department.name = ?1 " +
                "AND e.name = ?2";


        return em.createQuery(queryString, Employee.class)
                .setParameter(1, deptName)
                .setParameter(2, empName)
                .getSingleResult();
    }

    // Parametrizing with ':param'
    public Employee findByDepartmentAndEmployee2(final String deptName, final String empName) {
        String queryString = "SELECT e " +
                "FROM  Employee e " +
                "WHERE e.department.name = :deptName " +
                "AND e.name = :empName";


        return em.createQuery(queryString, Employee.class)
                .setParameter("deptName", deptName)
                .setParameter("empName", empName)
                .getSingleResult();
    }


}
