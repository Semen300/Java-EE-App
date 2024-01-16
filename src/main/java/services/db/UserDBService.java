package services.db;
import services.HashService;
import structure.User;
import java.sql.ResultSet;

public class UserDBService {

    public ResultSet allData(){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "SELECT * from users";
        return dataBaseService.select(request);
    }
    public Boolean create(User user){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "INSERT INTO users (login, password, fio)" +
                "VALUES ('"+user.getLogin()+"', '"+user.getPassword()+"','"+user.getFio()+"')";
        return dataBaseService.insert(request);
    }

    public Boolean update(User user){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "UPDATE users SET password="+user.getPassword()+"', fio='"+user.getFio()+"' WHERE login='"+user.getLogin()+"'";
        return dataBaseService.update(request);
    }

    public Boolean delete(User user){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "DELETE from users where login=" + user.getLogin();
        return dataBaseService.delete(request);
    }
}
