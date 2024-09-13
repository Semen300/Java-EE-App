package services;

import structure.Contract;
import structure.ContractM;
import structure.Task;
import structure.Worker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContractService {

    public List<Contract> getAllContracts(){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "SELECT * FROM contracts";
        ResultSet resultSet = dataBaseService.select(request);
        List<Contract> contractList = new ArrayList<>();
        ContractService contractService = new ContractService();
        try{
            while(resultSet.next()){
                contractList.add(
                        new Contract(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getDate("deadline"),
                                resultSet.getString("execLogin"),
                                resultSet.getString("consLogin"),
                                contractService.getPercentOfCompletion(resultSet.getInt("id")),
                                resultSet.getInt("status")
                        )
                );
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return contractList;
    }
    public List<ContractM> getContractsByManager(String managerLogin){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "SELECT id, name, deadline, execLogin, consLogin, supLogin, status FROM (contracts LEFT JOIN workers ON contracts.execLogin=workers.login) where supLogin is NULL or supLogin='"+managerLogin+"'";
        ResultSet resultSet = dataBaseService.select(request);
        List<ContractM> contracts = new ArrayList<>();
        ContractService contractService = new ContractService();
        try{
            while(resultSet.next()){
                ContractM contract = new ContractM(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDate("deadline"),
                        resultSet.getString("execLogin")!=null?resultSet.getString("execLogin"):"Не назначен",
                        resultSet.getString("consLogin"),
                        resultSet.getString("supLogin"),
                        contractService.getPercentOfCompletion(resultSet.getInt("id")),
                        resultSet.getInt("status")
                );
                contracts.add(contract);
            }
        } catch (java.sql.SQLException e){
            e.printStackTrace();
        }
        return contracts;
    }
    public List<Contract> getContractsByWorker(String workerLogin) {
        DataBaseService dataBaseService = new DataBaseService();
        String request = "SELECT * FROM contracts WHERE execLogin='"+workerLogin+"'";
        ResultSet resultSet = dataBaseService.select(request);
        List<Contract> workerContracts = new ArrayList<>();
        ContractService contractService = new ContractService();
        try {
            while (resultSet.next()) {
                Contract contract = new Contract(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDate("deadline"),
                        resultSet.getString("execLogin"),
                        resultSet.getString("consLogin"),
                        contractService.getPercentOfCompletion(resultSet.getInt("id")),
                        resultSet.getInt("status")
                );
                workerContracts.add(contract);
            }
        }
        catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return workerContracts;
    }

    public List<Contract> getContractsByCustomer(String Login){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "SELECT * FROM contracts WHERE consLogin='"+Login+"'";
        ResultSet resultSet = dataBaseService.select(request);
        List<Contract> consContracts= new ArrayList<>();
        ContractService contractService = new ContractService();
        try{
                while (resultSet.next()) {
                    Contract contract = new Contract(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getDate("deadline"),
                            resultSet.getString("execLogin")!=null?resultSet.getString("execLogin"):"Не назначен",
                            resultSet.getString("consLogin"),
                            contractService.getPercentOfCompletion(resultSet.getInt("id")),
                            resultSet.getInt("status")
                    );
                    consContracts.add(contract);
            }
        } catch(java.sql.SQLException e){
            e.printStackTrace();
        }
        return consContracts;
    }

    public Contract getContractById (int id){
        DataBaseService dataBaseService = new DataBaseService();
        TaskService taskService = new TaskService();
        String request = "SELECT * FROM contracts WHERE id='"+id+"'";
        ResultSet rs = dataBaseService.select(request);
        Contract contract = new Contract();
        try {
            if (rs != null) {
                rs.next();
                contract.setId(rs.getInt("id"));
                contract.setName(rs.getString("name"));
                contract.setDeadline(rs.getDate("deadline"));
                contract.setExecLogin(rs.getString("execLogin"));
                contract.setConsLogin(rs.getString("consLogin"));
                List<Task> tasks = taskService.getTaskByContract(contract.getId());
                contract.setTasks(tasks);
            }
        } catch (java.sql.SQLException e){
            e.printStackTrace();
        }
        return contract;
    }
    public boolean saveContract(Contract contract){
        DataBaseService dataBaseService = new DataBaseService();
        StringBuilder tasksRequest = new StringBuilder("INSERT INTO tasks (name, conID, itemID, amount) VALUES ");
        for(Task task : contract.getTasks()){
            tasksRequest.append("('").append(task.getName()).append("','").append(contract.getId()).append("','").append(task.getItem().getId()).append("','").append(task.getAmount()).append("')");
        }
        StringService.replaceAll(tasksRequest, ")(", "),(");
        String contractRequest = "INSERT INTO contracts (id, name, deadline, consLogin, status) " +
                "VALUES ('"+contract.getId()+"','"+contract.getName()+"','"+contract.getDeadline()+"','"+contract.getConsLogin()+"','"+contract.getStatus()+"')";
        return dataBaseService.insert(contractRequest) && dataBaseService.insert(String.valueOf(tasksRequest));
    }

    public boolean updateContract(Contract contract){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "UPDATE contracts SET name='" + contract.getName()+"',deadline='"+contract.getDeadline()+"',execLogin='"+contract.getExecLogin()+"',status='"+contract.getStatus()+"' WHERE id="+contract.getId();
        return dataBaseService.update(request);
    }

    public boolean deleteContract(Contract contract){
        DataBaseService dataBaseService = new DataBaseService();
        TaskService taskService = new TaskService();
        if(contract.getTasks()!=null) {
            for (Task task : contract.getTasks()) {
                taskService.deleteTask(task);
            }
        }
            String request = "DELETE FROM contracts WHERE id=" + contract.getId();
            return dataBaseService.delete(request);
    }

    public int getLastID(){
        int ID = 0;
        DataBaseService dataBaseService = new DataBaseService();
        String request = "SELECT * FROM contracts";
        ResultSet resultSet = dataBaseService.select(request);
        try {
            if(resultSet.last()) {
                ID = resultSet.getInt("id");
            } else ID = 1;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return ID;
    }
    public int getNumberOfContractsByWorker(String workerLogin){
        int numberOfContracts=0;
        List<Contract> contracts = getAllContracts();
        for(Contract contract : contracts){
            if(contract.getExecLogin()!=null && contract.getExecLogin().equals(workerLogin)) numberOfContracts++;
        }
        return numberOfContracts;
    }
    public int getNumberOfUnfinishedTasks(int contractID){
        int numberOfUnfinishedTasks = 0;
        TaskService taskService = new TaskService();
        List<Task> taskList = taskService.getTaskByContract(contractID);
        for(Task task : taskList){
            if(!task.isFinished()) numberOfUnfinishedTasks++;
        }
        return numberOfUnfinishedTasks;
    }

    public float getPercentOfCompletion(int contractID){
        String finishedRequest = "SELECT * FROM tasks WHERE finished = '1' AND conID='"+contractID+"'";
        String allRequest = "SELECT * FROM tasks WHERE conID='"+contractID+"'";
        int finishedAmount = 0;
        int allAmount = 0;
        DataBaseService dataBaseService = new DataBaseService();
        ResultSet finishedRS = dataBaseService.select(finishedRequest);
        ResultSet allRS = dataBaseService.select(allRequest);
        try{
            while(finishedRS.next()){
                finishedAmount+=finishedRS.getInt("amount");
            }
            while(allRS.next()){
                allAmount+=allRS.getInt("amount");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return (float)finishedAmount / allAmount * 100;
    }
}
