package services;

import org.apache.taglibs.standard.lang.jstl.NullLiteral;
import services.db.ContractDBService;
import structure.Contract;
import structure.Worker;
import structure.Customer;

import javax.lang.model.type.NullType;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ContractService {

    public List<Contract> getContractsByWorker(Worker worker){
        ContractDBService contractDBService=new ContractDBService();
        ResultSet allContracts = contractDBService.allData();
        List<Contract> userContracts= new ArrayList<>();
        try{
            if(allContracts!=null) {
                while (allContracts.next()) {
                    Contract contract = new Contract();
                    contract.setId(allContracts.getInt("id"));
                    contract.setName(allContracts.getString("name"));
                    contract.setDeadline(allContracts.getDate("deadline"));
                    contract.setExecLogin(allContracts.getString("execLogin"));
                    contract.setConsLogin(allContracts.getString("consLogin"));
                    if (contract.getExecLogin().equals(worker.getLogin())) {
                        userContracts.add(contract);
                    }
                }
            }
        } catch(java.sql.SQLException e){}
        return userContracts;
    }

    public List<Contract> getContractsByConsumer(String Login){
        ContractDBService contractDBService=new ContractDBService();
        ResultSet allContracts = contractDBService.allData();
        List<Contract> userContracts= new ArrayList<>();
        try{
            if(allContracts!=null) {
                while (allContracts.next()) {
                    Contract contract = new Contract();
                    contract.setId(allContracts.getInt("id"));
                    contract.setName(allContracts.getString("name"));
                    contract.setDeadline(allContracts.getDate("deadline"));
                    contract.setExecLogin(allContracts.getString("execLogin"));
                    if(contract.getExecLogin()==null){contract.setExecLogin("Не назначен");}
                    contract.setConsLogin(allContracts.getString("consLogin"));
                    if (contract.getConsLogin().equals(Login)) {
                        userContracts.add(contract);
                    }
                }
            }
        } catch(java.sql.SQLException e){}
        return userContracts;
    }

    public Contract getContractById (int id){
        ContractDBService contractDBService = new ContractDBService();
        ResultSet rs = contractDBService.select(id);
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

    public List<Contract> getAllData (){
        ContractDBService contractDBService=new ContractDBService();
        List<Contract> contractList = new ArrayList<>();
        ResultSet rs = contractDBService.allData();
        try{
            if(rs!=null){
                rs.next();
                Contract contract = new Contract();
                contract.setId(rs.getInt("id"));
                contract.setName(rs.getString("name"));
                contract.setDeadline(rs.getDate("deadline"));
                contract.setExecLogin(rs.getString("execLogin"));
                contract.setConsLogin(rs.getString("consLogin"));
                contractList.add(contract);
            }
        } catch (java.sql.SQLException e){};
        return contractList;
    }
    public void saveContract(Contract contract){
        ContractDBService contractDBService=new ContractDBService();
        contractDBService.create(contract);
    }

    public void updateContract(Contract contract){
        ContractDBService contractDBService=new ContractDBService();
        contractDBService.update(contract);
    }

    public void deleteContract(Contract contract){
        ContractDBService contractDBService=new ContractDBService();
        contractDBService.delete(contract);
    }
}
