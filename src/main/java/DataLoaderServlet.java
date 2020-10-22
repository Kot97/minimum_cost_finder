import impl.DataAccessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/load")
public class DataLoaderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String connection = req.getParameter("connection_string");
            DataAccessor dataAccessor = new DataAccessor(connection);
            dataAccessor.loadData();
            resp.getWriter().write("Data loaded successfully");
        }
        catch (Exception exception) {
            resp.getWriter().write("Error occurred: " + exception.getMessage());
        }
    }
}
