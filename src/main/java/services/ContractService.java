package services;

import services.db.ContractDBService;
import structure.Contract;
import structure.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ContractService {

    public List<Contract> getContractsByUser(User user){
        ContractDBService contractDBService=new ContractDBService();
        ResultSet allContracts = contractDBService.allData();
        List<Contract> userContracts= new ArrayList<>();
        try{
            if(allContracts!=null) {
                while (allContracts.next()) {
                    Contract contract = new Contract();
                    contract.setId(allContracts.getInt("id"));
                    contract.setName(allContracts.getString("name"));
                    contract.setDisc(allContracts.getString("disc"));
                    contract.setDeadline(allContracts.getString("deadline"));
                    contract.setExecLogin(allContracts.getString("execLogin"));
                    if (contract.getExecLog().equals(user.getLogin())) {
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
                contract.setDisc(rs.getString("disc"));
                contract.setDeadline(rs.getString("deadline"));
                contract.setExecLogin(rs.getString("execLogin"));
            }
        } catch (java.sql.SQLException e){}
        return contract;
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
