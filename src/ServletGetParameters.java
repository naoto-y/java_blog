import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletFormData")//[1]
public class ServletGetParameters extends HttpServlet {//[2]
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {//[3]
        response.setContentType("text/html; charset=UTF-8");//[4]
        request.setCharacterEncoding("UTF-8");

        String item1 = request.getParameter("item1");//[5]
        String item2 = request.getParameter("item2");//[6]
        PrintWriter out = response.getWriter();//[7]
        out.println("<html><head></head><body>");//[8]
        out.println("<p>入力された項目を表示します。</p>");//[9]
        out.println("<p>入力項目１：" + item1 + "</p>");//[10]
        out.println("<p>入力項目２：" + item2 + "</p>");//[11]
        out.println("</body></html>");//[12]
    }
}
