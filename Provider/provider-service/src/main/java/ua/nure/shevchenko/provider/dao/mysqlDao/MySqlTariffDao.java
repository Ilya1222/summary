package ua.nure.shevchenko.provider.dao.mysqlDao;

import org.apache.log4j.Logger;
import ua.nure.shevchenko.provider.constans.DBCommands;
import ua.nure.shevchenko.provider.constans.Fields;
import ua.nure.shevchenko.provider.constans.Messages;
import ua.nure.shevchenko.provider.dao.entityDaoInterface.TariffDao;
import ua.nure.shevchenko.provider.entity.Tariff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlTariffDao extends MySqlDao implements TariffDao {

    private static final Logger LOG = Logger.getLogger(MySqlTariffDao.class);

    @Override
    public Tariff findTariffById(int id) {

        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Tariff tariff = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(DBCommands.SQL_FIND_TARIFF_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                tariff = parseTariff(resultSet);
            }
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_TARIFF_BY_ID, e);

        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return tariff;
    }

    @Override
    public Tariff findTariffByService(String name)  {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Tariff tariff = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(DBCommands.SQL_FIND_TARIFF_BY_SERVICE);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                tariff = parseTariff(resultSet);
            }
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_TARIFFS_BY_SERVICE, e);

        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return tariff;
    }

    @Override
    public Tariff findTariffByName(String tariffName) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Tariff tariff = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(DBCommands.SQL_FIND_TARIFF_BY_NAME);
            preparedStatement.setString(1, tariffName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                tariff = parseTariff(resultSet);
            }
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_TARIFF_BY_NAME, e);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return tariff;
    }

    @Override
    public Tariff parseTariff(ResultSet rs) throws SQLException {
        Tariff tariff = new Tariff();
        tariff.setId(rs.getInt(Fields.ENTITY_ID));
        tariff.setName(rs.getString(Fields.TARIFF_NAME));
        tariff.setSpecification(rs.getString(Fields.TARIFF_SPECIFICATION));
        tariff.setPrice(rs.getDouble(Fields.TARIFF_PRICE));
        tariff.setServiceId(rs.getInt(Fields.SERVICE_ID));
        return tariff;
    }

    @Override
    public void addTariff(Tariff tariff) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            pstmt = connection.prepareStatement(DBCommands.SQL_ADD_TARIFF, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, tariff.getName());
            pstmt.setString(2, tariff.getSpecification());
            pstmt.setDouble(3, tariff.getPrice());
            pstmt.setInt(4, tariff.getServiceId());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(pstmt);
            close(rs);
            close(connection);
        }
    }

    @Override
    public boolean updateTariff(Tariff tariff) {
        Connection con = null;
        boolean result = false;
        try {
            con = getConnection();
            updateTariff(con,tariff);
            result = true;
        } catch (SQLException e) {
            rollBack(con);
        }finally {

            close(con);
        }
        return result;
    }

    private void updateTariff(Connection con, Tariff tariff) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(DBCommands.SQL_UPDATE_TARIFF);
        int k = 1;
        pstmt.setDouble(k++, tariff.getPrice());
        pstmt.setString(k,tariff.getName());
        pstmt.executeUpdate();
        pstmt.close();
    }

   @Override
    public void deleteTariff(Tariff tariff)  {

        PreparedStatement statement = null;
        Connection con =getConnection();
        try {

            statement = con.prepareStatement(DBCommands.SQL_DELETE_TARIFF);
            statement.setString(1, tariff.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_DELETE_TARIFF);
        } finally {
            close(statement);
            close(con);
        }

    }


    @Override
    public List<Tariff> getAllTariffs() {
        List<Tariff> tariffs = new ArrayList();
        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(DBCommands.SQL_FIND_ALL_TARIFFS);
            while (rs.next()) {
                Tariff tariff = parseTariff(rs);
                tariffs.add(tariff);
            }
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_OBRAIN_TARIFFS, e);
        } finally {
            close(con, stmt, rs);
        }

        return tariffs;
    }
}
