package jdbcutil;

import bean.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserResultAdapter implements ResultAdapter<List<User>>{


    @Override
    public List<User> handle(ResultSet resultSet) {

        List<User> userList = new ArrayList<>();
        try {


            while (resultSet.next()) {

                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setBalance(resultSet.getDouble(3));
                userList.add(user);
            }

            return userList;


        } catch (SQLException e){

            e.printStackTrace();
            return null;

        }



    }
}
