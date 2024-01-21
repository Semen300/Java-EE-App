package servlets;

import services.ContractService;
import services.UserService;
import structure.Contract;
import structure.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.servlet.ServletException;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    private User user;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String action = req.getParameter("action");
        UserService userService=new UserService();
        ContractService contractService = new ContractService();
        switch (action == null ? "info" : action) {
            case ("update") -> {
                req.setAttribute("login", user.getLogin());
                req.setAttribute("fio", user.getFio());
                List<Contract> contracts = contractService.getContractsByUser(user);
                req.setAttribute("contracts", contracts);
                getServletContext().getRequestDispatcher("/pages/userUpdate.jsp").forward(req, resp);
            }
            default -> {
                user = userService.getUserByLogin(req.getParameter("login"));
                req.setAttribute("login", user.getLogin());
                req.setAttribute("fio", user.getFio());
                List<Contract> contracts = contractService.getContractsByUser(user);
                req.setAttribute("contracts", contracts);
                getServletContext().getRequestDispatcher("/pages/userInfo.jsp").forward(req, resp);
            }
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setCharacterEncoding("UTF-8");
        UserService userService=new UserService();
        ContractService contractService = new ContractService();
        user.setLogin(req.getParameter("newLogin"));
        user.setPassword(req.getParameter("newPassword"));
        user.setFio(req.getParameter("newFio"));
        userService.updateUser(user);
        req.setAttribute("login", user.getLogin());
        req.setAttribute("fio", user.getFio());
        List<Contract> contracts = contractService.getContractsByUser(user);
        req.setAttribute("contracts", contracts);
        getServletContext().getRequestDispatcher("/pages/userInfo.jsp").forward(req, resp);
    }
}
