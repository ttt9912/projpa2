package tomcat_test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * open: http://localhost:9090/hello or /hi
 */

@WebServlet(
        description = "My Simple Servlet",
        urlPatterns = {
                "/hello",
                "/hi"
        }
)
public class Example extends HttpServlet {


    private static final long serialVersionUID = 1L;

    public Example() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Hi There!");
        out.println("Welcome to the page!");
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    }
}
