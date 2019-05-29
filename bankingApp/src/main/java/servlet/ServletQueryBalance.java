package servlet;

import bean.User;
import com.google.gson.Gson;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletQueryBalance")
public class ServletQueryBalance extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Gson gson = new Gson();

        String name = request.getParameter("username");

        UserDao userDao = UserDao.getUserDao();

        User user = userDao.getUserByName(name);

        PrintWriter  out = response.getWriter();

        out.write(gson.toJson(user));

    }
}
