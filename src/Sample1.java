import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Sample1 extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{

    response.setContentType("text/html; charset=Shift_JIS");
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head>");
    out.println("<title>データベーステスト</title>");
    out.println("</head>");
    out.println("<body>");

    out.println("<p>");

    Connection conn = null;
    String url =  System.getenv("DB_HOST") + "/blog_sys";
    String user = System.getenv("DB_USER");
    String password = System.getenv("DB_PASS");

    try {
      Class.forName(System.getenv("DB_DRIVER"));
      out.println("ドライバのロードに成功しました<br>");

      conn = DriverManager.getConnection(url, user, password);
      out.println("データベース接続に成功しました<br>");
    }catch (ClassNotFoundException e){
      out.println("ClassNotFoundException:" + e.getMessage());
    }catch (SQLException e){
      out.println("SQLException:" + e.getMessage());
    }catch (Exception e){
      out.println("Exception:" + e.getMessage());
    }finally{
      try{
        if (conn != null){
          conn.close();
          out.println("データベース切断に成功しました");
        }else{
          out.println("コネクションがありません");
        }
      }catch (SQLException e){
        out.println("SQLException:" + e.getMessage());
      }
    }

    out.println("</p>");

    out.println("</body>");
    out.println("</html>");
  }
}