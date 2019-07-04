package ua.nure.shevchenko.provider.controller;

import org.apache.log4j.Logger;
import ua.nure.shevchenko.provider.constans.Messages;
import ua.nure.shevchenko.provider.dao.mysqlDao.MySqlTariffDao;
import ua.nure.shevchenko.provider.entity.Service;
import ua.nure.shevchenko.provider.entity.Tariff;
import ua.nure.shevchenko.provider.service.TariffService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;


@WebServlet("/dwl")
public class DownloadController extends HttpServlet {

    private static final Logger log = Logger.getLogger(LoginController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext context = req.getServletContext();

        MySqlTariffDao tariffDao = (MySqlTariffDao) context.getAttribute("tariffDao");

        TariffService tariffService = (TariffService) context.getAttribute("tariffService");

        int id = Integer.parseInt(req.getParameter("id"));

        Tariff tariff = tariffDao.findTariffById(id);

        log.trace(Messages.TRACE_FOUND_TARIFF+tariff);

        Service service = tariffService.findService(tariff.getServiceId());

        log.trace(Messages.TRACE_FOUND_SERVICE+service);

        String fileName = "Tariff "+tariff.getName()+".pdf";

        String tariffName = tariff.getName();

        String serviceName = service.getName();

        String tariffSpecification = tariff.getSpecification();

        String tariffPrice = String.valueOf(tariff.getPrice());

        resp.setHeader("Content-Disposition", "attachment;filename="+fileName);

        resp.setContentType("application/pdf");

        try{
            Document document = new Document();
            PdfWriter.getInstance(document, resp.getOutputStream());
            document.open();
            Font redFont = FontFactory.getFont(FontFactory.COURIER, 16, Font.BOLD, new CMYKColor(0, 255, 0, 0));
            Font blackFont = FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, new CMYKColor(0, 0, 0, 255));

            document.add(new Paragraph("Tariff name: ",redFont));
            document.add(new Paragraph(tariffName,blackFont));
            document.add(new Paragraph("Service: ",redFont));
            document.add(new Paragraph(serviceName,blackFont));
            document.add(new Paragraph("Tariff specification ",redFont));
            document.add(new Paragraph(tariffSpecification,blackFont));
            document.add(new Paragraph("Price: ",redFont));
            document.add(new Paragraph(tariffPrice,blackFont));
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

}

