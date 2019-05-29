package servlet;

import bean.User;
import dao.UserDao;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletUserTransfer")
public class ServletUserTransfer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDao userDao = UserDao.getUserDao();

        String userName1 = request.getParameter("username1");
        String userName2 = request.getParameter("username2");

        User user1 = userDao.getUserByName(userName1);
        User user2 = userDao.getUserByName(userName2);


        Double amount = Double.valueOf(request.getParameter("amount"));


        userDao.updateUserBalance(user2,amount);
        amount = amount*(-1);
        userDao.updateUserBalance(user1,amount);




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
