package services;
import java.sql.*;

public class DataBaseService {
    private Connection getConnect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        final String url = "jdbc:mysql://localhost:3306/java_curs_data?serverTimezone=Europe/Moscow&useSSL=false";
        final String user = "root";
        final String password = "root";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return conn;
    }

    public ResultSet select(String sql){
        try {
            Connection con = getConnect();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            //closeConnect(con);
            return rs;
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            return null;
        }
    }

    public boolean insert(String sql){
        boolean isSuccessful = false;
        try {
            Connection con = getConnect();
            int rowsAffected = con.createStatement().executeUpdate(sql);
            if (rowsAffected > 0) {
                isSuccessful = true;
            }
            //closeConnect(con);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return isSuccessful;
    }

    public boolean update(String sql){
        boolean isSuccessful = false;
        try{
            Connection con = getConnect();
            int rowsAffected = con.createStatement().executeUpdate(sql);
            if (rowsAffected > 0) {
                isSuccessful = true;
            }
            //closeConnect(con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isSuccessful;
    }

    public boolean delete (String sql){
        boolean isSuccessful = false;
        try {
            Connection con = getConnect();
            int rowsAffected = con.createStatement().executeUpdate(sql);
            if (rowsAffected > 0) {
                isSuccessful = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isSuccessful;
    }

    public boolean exists(String sql){
        boolean is_esists = false;
        try{
            Connection con = getConnect();
            ResultSet rs = con.createStatement().executeQuery(sql);
            rs.last();
            int num = rs.getRow();
            if(num>0) is_esists=true;
            //closeConnect(con);
        } catch(java.sql.SQLException e){}
        return is_esists;
    }

    private void closeConnect(Connection connection){
        try {
            connection.close();
        }
        catch (java.sql.SQLException e){
            e.printStackTrace();
        }
    }
}
