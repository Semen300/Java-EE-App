package services.db;
import structure.Customer;

import java.sql.ResultSet;

public class UserDBService {

    public ResultSet allWorkers(){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "SELECT * from workers";
        return dataBaseService.select(request);
    }

    public ResultSet selectWorker(String login){
        DataBaseService dataBaseService=new DataBaseService();
        String request = "SELECT login, fio, supLogin from workers WHERE login='"+login+"'";
        return dataBaseService.select(request);
    }
    public Boolean createCustomer(Customer customer){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "INSERT INTO customers (login, password, fio, number, email)" +
                "VALUES ('"+ customer.getLogin()+"', '"+ customer.getPassword()+"','"+ customer.getFio()+"','"+ customer.getNumber()+"','"+ customer.getEmail()+"')";
        return dataBaseService.insert(request);
    }
}
