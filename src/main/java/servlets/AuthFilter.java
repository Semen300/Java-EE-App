package servlets;

import services.LoginService;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next) throws IOException, ServletException{
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String path = req.getRequestURI();
        Object userLoginAttribute = req.getSession().getAttribute("userLogin");

        if (path.startsWith(req.getContextPath() + "/login")) {
            next.doFilter(request, response);
            return;
        }

        if(userLoginAttribute!=null) {
            LoginService loginService = new LoginService();
            int role = loginService.getRoleByLogin(userLoginAttribute.toString());
            switch (role) {
                case 1 -> {
                    if (!path.startsWith(req.getContextPath() + "/worker")) {
                        req.getRequestDispatcher("/pages/authError.jsp").forward(req, resp);
                    }
                }
                case 2 -> {
                    if (!path.startsWith(req.getContextPath() + "/manager")) {
                        req.getRequestDispatcher("/pages/authError.jsp").forward(req, resp);
                    }
                }
                case 3 -> {
                    if (!path.startsWith(req.getContextPath() + "/customer")) {
                        req.getRequestDispatcher("/pages/authError.jsp").forward(req, resp);
                    }
                }
            }
        }
        else {
            req.getRequestDispatcher("/pages/authError.jsp").forward(req, resp);
        }
        next.doFilter(request, response);
    }
    @Override
    public void destroy(){}
}
