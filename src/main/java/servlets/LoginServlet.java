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
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("errorText", "");
        req.getRequestDispatcher("/pages/login.jsp").forward(req, res);
        res.setContentType("text/html");
        super.doGet(req, res);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getServletContext();
        User user = new User(req);
        LoginService loginService = new LoginService();
        switch (loginService.auth(user.getLogin(), user.getPassword())) {
            case 2: {
                loginService.logout(user.getLogin());
                String session = loginService.createSession(user.getLogin());
                req.getSession().setAttribute("session", session);
                res.addHeader("session",session);
                res.sendRedirect(req.getContextPath() + "/managerLk");
                break;
            }
            case 1: {
                loginService.logout(user.getLogin());
                String session = loginService.createSession(user.getLogin());
                req.getSession().setAttribute("session", session);
                res.addHeader("session",session);
                res.sendRedirect(req.getContextPath() + "/userLk");
                break;
            }
            default: {
                req.setAttribute("errorText", "Неверный пароль");
                res.setContentType("text/html");
                req.getRequestDispatcher("/pages/login.jsp").forward(req, res);
            }
        }
    }
}
