package ua.nure.shevchenko.provider.service;

import ua.nure.shevchenko.provider.dao.mysqlDao.MySqlTariffConnectionInfoDao;
import ua.nure.shevchenko.provider.dao.mysqlDao.MySqlTariffDao;
import ua.nure.shevchenko.provider.dao.mysqlDao.MySqlUserDao;
import ua.nure.shevchenko.provider.entity.TariffConnectionInfo;
import ua.nure.shevchenko.provider.entity.Score;
import ua.nure.shevchenko.provider.entity.Tariff;
import ua.nure.shevchenko.provider.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class InfoTariffService {

    private MySqlTariffConnectionInfoDao dao = new MySqlTariffConnectionInfoDao();

    private   ClientService clientService = new ClientService();



    /**
     * Check connected user tariff.
     *
     * @param user
     *
     * @param tariff
     *
     * @return list Boolean object.
     *
     */
    public List<Boolean> checkConnectedTariff(User user, Tariff tariff) {

        List<Boolean> list = new ArrayList<>(2);

        list.add(false);

        list.add(false);

        List<TariffConnectionInfo> infos = dao.findInformationListByUser(user);

        for (TariffConnectionInfo info : infos) {


            if (info.getTariffId() == tariff.getId()) {
                 list.set(0,true);
            }
            if (info.getServiceId() == tariff.getServiceId()) {
                list.set(1,true);
            }
        }
        return list;
    }



    public void addNewTariff(User user, Tariff tariff) {
        dao.addInformationList(user, tariff);
    }

    /**
     * Find id info by user and tariff.
     *
     * @param tariff
     *
     * @param user
     *
     * @return id.
     *
     */
    public long findIdInfo(Tariff tariff, User user) {

        long id = 0;

        List<TariffConnectionInfo> infos = dao.findInformationListByUser(user);

        for (TariffConnectionInfo info : infos) {

            if (info.getServiceId() == tariff.getServiceId()) {
                id = info.getId();
            }
        }

        return id;
    }

    public void update(Tariff tariff, int id) {
        dao.updateInfo(tariff, id);
    }




    /**
     * Find all info about connected user tariffs  by user.
     *
     * @param user
     *
     * @return list of TariffConnectionInfo object.
     *
     */
    public List<TariffConnectionInfo> findAllUserTariffs(User user) {

        List<TariffConnectionInfo> list = dao.findInformationListByUser(user);

        for(TariffConnectionInfo infos : list){
            parseUserInInfo(infos);
        }

        return list;

    }


    /**
     * Find all info tariff users.
     *
     * @return list of TariffConnectionInfo object.
     *
     */
    public List<TariffConnectionInfo> findAll(){

        List<TariffConnectionInfo> list = dao.getAll();

        for( TariffConnectionInfo infos : list ){

             parseUserInInfo(infos);

        }

        return list;
    }


    /**
     * Change debt tariff status and user block status.
     *
     * @param inf
     *
     * @return TariffConnectionInfo object.
     *
     */
    private void parseUserInInfo(TariffConnectionInfo inf){

        LoginService service = new LoginService();

        ClientService clientService = new ClientService();

        Date localDate = new Date();

        long id = inf.getUserId();

        User current = service.getUser(id);

        inf.setUser(current);

        if (localDate.after(inf.getPeriod())) {
             dao.updateDebt(true, inf.getId());
             inf.setDebt(true);
             clientService.changeStatus(current,true);
        }

    }


    /**
     * Find all debt about info tariff users  with changed block and debt status.
     *
     * @return list of TariffConnectionInfo object.
     *
     */
    public List<TariffConnectionInfo> findAllDebt(){

        List<TariffConnectionInfo> list = dao.getAllDebts();

        for( TariffConnectionInfo infos : list ){

             parseUserInInfo(infos);

        }

        return list;
    }


    /**
     * Checks if the users can  pay by debt tariff.
     *
     * @param infos
     *
     */
    public void autoReUpdate( List< TariffConnectionInfo> infos) {

        for (TariffConnectionInfo inf : infos) {


            MySqlUserDao userDao = new MySqlUserDao();

            MySqlTariffDao tariffDao = new MySqlTariffDao();

            User currentUser = userDao.findUserById((int) inf.getUserId());

            Score currentScore = clientService.getScoreById(currentUser.getScoreId());

            if (withdrawal(currentScore, inf.getMoney())) {
                Tariff tariff = tariffDao.findTariffById((int) inf.getTariffId());
                inf.setDebt(false);
                inf.setPeriod(new Date());
                currentUser.setBlocked(false);
                dao.updateInfo(tariff, (int) inf.getId());
                dao.updateDebt(false, inf.getId());
                userDao.updateBlockStatus(currentUser);
            } else {
                currentUser.setBlocked(true);
                userDao.updateBlockStatus(currentUser);
            }
        }
    }



    /**
     * Checks if there are enough funds in the ser balance to pay the tariff.
     *
     * @param score
     *
     * @param price
     *
     * @return true if can or false if cannot.
     *
     */
    private boolean withdrawal(Score score, double price) {

        boolean result = false;

        double res = score.getBalance() - price;

        if (res >= 0) {
            score.setBalance(res);
            clientService.updateScore(score);
            result = true;
        }
        return result;
    }


    /**
     * Find debts tariff in  list of info tariff users .
     *
     * @param list
     *
     * @return list of TariffConnectionInfo object.
     *
     */
    public  List<TariffConnectionInfo> findDebtTariff (List<TariffConnectionInfo> list){

        List<TariffConnectionInfo> users = new ArrayList();

        for (TariffConnectionInfo info : list ){
           if (info.isDebt()){
               users.add(info);
           }
        }
        return users;
    }

    public List<TariffConnectionInfo> findDebtTariff(User user){
        List<TariffConnectionInfo> users = dao.findInformationListByUserDebt(user);
        return users;
    }

    public void deleteTariffInfo(Tariff tariff){

        List<TariffConnectionInfo> list = dao.findInformationListByTariff(tariff);

        for(TariffConnectionInfo info : list){
            dao.deleteByTariff(info);
        }

    }



}
