package services;

import java.sql.ResultSet;
import java.util.Date;

public class LoginService {

    public int getRoleByLogin(String login){
        String request1 = "SELECT * FROM workers WHERE login='"+login+"'";
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
    public int auth(String login, String password){
        HashService hashService = new HashService();
        String hashedPass = hashService.createHash(password);
        int role = getRoleByLogin(login);
        String request = null;
        switch  (role){
            case 1: {
                request = "SELECT password FROM workers WHERE login='"+login+"'";
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
        String pass = null;
        if(passwords!=null) {
            try {
                passwords.next();
                pass = passwords.getString("password");
                passwords.close();
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        if(pass!=null && pass.equals(hashedPass)){
            return role;
        }
        else return 0;
    }


    public String createSession(String login){
        DataBaseService dataBaseService=new DataBaseService();
        HashService hashService = new HashService();
        String session = hashService.createHash(login+new Date());
        String request = "INSERT INTO sessions (login, session) values ('"+login+"','"+session+"')";
        dataBaseService.insert(request);
        return session;
    }
    public String getLoginBySession(String session){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "SELECT login FROM sessions WHERE session = '"+session+"'";
        ResultSet resultSet = dataBaseService.select(request);
        String login = null;
        try{
            resultSet.next();
            login = resultSet.getString("login");
            resultSet.close();
        } catch (java.sql.SQLException e) {}
        return login;
    }


    public void logout(String login){
        DataBaseService dataBaseService=new DataBaseService();
        String request = "DELETE FROM sessions WHERE login = '"+login+"'";
        dataBaseService.delete(request);
    }
}
