package jdbcutil;

import org.junit.Test;

import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class JdbcUtils {


    private static Properties properties = new Properties();

    static{

        try{


            InputStream is = Thread.currentThread().
                    getContextClassLoader().getResourceAsStream("jdbc.properties");

            properties.load(is);
            Class.forName(properties.getProperty("driverClass"));



        }catch(Exception e){
            e.printStackTrace();
        }

    }


    public static Connection getConnection(){




        try{


            return DriverManager.getConnection(properties.getProperty("url"),
                    properties.getProperty("user"),properties.getProperty("password"));
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }

    }


    public static void close(Connection connection, PreparedStatement preparedStatement,
                             ResultSet resultSet){

        if(connection != null){

            try{

                connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }finally {
                if(preparedStatement != null){
                    try{
                        preparedStatement.close();
                    }catch(SQLException e){
                        e.printStackTrace();
                    }finally {
                        if(resultSet != null){
                            try{
                                resultSet.close();
                            }catch(SQLException e){
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

        }


    }


    public static void update(String sql,Object...params){

        Connection connection = null;
        PreparedStatement preparedStatement = null;


        try{
            connection = JdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
                if(params != null){

                    for(int i = 0;i<params.length;i++){
                        preparedStatement.setObject(i+1,params[i]);
                    }

                }
            preparedStatement.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            JdbcUtils.close(connection,preparedStatement,null);
        }

    }


    public static <T> T query(String sql,ResultAdapter<T> resultAdapter,Object...params){

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{

            connection = JdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if(params != null){

                for(int i = 0;i<params.length;i++){
                    preparedStatement.setObject(i+1,params[i]);
                }

            }

            resultSet = preparedStatement.executeQuery();

            return resultAdapter.handle(resultSet);

        }catch(SQLException e){

            e.printStackTrace();
            return null;
        }finally {
            JdbcUtils.close(connection,preparedStatement,resultSet);
        }



    }





}
