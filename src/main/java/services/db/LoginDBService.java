package services.db;
import services.db.DataBaseService;

import java.sql.ResultSet;

public class LoginDBService {
    public String getPassByLogin(int role, String login){
        String request = null;
        switch  (role){
            case 1: {
                request = "SELECT password FROM rabotyagi WHERE login='"+login+"'";
                break;
            }
            case 2: {
                request = "SELECT password FROM managers WHERE login='"+login+"'";
                break;
            }
            case 3: {
                request = "SELECT password FROM customers WHERE login='"+login+"'";
                break;
            }
        }
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
        String request1 = "SELECT * FROM rabotyagi WHERE login='"+login+"'";
        String request2 = "SELECT * FROM managers WHERE login='"+login+"'";
        String request3 = "SELECT * FROM customers WHERE login='"+login+"'";
        DataBaseService dataBaseService=new DataBaseService();
        boolean is_worker = dataBaseService.exists(request1);
        boolean is_manager = dataBaseService.exists(request2);
        boolean is_customer = dataBaseService.exists(request3);
        int role = 0;
        if(is_customer) role = 3;
        else if(is_manager) role = 2;
        else if(is_worker) role = 1;
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

