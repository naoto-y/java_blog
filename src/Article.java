import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.ArticlesDAO;
import article.ArticlesDTO;

public class Article extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");

            int articleId =  Integer.parseInt(request.getParameter("id"));
            ArticlesDAO articlesdao = new ArticlesDAO();
            ArticlesDTO articlesdto = articlesdao.findById(articleId);

            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>");
            out.println(articlesdto.getTitle());
            out.println("</title>");
            out.println("<body>");
            out.println(articlesdto.getArticle_path());
            out.println("</body>");
            out.println("</html>");
            out.close();
    }
}
