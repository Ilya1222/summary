package ua.nure.shevchenko.provider.dao.entityDaoInterface;

import ua.nure.shevchenko.provider.entity.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ServiceDao {
    /**
     * Finds service in a database by its id.
     *
     * @param id
     *            of a user to find.
     * @return Service object.
     *
     */
     Service findServiceById(int id);

    /**
     * Parses all Services fields from database to Score object.
     *
     * @param rs
     *            ResultSet of values from database.
     * @return Service object.
     * @throws SQLException
     */
     Service parseService(ResultSet rs) throws SQLException;

    /**
     * Finds service in a database by its name.
     *
     * @param name
     *            of a user to find.
     * @return Service object.
     *
     */
    Service findServiceByName(String name) ;
    /**
     * Finds all service in a database.
     *
     * @return List of Service objects.
     *
     */
     List<Service> getAllServices() ;

}
