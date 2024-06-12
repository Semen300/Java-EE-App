package services;

import structure.Contract;
import structure.ContractM;
import structure.Worker;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ContractService {

    public List<ContractM> getContractsByManager(String managerLogin){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "SELECT id, name, deadline, execLogin, consLogin, supLogin FROM (contracts LEFT JOIN workers ON contracts.execLogin=workers.login) where supLogin is NULL or supLogin='"+managerLogin+"'";
        ResultSet resultSet = dataBaseService.select(request);
        List<ContractM> contracts = new ArrayList<>();
        try{
            while(resultSet.next()){
                ContractM contract = new ContractM(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDate("deadline"),
                        resultSet.getString("execLogin")!=null?resultSet.getString("execLogin"):"Не назначен",
                        resultSet.getString("consLogin"),
                        resultSet.getString("supLogin")
                );
                contracts.add(contract);
            }
        } catch (java.sql.SQLException e){}
        return contracts;
    }
    public List<Contract> getContractsByWorker(String workerLogin) {
        DataBaseService dataBaseService = new DataBaseService();
        String request = "SELECT * FROM contracts WHERE execLogin='"+workerLogin+"'";
        ResultSet resultSet = dataBaseService.select(request);
        List<Contract> workerContracts = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Contract contract = new Contract(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDate("deadline"),
                        resultSet.getString("execLogin"),
                        resultSet.getString("consLogin")
                );
                workerContracts.add(contract);
            }
        }
        catch (java.sql.SQLException e) {}
        return workerContracts;
    }

    public List<Contract> getContractsByCustomer(String Login){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "SELECT * FROM contracts WHERE consLogin='"+Login+"'";
        ResultSet resultSet = dataBaseService.select(request);
        List<Contract> consContracts= new ArrayList<>();
        try{
                while (resultSet.next()) {
                    Contract contract = new Contract(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getDate("deadline"),
                            resultSet.getString("execLogin")!=null?resultSet.getString("execLogin"):"Не назначен",
                            resultSet.getString("consLogin")
                    );
                    consContracts.add(contract);
            }
        } catch(java.sql.SQLException e){}
        return consContracts;
    }

    public Contract getContractById (int id){
        DataBaseService dataBaseService = new DataBaseService();
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
            }
        } catch (java.sql.SQLException e){}
        return contract;
    }
    public boolean saveContract(Contract contract){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "INSERT INTO contracts (name, deadline, consLogin) " +
                "VALUES ('"+contract.getName()+"','"+contract.getDeadline()+"','"+contract.getConsLogin()+"')";
        return dataBaseService.insert(request);
    }

    public boolean updateContract(Contract contract){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "UPDATE contracts SET name='" + contract.getName()+"',deadline='"+contract.getDeadline()+"',execLogin='"+contract.getExecLogin()+"' WHERE id="+contract.getId();
        return dataBaseService.update(request);
    }

    public boolean deleteContract(Contract contract){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "DELETE FROM contracts WHERE id=" + contract.getId();
        return dataBaseService.delete(request);
    }
}
