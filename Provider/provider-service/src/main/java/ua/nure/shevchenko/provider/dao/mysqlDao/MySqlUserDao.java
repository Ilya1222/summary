package ua.nure.shevchenko.provider.dao.mysqlDao;
import org.apache.log4j.Logger;
import ua.nure.shevchenko.provider.constans.DBCommands;
import ua.nure.shevchenko.provider.constans.Fields;
import ua.nure.shevchenko.provider.constans.Messages;
import ua.nure.shevchenko.provider.dao.entityDaoInterface.UserDao;
import ua.nure.shevchenko.provider.entity.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDao extends MySqlDao implements UserDao {

    private static final Logger LOG = Logger.getLogger(MySqlUserDao.class);


    @Override
    public User findUserByLogin(String login) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(DBCommands.SQL_FIND_USER_BY_LOGIN);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = parseUser(rs);
            }
        } catch (SQLException e) {
            rollBack(con);
             LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, e);
        }

        finally {
            close(con, pstmt, rs);
        }
        return user;
    }

    @Override
    public User findUserById(int id)  {
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        User user = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(DBCommands.SQL_FIND_USER_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = parseUser(rs);
            }
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, e);
        } finally {
            close(con, pstmt, rs);
        }
        return user;
    }

    @Override
    public boolean updateBlockStatus(User user)  {
        Connection con = null;
        boolean result = false;
        try {
            con = getConnection();
            updateUser(con,user);
            result = true;
        } catch (SQLException e) {
            rollBack(con);
        }finally {

            close(con);
        }
        return result;
    }




    private void updateUser(Connection con, User user) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(DBCommands.SQL_UPDATE_USER);
        int k = 1;
        pstmt.setBoolean(k++, user.isBlocked());
        pstmt.setLong(k,user.getId());
        pstmt.executeUpdate();
        pstmt.close();
    }

    @Override
    public boolean addUser(User user)  {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;



        try {
            connection = getConnection();
            pstmt = connection.prepareStatement(DBCommands.SQL_ADD_USER, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getLogin());
            pstmt.setString(2, user.getPassword());
            pstmt.setInt(3, user.getRoleId());
            pstmt.setString(4, user.getFirstName());
            pstmt.setString(5, user.getSurName());
            pstmt.setString(6, user.getLastName());
            pstmt.setString(7, user.getEmail());
            pstmt.setString(8, user.getPhoneNumber());
            pstmt.setLong(9,user.getScoreId());
            pstmt.setBoolean(10, user.isBlocked());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            close(pstmt);
            close(rs);
            close(connection);
        }

    }

    @Override
    public User parseUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(Fields.ENTITY_ID));
        user.setLogin(rs.getString(Fields.USER_LOGIN));
        user.setPassword(rs.getString(Fields.USER_PASSWORD));
        user.setRoleId(rs.getInt(Fields.USER_ROLE_ID));
        user.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
        user.setSurName(rs.getString(Fields.USER_SUR_NAME));
        user.setLastName(rs.getString(Fields.USER_LAST_NAME));
        user.setEmail(rs.getString(Fields.USER_EMAIL));
        user.setPhoneNumber(rs.getString(Fields.USER_PHONE));
        user.setScoreId(rs.getInt(Fields.USER_SCORE));
        user.setBlocked(rs.getBoolean(Fields.USER_IS_BLOCKED));
        return user;
    }

    @Override
    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(DBCommands.SQL_FIND_ALL_USERS);
            while (rs.next()) {
                User user = parseUser(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_USERS, e);
        } finally {
            close(con, stmt, rs);
        }

        return users;
    }
}
