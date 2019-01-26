package pl.sdacademy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@WebServlet(name = "HelloWorldServlet", value = "/helloWorld")
public class HelloWorldServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        PrintWriter writer = response.getWriter();
        writer.println("<!DOCTYPE html>\n" +
                "<head> \n" +
                "    <title>Dynamiczna strona www</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "Dzisiaj jest " + LocalDateTime.now() + "\n" +
                "</body>\n" +
                "</html>");
    }
}
