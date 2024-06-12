package structure;

import java.sql.Date;

public class ContractM extends Contract{
    private String managerLogin;

    public ContractM(int id, String name, Date deadline, String execLogin, String consLogin, String managerLogin){
        super(id,name,deadline,execLogin,consLogin);
        this.managerLogin=managerLogin;
    }

    public String getManagerLogin() {
        return managerLogin;
    }
    public void setManagerLogin(String managerLogin) {
        this.managerLogin = managerLogin;
    }
}
