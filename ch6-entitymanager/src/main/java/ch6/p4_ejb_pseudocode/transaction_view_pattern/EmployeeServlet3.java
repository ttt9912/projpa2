package ch6.p4_ejb_pseudocode.transaction_view_pattern;

import ch6.entities.Employee;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
 * Entity Manager per Request
 * with extended entity manager
 */
public class EmployeeServlet3 extends HttpServlet {
    @EJB
    EmployeeQuery bean;

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Employee> emps = bean.findAll();
            req.setAttribute("employees", emps);
            getServletContext().getRequestDispatcher("/listEmployees.jsp").forward(req, resp);
        } finally {
            bean.finished();
        }
    }
}
