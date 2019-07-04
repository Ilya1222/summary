package ua.nure.shevchenko.provider.dao.mysqlDao;

import org.apache.log4j.Logger;
import ua.nure.shevchenko.provider.constans.DBCommands;
import ua.nure.shevchenko.provider.constans.Fields;
import ua.nure.shevchenko.provider.constans.Messages;
import ua.nure.shevchenko.provider.dao.entityDaoInterface.InfoTariffUsersDao;
import ua.nure.shevchenko.provider.entity.TariffConnectionInfo;
import ua.nure.shevchenko.provider.entity.Tariff;
import ua.nure.shevchenko.provider.entity.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MySqlTariffConnectionInfoDao extends MySqlDao implements InfoTariffUsersDao {

    private static final Logger LOG = Logger.getLogger(MySqlTariffDao.class);

    @Override
    public boolean addInformationList(User user, Tariff tariff) {

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        boolean res = false;

        try {
            connection = getConnection();
            pstmt = connection.prepareStatement(DBCommands.SQL_ADD_INFORMATION_LIST, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, user.getId());
            pstmt.setLong(2, tariff.getId());
            pstmt.setString(3, tariff.getName());
            pstmt.setInt(4, tariff.getServiceId());
            pstmt.setDate(5, Date.valueOf(LocalDate.now().plusMonths(1)));
            pstmt.setDouble(6, tariff.getPrice());
            pstmt.setBoolean(7, false);
            pstmt.executeUpdate();

            res = true;

        } catch (SQLException ex) {
           LOG.error(Messages.ERR_CANNOT_ADD_CONNECTION_INFO);
        }
        close(pstmt);
        close(rs);
        close(connection);

        return res;
    }

    @Override
    public List<TariffConnectionInfo> findInformationListByTariff(Tariff tariff) {
        List<TariffConnectionInfo> infs = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(DBCommands.SQL_FIND_INFORMATION_LIST_BY_TARIFF);
            pstmt.setLong(1, tariff.getId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                TariffConnectionInfo info = parseUserTariffs(rs);
                infs.add(info);
            }
        } catch (SQLException e) {
             LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION_INFO);
            rollBack(con);
        } finally {
            close(con, pstmt, rs);
        }
        return infs;
    }

    @Override
    public List<TariffConnectionInfo> findInformationListByPeriod(LocalDate date) {

        List<TariffConnectionInfo> infs = new ArrayList();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            stmt = con.prepareStatement(DBCommands.SQL_FIND_INFORMATION_LIST_BY_PERIOD);
            stmt.setDate(1, Date.valueOf(date.toString()));
            rs = stmt.executeQuery();
            while (rs.next()) {
                TariffConnectionInfo inf = parseUserTariffs(rs);
                infs.add(inf);
            }
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION_INFO);
        } finally {
            close(con, stmt, rs);
        }
        return infs;
    }


    @Override
    public List<TariffConnectionInfo> findInformationListByUser(User user) {

        List<TariffConnectionInfo> infs = new ArrayList();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            stmt = con.prepareStatement(DBCommands.SQL_FIND_INFORMATION_LIST_BY_USER);
            stmt.setLong(1, user.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                TariffConnectionInfo inf = parseUserTariffs(rs);
                infs.add(inf);
            }
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION_INFO);
        } finally {
            close(con, stmt, rs);
        }
        return infs;
    }

    @Override
    public List<TariffConnectionInfo> findInformationListByUserDebt(User user) {

        List<TariffConnectionInfo> infs = new ArrayList();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            stmt = con.prepareStatement(DBCommands.SQL_FIND_INFORMATION_LIST_BY_USER_DEBT);
            stmt.setLong(1, user.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                TariffConnectionInfo inf = parseUserTariffs(rs);
                infs.add(inf);
            }
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION_INFO);
        } finally {
            close(con, stmt, rs);
        }
        return infs;
    }


    @Override
    public boolean updateInfo(Tariff tariff, int id) {
        Connection con = null;
        boolean result = false;
        try {
            con = getConnection();
            updateInfoTariffUsers(con, tariff, id);
            result = true;
        } catch (SQLException e) {
            rollBack(con);
        } finally {

            close(con);
        }
        return result;
    }


    private void updateInfoTariffUsers(Connection con, Tariff tariff, int id) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(DBCommands.SQL_UPDATE_INFO);

        LocalDate localDate = LocalDate.now().plusMonths(1);

        int k = 1;
        pstmt.setLong(k++, tariff.getId());
        pstmt.setString(k++, tariff.getName());
        pstmt.setDate(k++, Date.valueOf(localDate));
        pstmt.setDouble(k++, tariff.getPrice());
        pstmt.setLong(k, id);
        pstmt.executeUpdate();
        pstmt.close();
    }

    @Override
    public boolean updateDebt(boolean status, long id) {
        Connection con = null;
        boolean result = false;
        try {
            con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(DBCommands.SQL_UPDATE_INFO_DEBT);
            int k = 1;
            pstmt.setBoolean(k++, status);
            pstmt.setLong(k, id);
            pstmt.executeUpdate();
            pstmt.close();
            result = true;
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_UPDATE_CONNECTION_INFO);
            rollBack(con);
        } finally {

            close(con);
        }
        return result;
    }


    @Override
    public TariffConnectionInfo parseUserTariffs(ResultSet rs) throws SQLException {
        TariffConnectionInfo tariffConnectionInfo = new TariffConnectionInfo();
        tariffConnectionInfo.setId(rs.getInt(Fields.ENTITY_ID));
        tariffConnectionInfo.setUserId(rs.getInt(Fields.USER_ID));
        tariffConnectionInfo.setTariffId(rs.getInt(Fields.TARIFF_ID));
        tariffConnectionInfo.setTariffName(rs.getString(Fields.TARIFF_NAME));
        tariffConnectionInfo.setServiceId(rs.getInt(Fields.SERVICE_ID));
        tariffConnectionInfo.setPeriod(rs.getDate(Fields.PERIOD));
        tariffConnectionInfo.setMoney(rs.getDouble(Fields.MONEY));
        tariffConnectionInfo.setDebt(rs.getBoolean(Fields.IS_DEBT));
        return tariffConnectionInfo;
    }


    @Override
    public List<TariffConnectionInfo> getAllDebts() {
        List<TariffConnectionInfo> infs = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(DBCommands.SQL_FIND_INFORMATION_LIST_BY_DEBT);
            while (rs.next()) {
                TariffConnectionInfo inf = parseUserTariffs(rs);
                infs.add(inf);
            }
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION_INFO);
        } finally {
            close(con, stmt, rs);
        }
        return infs;
    }

    @Override
    public List<TariffConnectionInfo> getAll() {
        List<TariffConnectionInfo> infs = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(DBCommands.SQL_FIND_INFORMATION_LIST_BY_ALL);
            while (rs.next()) {
                TariffConnectionInfo inf = parseUserTariffs(rs);
                infs.add(inf);
            }
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION_INFO);
        } finally {
            close(con, stmt, rs);
        }
        return infs;
    }

    @Override
    public void deleteByTariff(TariffConnectionInfo info) {

        PreparedStatement statement = null;
        Connection con = getConnection();
        try {

            statement = con.prepareStatement(DBCommands.SQL_DELETE_INFO);
            statement.setLong(1, info.getId());
            statement.executeUpdate();
        } catch (SQLException e) {

            LOG.error(Messages.ERR_CANNOT_DELETE_CONNECTION_INFO);

        } finally {
            close(statement);
            close(con);
        }

    }
}
