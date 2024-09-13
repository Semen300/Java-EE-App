package structure;

import java.sql.Date;

public class ContractM extends Contract{
    private String managerLogin;

    public ContractM(int id, String name, Date deadline, String execLogin, String consLogin, String managerLogin, float percentOfCompletion, int status){
        super(id,name,deadline,execLogin,consLogin,percentOfCompletion, status);
        this.managerLogin=managerLogin;
    }

    public String getManagerLogin() {
        return managerLogin;
    }
    public void setManagerLogin(String managerLogin) {
        this.managerLogin = managerLogin;
    }
}
