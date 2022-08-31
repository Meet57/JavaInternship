package Todo;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
@MultipartConfig
public class Servlet extends HttpServlet {
    private final TodoDAO todoDAO;

    public Servlet() {
        this.todoDAO = new TodoDAO();
        System.out.println("Server Init");
    }

    public void init() throws ServletException {
        // Do required initialization
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.print("Get Request");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request,response);
        PrintWriter out=response.getWriter();
        out.print(request.getParameter("name"));
        out.close();
    }
}
