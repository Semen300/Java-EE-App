package structure;

public class Manager extends User{

    public Manager(){}
    public Manager(String login, String password, String fio){
        super(login, password, fio);
    }

    @Override
    public String getLogin() {
        return super.getLogin();
    }

    @Override
    public void setLogin(String login) {
        super.setLogin(login);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public String getFio() {
        return super.getFio();
    }

    @Override
    public void setFio(String fio) {
        super.setFio(fio);
    }
}
