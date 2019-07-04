package ua.nure.shevchenko.provider.controller;

import org.apache.log4j.Logger;
import ua.nure.shevchenko.provider.constans.Messages;
import ua.nure.shevchenko.provider.entity.Score;
import ua.nure.shevchenko.provider.entity.Tariff;
import ua.nure.shevchenko.provider.entity.User;
import ua.nure.shevchenko.provider.service.ClientService;
import ua.nure.shevchenko.provider.service.InfoTariffService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebServlet("/confirm")
public class TariffConfirmController extends HttpServlet {

    private static final Logger log = Logger.getLogger(LoginController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        ServletContext context = req.getServletContext();

        ClientService clientService = (ClientService) context.getAttribute("clientService");

        InfoTariffService infoService = (InfoTariffService) context.getAttribute("infoTariffService");

        User user = (User) session.getAttribute("user");

        log.trace(Messages.TRACE_FOUND_USER+user);

        Tariff tariff = (Tariff) session.getAttribute("currentTariff");

        log.trace(Messages.TRACE_FOUND_TARIFF+tariff);

        Score score = (Score) session.getAttribute("score");

        log.trace(Messages.TRACE_FOUND_SCORE+score);

        if(nonNull(session.getAttribute("update"))){

            int id  = (int) infoService.findIdInfo(tariff,user);
            infoService.update(tariff,id);
            log.trace(Messages.TRACE_INFO_UPDATE+tariff+"->"+user);

        } else {
            infoService.addNewTariff(user, tariff);
            log.trace(Messages.TRACE_ADD_NEW_INFO+tariff+"->"+user);
        }

        double balance = score.getBalance()-tariff.getPrice();

        score.setBalance(balance);

        clientService.updateScore(score);

        log.trace(Messages.ERR_CANNOT_UPDATE_SCORE+score);


        req.getRequestDispatcher("menu").forward(req,resp);

    }
}
