package structure;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class User {
        private String login;
        private String password;
        private String fio;
        private int role;

        public User (){}
        public User (String login, String password, String fio, int role){
                this.login = login;
                this.password = password;
                this.fio = fio;
                this.role=role;
        }

        public User(HttpServletRequest req){
                this.login=req.getParameter("login");
                this.password=req.getParameter("password");
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

        public int getRole(){return role;}
        public void setRole(int newRole){role=newRole;}
}
