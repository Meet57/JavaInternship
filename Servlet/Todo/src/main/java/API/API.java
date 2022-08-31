package API;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class API extends HttpServlet {
    public API(){
        System.out.println("Server Init");
    }
    public void init() throws ServletException {
        // Do required initialization
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        resp.setContentType("text/html");
        System.out.println("Get");
        PrintWriter out=resp.getWriter();
        out.write("Get Request");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        resp.setContentType("text/html");
        PrintWriter out=resp.getWriter();
        out.write("Post Request");
        out.print("Post Request");
        out.close();
    }
}
