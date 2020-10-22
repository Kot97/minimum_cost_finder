import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import impl.SearchTable;

@WebServlet("/clear")
public class ResultCleanerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            boolean clearAll = req.getParameterMap().containsKey("all") && req.getParameter("all").equals("true");
            if (clearAll) {
                int count = SearchTable.clearAll();
                resp.getWriter().write(String.format("%d search tables deleted", count));
            } else {
                String connection = req.getParameter("connection_string");
                SearchTable.clear(connection);
                resp.getWriter().write(connection + " related search table deleted");
            }
        }
        catch (Exception exception) {
            resp.getWriter().write("Error occurred: " + exception.getMessage());
        }
    }
}
