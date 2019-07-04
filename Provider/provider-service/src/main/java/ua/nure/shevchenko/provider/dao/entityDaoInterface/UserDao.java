package ua.nure.shevchenko.provider.dao.entityDaoInterface;

import ua.nure.shevchenko.provider.entity.User;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    /**
     * Finds user in a database by its id.
     *
     * @param id
     *            of a user to find.
     * @return User object.
     *
     */
     User findUserById(int id);

    /**
     * Finds user in a database by its login.
     *
     * @param login
     *            of a user to find.
     * @return User object.
     *
     */
     User findUserByLogin(String login);

    /**
     * Parses all User fields from database to User object.
     *
     * @param rs
     *            ResultSet of values from database.
     * @return User object.
     * @throws SQLException
     */
     User parseUser(ResultSet rs) throws SQLException;

    /**
     * Inserts a User into database.
     *
     * @param user
     *            to be inserted.
     * @return <b>true</b> if user has been successfully inserted in database or
     *         <b>false</b> if not.
     *
     */
     boolean addUser(User user);

    /**
     * Updates a user in database.
     *
     * @param user
     *            to update.
     * @return <b>true</b> if user has been successfully updated or <b>false</b>
     *         if not.
     *
     */
     boolean updateBlockStatus(User user);



    /**
     * Finds all users in a database.
     *
     * @return List of User objects.
     *
     */
     List<User> getAllUsers() ;
}
