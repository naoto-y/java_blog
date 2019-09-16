import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.ArticlesDAO;

public class outputFile extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

            ArticlesDAO articlesdao = new ArticlesDAO();

            response.setContentType("text/html; charaset=UTF-8");
            request.setCharacterEncoding("UTF-8");

            String title =request.getParameter("title");
            String item1 = request.getParameter("item1");

            long timepath = System.currentTimeMillis();
            String filedir = null;

            String osname = System.getProperty("os.name");
            if(osname.indexOf("Windows")>=0) {
                filedir = "G:" + File.separator +"tmp" + File.separator;
            } else if(osname.indexOf("Linux")>=0) {
                filedir = "/usr/tomcat9/Data/";
            }

            String filename = filedir + String.valueOf(timepath) + ".txt";
            String sql = null;

            try {
                File file = new File(filename);
                file.createNewFile();
                FileWriter filewriter = new FileWriter(file);
                articlesdao.insertArticle(1, title, filename);
                sql = "INSERT INTO article_list VALUES (null," + 1 + ",0,'" + title + "','" + filename + "',0)";

                filewriter.write(item1);

                filewriter.close();
            } catch(IOException e) {
                PrintWriter errorPW = response.getWriter();
                System.out.println("失敗");
                System.out.println(e);
                errorPW.println(e);
                errorPW.close();
            }

            PrintWriter out = response.getWriter();
            out.println("<html><head></head><body>");
            out.println("<p>"+ filename +"</p>");
            out.println("<p>"+ sql + "</p>");
            out.println("</body></html>");
        }
}