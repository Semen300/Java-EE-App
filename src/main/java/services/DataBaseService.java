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
        try{
            Connection con = getConnect();
            Statement statement = con.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void insert(String sql){
        try(Connection con = getConnect()) {
            con.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(String sql){
        try(Connection con = getConnect()){
            con.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete (String sql){
        try(Connection con = getConnect()){
            con.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean exists(String sql){
        boolean is_exists = false;
        try(Connection con = getConnect()){
            ResultSet rs = con.createStatement().executeQuery(sql);
            rs.last();
            int num = rs.getRow();
            if(num>0) is_exists=true;
        } catch(java.sql.SQLException e){
            e.printStackTrace();
        }
        return is_exists;
    }
}
