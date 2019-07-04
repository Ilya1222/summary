package ua.nure.shevchenko.provider.dao.entityDaoInterface;

import ua.nure.shevchenko.provider.entity.TariffConnectionInfo;
import ua.nure.shevchenko.provider.entity.Tariff;
import ua.nure.shevchenko.provider.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface InfoTariffUsersDao {

    /**
     * Inserts a Info about Tariffs and User into database.
     *
     * @param user
     *
     * @param tariff
     *
     * @return <b>true</b> if info has been successfully inserted in database or
     *         <b>false</b> if not.
     *
     */

     boolean addInformationList(User user , Tariff tariff);


    /**
     * Finds all information about tariffs user in a database by tariff.
     *
     * @param tariff
     *
     * @return list of TariffConnectionInfo object.
     *
     */
     List<TariffConnectionInfo> findInformationListByTariff(Tariff tariff);


    /**
     * Finds all information about tariffs user in a database by date.
     *
     * @param date
     *
     * @return list of TariffConnectionInfo object.
     *
     */
     List<TariffConnectionInfo> findInformationListByPeriod(LocalDate date);


    /**
     * Finds all information about tariffs user in a database by user.
     *
     * @param user
     *
     * @return list of TariffConnectionInfo object.
     *
     */
     List <TariffConnectionInfo> findInformationListByUser(User user);


    /**
     * Finds all information about debt tariffs user in a database by tariff.
     *
     * @param user
     *
     * @return list of TariffConnectionInfo object.
     *
     */
     List <TariffConnectionInfo> findInformationListByUserDebt(User user);

    /**
     * Updates a info in database.
     *
     * @param tariff
     *
     * @param id
     *
     * @return <b>true</b> if user has been successfully updated or <b>false</b>
     *         if not.
     *
     */

      boolean updateInfo( Tariff tariff , int id);


    /**
     * Updates a info about debt in database.
     *
     * @param status
     *
     * @param id
     *
     * @return <b>true</b> if user has been successfully updated or <b>false</b>
     *         if not.
     *
     */
      boolean updateDebt( boolean status , long id);


    /**
     * Parses all Info  fields from database to InfoTariffsUser object.
     *
     * @param rs
     *            ResultSet of values from database.
     * @return TariffConnectionInfo User object.
     * @throws SQLException
     */
     TariffConnectionInfo parseUserTariffs(ResultSet rs) throws SQLException;


    /**
     * Finds all info about debts from tariffs user in a database.
     *
     * @return List of InfoTariffsUser objects.
     */
     List<TariffConnectionInfo> getAllDebts();


    /**
     * Finds all info about tariffs user in a database.
     *
     * @return List of InfoTariffsUser objects.
     */
     List<TariffConnectionInfo> getAll();

    /**
     * Finds info tariff users in a database and delete .
     *
     * @param info
     *
     *
     */
     void deleteByTariff(TariffConnectionInfo info);
}
