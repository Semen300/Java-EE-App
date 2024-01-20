package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import services.LoginService;
import structure.User;

public class LoginServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("errorText", "");
        req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
        resp.setContentType("text/html");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext();
        User user = new User(req);
        LoginService loginService = new LoginService();
        int role =loginService.auth(user.getLogin(), user.getPassword());
        switch (role) {
            case 2: {
                loginService.logout(user.getLogin());
                String session = loginService.createSession(user.getLogin());
                req.getSession().setAttribute("session", session);
                req.getSession().setAttribute("user_login", user.getLogin());
                req.getSession().setAttribute("role", role);
                resp.addHeader("session",session);
                resp.sendRedirect(req.getContextPath() + "/managerLk");
                break;
            }
            case 1: {
                loginService.logout(user.getLogin());
                String session = loginService.createSession(user.getLogin());
                req.getSession().setAttribute("session", session);
                req.getSession().setAttribute("user_login", user.getLogin());
                req.getSession().setAttribute("role", role);
                resp.addHeader("session",session);
                resp.sendRedirect(req.getContextPath() + "/userInfo?login="+user.getLogin());
                break;
            }
            default: {
                req.setAttribute("errorText", "Неверный пароль");
                resp.setContentType("text/html");
                req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
            }
        }
    }
}
