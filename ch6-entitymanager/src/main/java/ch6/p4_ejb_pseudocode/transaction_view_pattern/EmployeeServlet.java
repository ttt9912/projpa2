package ch6.p4_ejb_pseudocode.transaction_view_pattern;

import ch6.entities.Employee;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.*;
import java.util.List;

/*
 * Transaction View Pattern
 * combining a Session Bean Method and JSP in a single Transaction
 */
public class EmployeeServlet extends HttpServlet {
    @Resource
    UserTransaction tx;
    @EJB
    EmployeeServive bean;

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) {
        try {
            tx.begin();
            List<Employee> emps = bean.findAll();
            req.setAttribute("employees", emps);
            getServletContext().getRequestDispatcher("/listEmployees.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                tx.commit();
            } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SystemException e) {
                e.printStackTrace();
            }
        }
    }
}
