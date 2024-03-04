package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import services.LoginService;
import structure.Worker;

public class LoginServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if((req.getAttribute("operation")!=null)&&("login".equals(req.getAttribute("operation")))) {
            req.setAttribute("errorText", "");
            req.getRequestDispatcher("/pages/login/login.jsp").forward(req, resp);
            resp.setContentType("text/html");
        } // Переход на страницу входа при operation=login
        else if((req.getAttribute("operation")!=null)&&("auth".equals(req.getAttribute("operation")))){
            req.setAttribute("errorText", "");
            req.getRequestDispatcher("/pages/login/new.jsp").forward(req, resp);
            resp.setContentType("text/html");
        } //Переход на страницу регистрации нового пользователя при operation=auth
        else {
            req.setAttribute("errorText", "");
            req.setAttribute("operation", "login");
            req.getRequestDispatcher("/pages/login/login.jsp").forward(req, resp);
        } //По усмолчанию переходим на страницу входа
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Worker worker = new Worker(req);
        LoginService loginService = new LoginService();
        int role = loginService.auth(worker.getLogin(), worker.getPassword());
        switch (role){
            case 3:{
                loginService.logout(worker.getLogin());
                String session = loginService.createSession(worker.getLogin());
                resp.addHeader("session",session);
                req.getSession().setAttribute("name", session);
                resp.sendRedirect(req.getContextPath() + "/consumer");
                break;
            }
            case 2: {
                loginService.logout(worker.getLogin());
                String session = loginService.createSession(worker.getLogin());
                resp.addHeader("session",session);
                req.getSession().setAttribute("name", session);
                resp.sendRedirect(req.getContextPath() + "/manager");
                break;
            }
            case 1: {
                loginService.logout(worker.getLogin());
                String session = loginService.createSession(worker.getLogin());
                resp.addHeader("session",session);
                req.getSession().setAttribute("name", session);
                resp.sendRedirect(req.getContextPath() + "/worker");
                break;
            }
            default: {
                req.setAttribute("errorText", "Неверный пароль");
                resp.setContentType("text/html");
                req.getRequestDispatcher("/pages/login/login.jsp").forward(req, resp);
            }
        }
    }
}
