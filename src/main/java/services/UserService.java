package services;

import structure.Customer;
import structure.Worker;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    public List<Worker> getWorkersByManager(String login){
        List<Worker> workers = new ArrayList<>();
        DataBaseService dataBaseService = new DataBaseService();
        ContractService contractService = new ContractService();
        String request = "SELECT * FROM workers WHERE supLogin='"+login+"'";
        ResultSet resultSet = dataBaseService.select(request);
        try{
            while(resultSet.next()){
                Worker worker = new Worker(
                        resultSet.getString("login"),
                        "",
                        resultSet.getString("fio"),
                        login,
                        contractService.getNumberOfContractsByWorker(resultSet.getString("login"))
                );
                workers.add(worker);
            }
            resultSet.close();
        } catch(java.sql.SQLException e) {
            e.printStackTrace();
        }
        return workers;
    }
    public boolean in_system(String login){
        LoginService loginService=new LoginService();
        return loginService.getRoleByLogin(login)!=0;
    }

    public void createCustomer(String login, String protopass, String fio, String number, String email){
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
        dataBaseService.insert(request);
    }
}
