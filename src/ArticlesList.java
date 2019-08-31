import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.ArticlesDAO;
import article.ArticlesDTO;

public class ArticlesList extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");

            ArticlesDAO articlesdao = new ArticlesDAO();
            List<ArticlesDTO> articles = articlesdao.findAll();

            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>記事一覧</title>");
            out.println("</head>");
            out.println("<body>");

            for(int n = 0; n < articles.size(); n++) {
                out.println("<p>");
                out.println("<a href=\"./article?id=" + articles.get(n).getId() + "\">");
                out.println(articles.get(n).getTitle());
                out.println("</a>");
                out.println("</p>");
            }

            out.println("</body>");
            out.println("</html>");
            out.close();
    }
}
