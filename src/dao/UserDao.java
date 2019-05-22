package dao;

import bean.User;
import jdbcutil.JdbcUtils;
import jdbcutil.UserResultAdapter;

import java.util.List;

public class UserDao {


    private static UserDao userDao;

    private UserDao(){}

    public static synchronized UserDao getUserDao(){

        if(userDao == null){
            userDao = new UserDao();
        }
        return userDao;
    }


    public User getUserByName(String name){

        String sql = "select * from user where name = ?";
        List<User> resultSet = JdbcUtils.query(sql,new UserResultAdapter(),name);
        return resultSet.get(0);

    }

    public void addUser(User user){

        String sql = "insert into user values(?,?,?)";
        JdbcUtils.update(sql,user.getId(),user.getName(),user.getBalance());

    }

    public void updateUserBalance(User user,double amount){

        String sql = "update user set balance = ? where id = ?";
        JdbcUtils.update(sql,user.getBalance()+amount,user.getId());

    }




}
