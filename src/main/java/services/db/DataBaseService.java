package services.db;
import java.sql.*;

public class DataBaseService {
    private Connection getConnect(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        final String url = "jdbc:postgresql://217.107.219.154:49307/bonch_2105569";
        final String user = "bonch_2105569";
        final String password = "imfuXXn9tPD7DnKf";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
        }
        return conn;
    }

    public ResultSet select(String sql){
        Statement statement = null;
        try {
            statement = getConnect().createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            return null;
        }
    }

    public boolean insert(String sql){
        boolean isSuccessful = false;
        Statement statement = null;
        try {
            statement = getConnect().createStatement();
            int rowsAffected = statement.executeUpdate(sql);
            if (rowsAffected > 0) {
                isSuccessful = true;
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return isSuccessful;
    }

    public boolean update(String sql){
        boolean isSuccessful = false;
        Statement statement = null;
        try{
            statement = getConnect().createStatement();
            int rowsAffected = statement.executeUpdate(sql);
            if (rowsAffected > 0) {
                isSuccessful = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isSuccessful;
    }

    public boolean delete (String sql){
        boolean isSuccessful = false;
        Statement statement = null;
        try {
            statement = getConnect().createStatement();
            int rowsAffected = statement.executeUpdate(sql);
            if (rowsAffected > 0) {
                isSuccessful = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isSuccessful;
    }

    public boolean exists(String sql){
        Statement statement = null;
        boolean is_esists = false;
        try{
            statement = getConnect().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            rs.last();
            int num = rs.getRow();
            if(num>0) is_esists=true;
        } catch(java.sql.SQLException e){}
        return is_esists;
    }
}
