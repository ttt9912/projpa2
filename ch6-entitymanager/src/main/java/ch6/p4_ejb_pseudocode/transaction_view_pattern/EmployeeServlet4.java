package ch6.p4_ejb_pseudocode.transaction_view_pattern;

import ch6.entities.Employee;

import javax.ejb.EJB;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * Session Facade
 * with stateless session bean
 */
public class EmployeeServlet4 extends HttpServlet {
    @EJB
    EmployeeServive bean;

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) {
        long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        HttpSession session = req.getSession();
        Employee emp = (Employee) session.getAttribute("employee.edit");
        emp.setId(id);
        emp.setName(name);
        bean.updateEmployee(emp);
        // ...
    }
}
