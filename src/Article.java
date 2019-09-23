import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.ArticlesDAO;
import article.ArticlesDTO;
import comment.CommentsDAO;
import comment.CommentsDTO;
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

            CommentsDAO commentsdao = new CommentsDAO();
            List<CommentsDTO> commentsdto = commentsdao.findByAtrticleId(articleId);

            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"utf-8\">");
            out.println("<title>");
            out.println(articlesdto.getTitle());
            out.println("</title>");
            out.println("<body>");
            out.println("<div class=\"article\">");

            for(int i = 0; i < htmlData.size(); i++) {
                out.print(htmlData.get(i));
            }
            out.println("</div>");

            out.println("<div class=\"commentList\">");
            for(int n = 0; n < commentsdto.size(); n++) {
                out.println("<div>");
                out.println("<hr><p>" + (n + 1) +"</p>");
                out.println("<p class=\"title\">");
                out.println(commentsdto.get(n).getTitle());
                if(commentsdto.get(n).getTitle() == "null") {
                    out.println("無題");
                } else {
                    out.println(commentsdto.get(n).getTitle());
                }
                out.println("</p>");
                out.println("<p class=\"comment\">");
                out.println(commentsdto.get(n).getComment());
                out.println("</p>");
                out.println("<p class=\"user\">");
                out.println(commentsdto.get(n).getUser_id());
                out.println("</p>");
                out.println("</div>");;
            }
            out.println("</div>");

            out.println("<div class=\"commentAdd\">");
            out.println("<hr><p>コメントする</p>");
            out.println("<form action=\"./addComment\" method=\"post\">");
            out.println("<input type=\"hidden\"\" name=\"article_id\" value=\"" + articleId + "\">");
            out.println("<p><input type=\"text\" name=\"title\" placeholder=\"タイトル\"/></p>");
            out.println("<p><textarea name=\"comment\"></textarea></p>");
            out.println("<input type=\"submit\" value=\"投稿\">");
            out.println("</form></div>");

            out.println("<p><a href=\"./articlesList\">記事一覧に戻る</a></p>");
            out.println("</body>");
            out.println("</html>");
            out.close();
    }
}
