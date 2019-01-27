package pl.sdacademy;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet(name = "Download", value = "/download")

public class Download extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
writer.println("<!DOCTYPE html>\n" +
        "<body>\n" +
        "<h1></h1>\n" +
        "    <p>File name: </p>\n" +
        "<form method=\"post\" action=\"/download\">"+
        "<input type=\"text\" name=\"fileName\"> \n"+
        "    <input type=\"submit\" value=\"Submit\">\n" +
        "</form>\n" +
        "</body>\n");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("fileName");
        ServletContext servletContext=getServletContext();
        InputStream inputStream=servletContext.getResourceAsStream("/"+ fileName);
               if(StringUtils.isBlank(fileName)){
                   response.getWriter().println("<html>" +
                           "<body>" + "<p>Brak nazwy pliku</p>"+
                           "</body>"+
                           "</html>"
                   );
               }
                if(inputStream==null){
                    response.getWriter().println("File not available for download");
                    return;
                }
           response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; fileName=\""+fileName+"\"");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        byte[] buffer = new byte [8*1024];
        int bytesRead;
        while((bytesRead= inputStream.read(buffer)) !=-1){
            responseOutputStream.write(buffer, 0, bytesRead);
        }
        responseOutputStream.flush();
        responseOutputStream.close();

    }
}
