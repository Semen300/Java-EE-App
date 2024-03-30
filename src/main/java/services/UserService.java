package services;

import services.db.LoginDBService;
import services.db.UserDBService;
import structure.Customer;
import structure.Worker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    public List<Worker> getAllWorkers(){
        List<Worker> workers=new ArrayList<>();
        UserDBService userDBService=new UserDBService();
        ResultSet Users = userDBService.allWorkers();
        try {
            while (Users.next()) {
                Worker cur= new Worker();
                cur.setLogin(Users.getString("login"));
                cur.setFio(Users.getString("fio"));
                workers.add(cur);
            }
        } catch (SQLException | NullPointerException e){
            e.printStackTrace();
        }
        return workers;
    }

    public Worker getWorkerByLogin(String login){
        UserDBService userDBService=new UserDBService();
        Worker user = new Worker();
        ResultSet rs = userDBService.selectWorker(login);
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

    public boolean in_system(String login){
        LoginDBService loginDBService=new LoginDBService();
        return loginDBService.getRoleByLogin(login)!=0;
    }

    public boolean createCustomer(String login, String protopass, String fio, String number, String email){
        HashService hashService=new HashService();
        UserDBService userDBService=new UserDBService();
        Customer customer = new Customer(
                login,
                hashService.createHash(protopass),
                fio,
                number,
                email
        );
        return userDBService.createCustomer(customer);
    }
}
