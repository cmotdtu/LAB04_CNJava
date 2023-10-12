package com.example.demo.BAI2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.net.SocketException;


public class ImageServlet2 extends HttpServlet {
    public ImageServlet2() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        File file = new File("C:\\Users\\ASUS\\IdeaProjects\\THUC HANH LAB CN JAVA\\LAB04\\demo\\src\\main\\resources\\image2.png");
        RandomAccessFile accessFile = new RandomAccessFile(file, "r");
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.addHeader("Content-Disposition", "attachment; filename=\"\"");
        resp.addHeader("Content-Length", String.valueOf(accessFile.length()));
        HttpServletRequest req = (HttpServletRequest) request;

        OutputStream out = resp.getOutputStream();
        try {
            int kb = 1024;
            int bufferLength = 1024 * kb;
            byte[] buffer = new byte[bufferLength];
            while (accessFile.read(buffer, 0, buffer.length) != -1) {
                long length = accessFile.length() - accessFile.getFilePointer();
                if (length > 0) {
                    out.write(buffer, 0, buffer.length);
                } else {
                    length = accessFile.length() - ((accessFile.length() / buffer.length) * buffer.length);
                    out.write(buffer, 0, (int) length);
                }
                out.flush();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Bandwidth Limitation Error");
                }
            }
            out.close();
            accessFile.close();
        } catch (IOException throwable) {
            if (throwable.getCause() instanceof SocketException) {
                System.out.println("User Clicked On Cancel or Connection Reset");
                out.close();
                accessFile.close();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
