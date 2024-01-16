package services.db;
import services.db.DataBaseService;

import java.sql.ResultSet;

public class LoginDBService {
    public String getPassByLogin(String login){
        String request = "SELECT password FROM users WHERE login='"+login+"'";
        DataBaseService dataBaseService=new DataBaseService();
        ResultSet passwords= dataBaseService.select(request);
        String password = null;
        try{
            passwords.next();
            password=passwords.getString("password");
        } catch (java.sql.SQLException e){}
        return password;
    }

    public int getRoleByLogin(String login){
        String request = "SELECT role FROM users WHERE login='"+login+"'";
        DataBaseService dataBaseService=new DataBaseService();
        ResultSet roles= dataBaseService.select(request);
        int role = 0;
        try{
            roles.next();
            role=roles.getInt("role");
        } catch(java.sql.SQLException e){}
        return role;
    }

    public void createSession(String login, String session){
        String request = "INSERT INTO sessions (login, session) values ('"+login+"','"+session+"')";
        DataBaseService dataBaseService=new DataBaseService();
        dataBaseService.insert(request);
    }

    public String getLoginBySession(String session){
        String request = "SELECT login FROM sessions WHERE session = '"+session+"'";
        DataBaseService dataBaseService = new DataBaseService();
        ResultSet resultSet = dataBaseService.select(request);
        String login = null;
        try{
            resultSet.next();
            login = resultSet.getString("login");
        } catch (java.sql.SQLException e) {}
        return login;
    }

    public void logout(String login){
        String request = "DELETE FROM sessions WHERE login = '"+login+"'";
        DataBaseService dataBaseService=new DataBaseService();
        dataBaseService.delete(request);
    }
}

