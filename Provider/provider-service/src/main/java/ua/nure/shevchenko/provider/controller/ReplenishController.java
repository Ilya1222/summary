package ua.nure.shevchenko.provider.controller;

import org.apache.log4j.Logger;
import ua.nure.shevchenko.provider.constans.Messages;
import ua.nure.shevchenko.provider.constans.Page;
import ua.nure.shevchenko.provider.entity.Score;
import ua.nure.shevchenko.provider.entity.User;
import ua.nure.shevchenko.provider.service.ClientService;
import ua.nure.shevchenko.provider.utils.Validator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/repl")
public class ReplenishController extends HttpServlet {

    private static final Logger log = Logger.getLogger(LoginController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Integer roleAccess = (Integer) session.getAttribute("roleAccess");

        if(!(roleAccess ==null) && roleAccess==2 ) {
            req.getRequestDispatcher(Page.REPLENISH_PAGE).forward(req, resp);
        } else{
            req.getRequestDispatcher(Page.ERROR_PAGE).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext context = req.getServletContext();

        ClientService clientService = (ClientService) context.getAttribute("clientService");

        HttpSession session = req.getSession();

        int scoreId;

        String amo = req.getParameter("currentAmount");

        if (Validator.validateTariffParameters(amo) && !amo.isEmpty()) {


            Double amount = Double.parseDouble(amo);

            log.trace(Messages.TRACE_REFILL + amount);

            User user = (User) session.getAttribute("user");

            log.trace(Messages.TRACE_SESSION_ATTRIBUTE_USER+user);

            scoreId = user.getScoreId();

            Score score = clientService.getScoreById(scoreId);

            log.trace(Messages.TRACE_FOUND_SCORE+score);

            Score newScore = clientService.doReplenish(score, amount);

            clientService.updateScore(newScore);

            log.trace(Messages.TRACE_SCORE_UPDATE+newScore);

            req.getRequestDispatcher("menu").forward(req, resp);

        } else {
            doGet(req, resp);
        }
    }


}
