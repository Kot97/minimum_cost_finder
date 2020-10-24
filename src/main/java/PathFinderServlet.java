import impl.DataAccessor;
import impl.SearchTable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/find")
public class PathFinderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String connectionString = req.getParameter("connection_string");
            if (req.getParameterMap().containsKey("user") && req.getParameterMap().containsKey("password")) {
                DataAccessor dataAccessor = new DataAccessor(connectionString);
                dataAccessor.loadData(req.getParameter("user"), req.getParameter("password"));
            }

            int destination = Integer.parseInt(req.getParameter("indeks"));
            resp.getWriter().write(SearchTable.getResult(connectionString, destination));
        } catch (Exception exception) {
            resp.getWriter().write("Error occurred: " + exception.getMessage());
        }
    }
}
