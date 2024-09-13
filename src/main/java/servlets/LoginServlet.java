package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import services.LoginService;
import services.UserService;
import structure.User;

public class LoginServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        if((req.getParameter("action")!=null)&&(req.getParameter("action").equals("login"))) {
            req.getRequestDispatcher("/pages/login/login.jsp").forward(req, resp);
            resp.setContentType("text/html");
        }
        else if((req.getParameter("action")!=null)&&(req.getParameter("action").equals("auth"))){
            req.getRequestDispatcher("/pages/login/auth.jsp").forward(req, resp);
            resp.setContentType("text/html");
        }
        else {
            req.setAttribute("errorText", "");
            req.getRequestDispatcher("/pages/login/login.jsp").forward(req, resp);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("action").equals("login")) {
            User user = new User(req);
            LoginService loginService = new LoginService();
            int role = loginService.auth(user.getLogin(), user.getPassword());
            switch (role) {
                case 3: {
                    HttpSession session = req.getSession();
                    session.setAttribute("userLogin", user.getLogin());
                    resp.sendRedirect(req.getContextPath() + "/customer?action=show");
                    break;
                }
                case 2: {
                    HttpSession session = req.getSession();
                    session.setAttribute("userLogin", user.getLogin());
                    resp.sendRedirect(req.getContextPath() + "/manager");
                    break;
                }
                case 1: {
                    HttpSession session = req.getSession();
                    session.setAttribute("userLogin", user.getLogin());
                    resp.sendRedirect(req.getContextPath() + "/worker");
                    break;
                }
                default: {
                    req.setAttribute("errorText", "Неверный пароль или пользователь не существует");
                    req.setAttribute("action", "login");
                    resp.setContentType("text/html");
                    req.getRequestDispatcher("/pages/login/login.jsp").forward(req, resp);
                }
            }
        }
        else if(req.getParameter("action").equals("auth")){
            UserService userService = new UserService();
            if(userService.in_system(req.getParameter("login"))){
                req.setAttribute("errorText", "Пользователь с таким логином уже есть в системе");
                req.getRequestDispatcher("/pages/login/auth.jsp").forward(req, resp);
            }//Вывод ошибки о существовании пользователя на странице авторизации
            else if(req.getParameter("login").equals("")) {
                req.setAttribute("errorText", "Поле 'логин' не должно быть пустым");
                req.getRequestDispatcher("/pages/login/auth.jsp").forward(req, resp);
            }//Вывод ошибки о пустом поле логина на странице авторизации
            else if(req.getParameter("password").equals("")){
                req.setAttribute("errorText", "Поле 'пароль' не должно быть пустым");
                req.getRequestDispatcher("/pages/login/auth.jsp").forward(req, resp);
            }//Вывод ошибки о пустом поле пароля на странице авторизации
            else{
                if(
                    userService.createCustomer(
                            req.getParameter("login"),
                            req.getParameter("password"),
                            req.getParameter("fio"),
                            req.getParameter("number"),
                            req.getParameter("email")
                    )
                ) {
                    req.setAttribute("errorText", "Пользователь успешно создан!");
                } else
                    req.setAttribute("errorText", "Произошла непредвиденная ошибка");
                req.getRequestDispatcher("/pages/login/auth.jsp").forward(req, resp);
            }
        }
        else resp.sendRedirect(req.getContextPath()+"/login?action=login");
    }
}
