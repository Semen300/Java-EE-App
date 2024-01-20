package servlets;

import services.ContractService;
import services.UserService;
import structure.Contract;
import structure.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        UserService userService=new UserService();
        ContractService contractService = new ContractService();
        User user=userService.getUserByLogin(req.getParameter("login"));
        req.setAttribute("login", user.getLogin());
        req.setAttribute("fio", user.getFio());
        List<Contract> contracts =  contractService.getContractsByUser(user);
        req.setAttribute("contracts", contracts);
        getServletContext().getRequestDispatcher("/pages/userInfo.jsp").forward(req, resp);;
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){

    }
}
