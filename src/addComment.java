import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.CommentsDAO;

public class addComment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

            CommentsDAO commentDAO = new CommentsDAO();

            response.setContentType("text/html; charset=UTF-8");
            request.setCharacterEncoding("UTF-8");

            int article_id = Integer.parseInt(request.getParameter("article_id"));
            String title = request.getParameter("title");
            String comment = request.getParameter("comment");

            commentDAO.insertComment(article_id, 1, title, comment);

            PrintWriter out = response.getWriter();
            out.println("<html><head>");
            out.println("<meta charset=\"utf-8\">");
            out.println("<title>コメントが登録されました</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>コメントが登録されました</p>");
            out.println("<p>※管理者が承認するまでコメントは表示されません</p>");
            out.println("<p><a href=\"./article?id=" + article_id + "\">記事に戻る</p>");
            out.println("</body></html>");
            out.close();
        }
}