package ch7.query;

import ch7.entity.Department;
import ch7.entity.Employee;

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


    // ---------------------------------------------------------
    // Static Queries / Query syntax
    // ---------------------------------------------------------

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

    // implicit Join
    public List<Object[]> implicitJoinEmployeePhones() {
        return em.createQuery("SELECT e.name, p.number FROM Employee e, Phone p " +
                "WHERE e = p.employee", Object[].class)
                .getResultList();
    }

    // explicit JOIN
    public List<Object[]> explicitJoinEmployeePhones() {
        return em.createQuery(
                "SELECT e.name, p.number " +
                        "FROM Employee e JOIN e.phones p", Object[].class)
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

    // Parametrizing with concatenation - very bad!
    // - creates new query each time
    // - injection attacks
    public Employee findByDepartmentAndEmployee0(final String deptName, final String empName) {
        String queryString = "SELECT e " +
                "FROM  Employee e " +
                "WHERE e.department.name = '" + deptName + "' " +
                "AND e.name = '" + empName + "'";


        return em.createQuery(queryString, Employee.class)
                .getSingleResult();
    }

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

    // Dynamic Queries with typed parameters
    public List<Employee> findByDepartmentAndEmployee3(Department department, String empName) {
        final String queryStirng = "SELECT e FROM  Employee e " +
                "WHERE e.department = :dept " +
                "AND e.name = :empName";

        return em.createQuery(queryStirng, Employee.class)
                .setParameter("dept", department)
                .setParameter("empName", empName)
                .getResultList();
    }

}
