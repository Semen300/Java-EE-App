package services;

import services.db.LoginDBService;
import java.util.Date;

public class LoginService {
    public int auth(String login, String password){
        LoginDBService loginDBService=new LoginDBService();
        HashService hashService = new HashService();
        String hashedPass = hashService.createHash(password);
        int role = loginDBService.getRoleByLogin(login);
        String pass = loginDBService.getPassByLogin(login);
        if(pass!=null && pass.equals(hashedPass)){
            return loginDBService.getRoleByLogin(login);
        }
        else return 0;
    }


    public String createSession(String login){
        LoginDBService loginDBService=new LoginDBService();
        HashService hashService = new HashService();
        String session = hashService.createHash(login+new Date());
        loginDBService.createSession(login, session);
        return session;
    }
    public String getLoginBySession(String session){
        LoginDBService loginDBService=new LoginDBService();
        return loginDBService.getLoginBySession(session);
    }


    public void logout(String login){
        LoginDBService loginDBService=new LoginDBService();
        loginDBService.logout(login);
    }
}
