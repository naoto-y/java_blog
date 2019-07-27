import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class outputFile extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			response.setContentType("text/html; charaset=UTF-8");
			request.setCharacterEncoding("UTF-8");

			String item1 = request.getParameter("item1");

			long timepath = System.currentTimeMillis();
			String filename = "./Data/"+String.valueOf(timepath)+".txt";

			try {
				System.out.println(filename);
				File file = new File(filename);
				file.createNewFile();
//				FileWriter filewriter = new FileWriter(file);

//				filewriter.write(item1);

//				filewriter.close();
			} catch(IOException e) {
				System.out.println("失敗");
				System.out.println(e);
			}

			PrintWriter out = response.getWriter();
	        out.println("<html><head></head><body>");
	        out.println("<p>"+ filename +"</p>");
	        out.println("</body></html>");
		}
}
