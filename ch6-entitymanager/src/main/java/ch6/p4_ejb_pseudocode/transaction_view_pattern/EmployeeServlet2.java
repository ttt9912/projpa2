package ch6.p4_ejb_pseudocode.transaction_view_pattern;

import ch6.entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
 * Entity Manager per Request
 * with application managed entity manager
 */
public class EmployeeServlet2 extends HttpServlet {
    @PersistenceUnit(unitName = "DepartmentPU")
    EntityManagerFactory emf;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();

        try {
            List<Employee> emps =
                    em.createQuery("select e from Employee e").getResultList();
            req.setAttribute("employees", emps);
            getServletContext().getRequestDispatcher("/listEmployees.jsp").forward(req, resp);
        } finally {
            em.close();
        }
    }
}
