package com.example.demo.BAI4;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.ws.rs.core.Request;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class UploadServlet extends HttpServlet {

    public static boolean check(HttpServletRequest request,String filename)
    {
        File file = new File("C:\\Users\\ASUS\\IdeaProjects\\THUC HANH LAB CN JAVA\\LAB04\\demo\\src\\main\\webapp\\uploads\\"+filename);
        if(file.exists())
        {
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("upload.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        Part filePart = request.getPart("file");

        String customFileName = request.getParameter("filename");
        String fileName = filePart.getSubmittedFileName();
        String override = request.getParameter("override");

        fileName=customFileName+fileName.substring(fileName.indexOf('.'),fileName.length());

        if(check(request,fileName)==true && override==null) //nếu không chọn override thì báo tồn tại
        {
            response.getWriter().print("The file uploaded exist.");
        }
        else if(check(request,fileName)==true && override!=null) //nếu không chọn override thì báo tồn tại
        {
            File x = new File("C:\\Users\\ASUS\\IdeaProjects\\THUC HANH LAB CN JAVA\\LAB04\\demo\\src\\main\\webapp\\uploads\\"+fileName);
            x.delete();
        }

            for (Part part : request.getParts())
            {
                part.write("C:\\Users\\ASUS\\IdeaProjects\\THUC HANH LAB CN JAVA\\LAB04\\demo\\src\\main\\webapp\\uploads\\" + fileName);
            }
            response.getWriter().print("The file uploaded sucessfully.");
            response.getWriter().print("<p>Click <a href='/download?file="+fileName+"'>here</a> to visite file</p>");
            response.getWriter().print("<a href='/upload'>Back</a>");

    }
}
