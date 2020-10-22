import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import impl.SearchTable;

@WebServlet("/find")
public class PathFinderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int destination = Integer.parseInt(req.getParameter("indeks"));
            String connection = req.getParameter("connection_string");
            resp.getWriter().write(SearchTable.getResult(connection, destination));
        }
        catch (Exception exception) {
            resp.getWriter().write("Error occurred: " + exception.getMessage());
        }
    }
}
