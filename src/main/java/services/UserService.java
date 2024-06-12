package services;

import structure.Customer;
import structure.Worker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    public List<Worker> getWorkersByManager(String login){
        List<Worker> workers = new ArrayList<>();
        DataBaseService dataBaseService = new DataBaseService();
        String request = "SELECT * FROM workers WHERE supLogin='"+login+"'";
        ResultSet resultSet = dataBaseService.select(request);
        try{
            while(resultSet.next()){
                Worker worker = new Worker(
                        resultSet.getString("login"),
                        "",
                        resultSet.getString("fio"),
                        ""
                );
                workers.add(worker);
            }
        } catch(java.sql.SQLException e) {}
        return workers;
    }
    public List<Worker> getAllWorkers(){
        List<Worker> workers=new ArrayList<>();
        DataBaseService dataBaseService = new DataBaseService();
        String request = "SELECT * FROM workers";
        ResultSet Users = dataBaseService.select(request);
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
        Worker worker = new Worker();
        DataBaseService dataBaseService=new DataBaseService();
        String request = "SELECT login, fio, supLogin from workers WHERE login='"+login+"'";
        ResultSet rs = dataBaseService.select(request);
        if(rs!=null) {
            try {
                rs.next();
                worker.setLogin(rs.getString("login"));
                worker.setFio(rs.getString("fio"));
                worker.setSupLogin(rs.getString("supLogin"));
            } catch (java.sql.SQLException e) {
            }
        }
        return worker;
    }

    public boolean in_system(String login){
        LoginService loginService=new LoginService();
        return loginService.getRoleByLogin(login)!=0;
    }

    public boolean createCustomer(String login, String protopass, String fio, String number, String email){
        HashService hashService=new HashService();
        DataBaseService dataBaseService = new DataBaseService();
        Customer customer = new Customer(
                login,
                hashService.createHash(protopass),
                fio,
                number,
                email
        );
        String request = "INSERT INTO customers (login, password, fio, number, email)" +
                "VALUES ('"+ customer.getLogin()+"', '"+ customer.getPassword()+"','"+ customer.getFio()+"','"+ customer.getNumber()+"','"+ customer.getEmail()+"')";
        return dataBaseService.insert(request);
    }
}
