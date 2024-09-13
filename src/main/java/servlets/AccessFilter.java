package servlets;

import services.ContractService;
import structure.Contract;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig){}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if(req.getParameter("id")!=null){
            int conID = Integer.parseInt(req.getParameter("id"));
            ContractService contractService = new ContractService();
            Contract contract = contractService.getContractById(conID);
            if(!contract.getExecLogin().equals(req.getSession().getAttribute("userLogin"))){
                req.getRequestDispatcher("pages/worker/accessError.jsp").forward(req, resp);
            }
        }
        next.doFilter(request, response);
    }

    @Override
    public void destroy(){}
}
