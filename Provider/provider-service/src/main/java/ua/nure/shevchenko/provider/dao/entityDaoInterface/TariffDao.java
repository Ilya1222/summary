package ua.nure.shevchenko.provider.dao.entityDaoInterface;

import ua.nure.shevchenko.provider.entity.Tariff;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface TariffDao {
    /**
     * Finds tariff in a database by its id.
     *
     * @param id
     *            of a tariff to find.
     * @return Tariff object.
     *
     */
     Tariff findTariffById(int id);
    /**
     * Finds tariff in a database and delete .
     *
     * @param tariff
     *

     */
     void deleteTariff(Tariff tariff);

    /**
     * Finds tariff in a database by its service.
     *
     * @param name
     *            of a user to find.
     * @return Tariff object.
     *
     */
     Tariff findTariffByService(String name) ;

    /**
     * Finds tariff in a database by its name.
     *
     * @param name
     *            of a user to find.
     * @return Tariff object.
     *
     */
     Tariff findTariffByName(String name);


    /**
     * Parses all Tariff fields from database to Tariff object.
     *
     * @param rs
     *            ResultSet of values from database.
     * @return Tariff object.
     * @throws SQLException
     */
     Tariff parseTariff(ResultSet rs) throws SQLException;

    /**
     * Inserts a Tariff into database.
     *
     * @param tariff
     *            to be inserted.
     * @return <b>true</b> if user has been successfully inserted in database or
     *         <b>false</b> if not.
     *
     */
     void addTariff(Tariff tariff) ;

    /**
     * Updates a tariff in database.
     *
     * @param tariff
     *            to update.
     * @return <b>true</b> if user has been successfully updated or <b>false</b>
     *         if not.
     *
     */
     boolean updateTariff(Tariff tariff);

    /**
     * Finds all tariffs in a database.
     *
     * @return List of Tariff objects.
     *
     */
     List<Tariff> getAllTariffs();
}
