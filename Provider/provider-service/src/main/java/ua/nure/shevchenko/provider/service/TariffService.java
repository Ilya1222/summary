package ua.nure.shevchenko.provider.service;

import ua.nure.shevchenko.provider.dao.mysqlDao.MySqlTariffConnectionInfoDao;
import ua.nure.shevchenko.provider.dao.mysqlDao.MySqlServiceDao;
import ua.nure.shevchenko.provider.dao.mysqlDao.MySqlTariffDao;
import ua.nure.shevchenko.provider.entity.TariffConnectionInfo;
import ua.nure.shevchenko.provider.entity.Service;
import ua.nure.shevchenko.provider.entity.Tariff;
import ua.nure.shevchenko.provider.entity.User;

import java.util.List;

public class TariffService {


    private  MySqlServiceDao serviceDao = new MySqlServiceDao();


    public List<Service> getAllServices() {

        serviceDao = new MySqlServiceDao();

        List<Service> cur = serviceDao.getAllServices();

        return cur;
    }



    public Service findService(String name) {


        Service service = serviceDao.findServiceByName(name);

        return service;

    }


    public List<TariffConnectionInfo> getAllInfoByUser(User user) {

        MySqlTariffConnectionInfoDao dao = new MySqlTariffConnectionInfoDao();

        List<TariffConnectionInfo> infs = dao.findInformationListByUser(user);

        return infs;
    }

    public Service findService(int id) {
        Service service = serviceDao.findServiceById(id);
        return service;
    }

    public boolean isExist(String name){

        boolean result = false;

        MySqlTariffDao dao = new MySqlTariffDao();

        Tariff tariff =  dao.findTariffByName(name);

        if(tariff==null){
            result = true;
        }
         return result;
    }

}
