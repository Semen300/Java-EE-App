package structure;

public class Customer extends User{
    private String number;
    private String email;

    public Customer(){}
    public Customer(String login, String password, String fio, String number, String email){
        super(login, password, fio);
        this.number=number;
        this.email=email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
