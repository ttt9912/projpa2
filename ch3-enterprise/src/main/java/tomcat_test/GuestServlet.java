package tomcat_test;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GuestServlet", urlPatterns = {"/guest"})
public class GuestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Injected DAO EJB:
    @Inject
    GuestDAO guestDAO;

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Display the list of guests:
        request.setAttribute("guests", guestDAO.getAllGuests());
//        request.getRequestDispatcher("/guest.jsp").forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Handle a new guest:
        String name = request.getParameter("name");
        if (name != null)
            guestDAO.persist(new Guest(name));

        // Display the list of guests:
        doGet(request, response);
    }
}
