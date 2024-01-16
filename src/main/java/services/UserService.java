package services;

import services.db.UserDBService;
import structure.User;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    public List<User> getAllData(){
        List<User> users=new ArrayList<>();
        UserDBService userDBService=new UserDBService();
        ResultSet Users = userDBService.allData();
        try {
            while (Users.next()) {
                User cur= new User();
                cur.setLogin(Users.getString("login"));
                cur.setFio(Users.getString("fio"));
                users.add(cur);
            }
        } catch (java.sql.SQLException e){}
        return users;
    }
    public void saveUser(User user){
        UserDBService userDBService = new UserDBService();
        HashService hashService = new HashService();
        String hashedPass = hashService.createHash(user.getPassword());
        user.setPassword(hashedPass);
        userDBService.create(user);
    }

    public void updateUser(User user){
        UserDBService userDBService = new UserDBService();
        HashService hashService = new HashService();
        String hashedPass = hashService.createHash(user.getPassword());
        user.setPassword(hashedPass);
        userDBService.update(user);
    }
    public void deleteUser(User user){
        UserDBService userDBService = new UserDBService();
        userDBService.delete(user);
    }
}
