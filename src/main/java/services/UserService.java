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
        } catch (java.sql.SQLException e){
            System.out.println(e.getMessage());
        }
        catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        return users;
    }

    public User getUserByLogin(String login){
        UserDBService userDBService=new UserDBService();
        User user = new User();
        ResultSet rs = userDBService.select(login);
        if(rs!=null) {
            try {
                rs.next();
                user.setLogin(rs.getString("login"));
                user.setFio(rs.getString("fio"));
            } catch (java.sql.SQLException e) {
            }
        }
        return user;
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
