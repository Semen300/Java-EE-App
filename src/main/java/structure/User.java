package structure;

import javax.servlet.http.HttpServletRequest;

public class User {
    private String login;
    private String password;
    private String fio;

    public User (){}
    public User (String login, String password, String fio){
        this.login = login;
        this.password = password;
        this.fio = fio;
    }
    public User(HttpServletRequest req){
        login=req.getParameter("login");
        password=req.getParameter("password");
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFio() {
        return fio;
    }
    public void setFio(String fio) {
        this.fio = fio;
    }
}
