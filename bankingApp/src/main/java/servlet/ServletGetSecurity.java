package servlet;


import bean.Crawling;
import bean.Securities;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;

@javax.servlet.annotation.WebServlet(name = "ServletGetSecurity")
public class ServletGetSecurity extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {

        Securities[] securities = Crawling.getSecuritiesInfo();

        Gson gson = new Gson();

        gson.toJson(securities);

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();

        out.write(gson.toJson(securities));


    }


//    @Test
//    public void test(){
//
//        Securities[] securities = Crawling.getSecuritiesInfo();
//
//        Gson gson = new Gson();
//
//        String str = gson.toJson(securities);
//
//        System.out.println(str);

//
//    }

}
