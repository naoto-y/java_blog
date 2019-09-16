import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.ArticlesDAO;
import article.ArticlesDTO;
import lib.MarkDownRender;

public class Article extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");

            int articleId =  Integer.parseInt(request.getParameter("id"));
            ArticlesDAO articlesdao = new ArticlesDAO();
            ArticlesDTO articlesdto = articlesdao.findById(articleId);

            MarkDownRender render = new MarkDownRender();
            List<String>htmlData = render.rendering(articlesdto.getArticle_path());

            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>");
            out.println(articlesdto.getTitle());
            out.println("</title>");
            out.println("<body>");

            for(int i = 0; i < htmlData.size(); i++) {
                out.print(htmlData.get(i));
            }

            out.println("<a href=\"./articlesList\">記事一覧に戻る</a>");
            out.println("</body>");
            out.println("</html>");
            out.close();
    }
}
