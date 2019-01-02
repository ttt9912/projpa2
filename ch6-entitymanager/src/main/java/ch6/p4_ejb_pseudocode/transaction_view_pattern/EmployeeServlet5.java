package ch6.p4_ejb_pseudocode.transaction_view_pattern;

import ch6.entities.Employee;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
 * Edit session
 * with stateful session bean
 */
public class EmployeeServlet5 extends HttpServlet {
    @EJB
    EmployeeEdit bean;

    // load managed bean
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        bean.begin(id);

        HttpSession session = req.getSession();
        session.setAttribute("empl.edit", bean);
        session.setAttribute("empl", bean.empl);
        getServletContext().getRequestDispatcher("/editEmpl.jsp")
                .forward(req, resp);
    }

    // close & persist
    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        HttpSession session = req.getSession();
        EmployeeEdit bean = (EmployeeEdit) session.getAttribute("empl.edit");
        session.removeAttribute("empl.edit");
        Employee emp = bean.empl;
        emp.setId(id);
        emp.setName(name);
        bean.save();
        // ...
    }
}
