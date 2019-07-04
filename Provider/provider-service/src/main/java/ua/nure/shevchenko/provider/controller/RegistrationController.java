package ua.nure.shevchenko.provider.controller;

import org.apache.log4j.Logger;
import ua.nure.shevchenko.provider.constans.Messages;
import ua.nure.shevchenko.provider.constans.Page;
import ua.nure.shevchenko.provider.dao.mysqlDao.MySqlUserDao;
import ua.nure.shevchenko.provider.entity.User;
import ua.nure.shevchenko.provider.service.ClientService;
import ua.nure.shevchenko.provider.service.LoginService;
import ua.nure.shevchenko.provider.utils.PasswordHash;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@WebServlet("/reg")
public class RegistrationController extends HttpServlet {

    private static final Logger log = Logger.getLogger(LoginController.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Integer roleAccess = (Integer) session.getAttribute("roleAccess");

        if(!(roleAccess ==null) && roleAccess==1 ) {
            ServletContext context = req.getServletContext();
            LoginService loginService = (LoginService) context.getAttribute("loginService");
            List<User> users = loginService.getAllUser();
            log.trace(Messages.TRACE_FOUND_USERS + users);
            req.setAttribute("users", users);
            req.getRequestDispatcher(Page.REGISTRATION_PAGE).forward(req, resp);
        }else {

            req.getRequestDispatcher(Page.ERROR_PAGE).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext context = req.getServletContext();

        ClientService clientService = (ClientService) context.getAttribute("clientService");

        MySqlUserDao userDao = (MySqlUserDao) context.getAttribute("userDao");

        if((boolean)req.getAttribute("validate")) {

            String login = req.getParameter("login");
            String pass = req.getParameter("pass");
            String name = req.getParameter("name");
            String sur = req.getParameter("sur");
            String last = req.getParameter("last");
            String email = req.getParameter("email");
            String numb = req.getParameter("numb");
            String passwordHash = null;
            long score;


            try {
                passwordHash = PasswordHash.hash(pass);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }


            User newUser = new User(login, passwordHash, 2, name, sur, last, email, numb, false);

            log.trace(Messages.TRACE_CREATE_USER+newUser);

            score = clientService.createScore();

            log.trace(Messages.TRACE_CREATE_SCORE+score);

            newUser.setScoreId((int) score);

            req.removeAttribute("errorValid");

            userDao.addUser(newUser);

            doGet(req, resp);
        }
        else {
            req.setAttribute("messageErr",req.getAttribute("errorValid"));
            doGet(req,resp);
        }
    }
}
