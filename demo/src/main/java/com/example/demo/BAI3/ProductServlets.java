package com.example.demo.BAI3;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class ProductServlets extends HttpServlet {
    ArrayList<Product> listProducts = new ArrayList<>();
//    private static final long serialVersionUID = 1L;
    @Override
    public void init() throws ServletException {
        this.listProducts.add(new Product(0, "Samsung Z Clip", 1000));
        this.listProducts.add(new Product(1, "iPhone 14", 2000));
        this.listProducts.add(new Product(2, "Samsung Galaxy Note 9", 600));
        this.listProducts.add(new Product(3, "Nokia 1280", 50));
        this.listProducts.add(new Product(4, "Samsung Galaxy Fold 3", 3000));
        this.listProducts.add(new Product(5, "Asus ROG Phone 4", 1000));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JsonObject json = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        int jsonCode = 0;
        String jsonMessage = "";

        //đọc 1 sản phẩm
        if(request.getParameter("id")!=null)
        {
            int productId = Integer.parseInt(request.getParameter("id")); //lấy id trên url
            Product product = null;
            for(Product p:listProducts)
            {
                if(p.getId()==productId)
                {
                    product = p;
                    break;
                }
            }

            if(product!=null) //tìm được sản phẩm và hiện lên json
            {
                JsonObject jsonObjectItem = new JsonObject();
                jsonObjectItem.addProperty("id",product.getId());
                jsonObjectItem.addProperty("name",product.getName());
                jsonObjectItem.addProperty("price",product.getPrice());
                jsonArray.add(jsonObjectItem);

                jsonCode = 0;
                jsonMessage="Đọc sản phẩm thành công";
            }
            else{
                jsonCode = 2;
                jsonMessage = "Không tìm tấy sản phẩm nào phù hợp với mã số";
            }
        }
        //id không có nên đọc hết sản phẩm
        else{
            jsonCode = 0;
            jsonMessage = "Đọc sản phẩm thành công";

            for(Product p:listProducts)
            {
                JsonObject js = new JsonObject();
                js.addProperty("id",p.getId());
                js.addProperty("name",p.getName());
                js.addProperty("price",p.getPrice());
                jsonArray.add(js);
            }
        }
        //Hiện lên màn hình
        json.addProperty("code",jsonCode);
        json.addProperty("message",jsonMessage);
        if(jsonArray.size()>0)
        {
            json.add("data",jsonArray);
        }
        PrintWriter printWriter = response.getWriter();
        printWriter.println(json.toString());
        printWriter.flush();
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }






    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
