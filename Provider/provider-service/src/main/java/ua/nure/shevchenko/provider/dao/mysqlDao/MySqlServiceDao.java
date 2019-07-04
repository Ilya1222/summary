package ua.nure.shevchenko.provider.dao.mysqlDao;
import org.apache.log4j.Logger;
import ua.nure.shevchenko.provider.constans.DBCommands;
import ua.nure.shevchenko.provider.constans.Fields;
import ua.nure.shevchenko.provider.constans.Messages;
import ua.nure.shevchenko.provider.dao.entityDaoInterface.ServiceDao;
import ua.nure.shevchenko.provider.entity.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class MySqlServiceDao extends MySqlDao implements ServiceDao {

    private static final Logger LOG = Logger.getLogger(MySqlServiceDao.class);

    @Override
    public Service findServiceById(int id)  {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Service service=null;
        try{
            connection = getConnection();
            preparedStatement = connection.prepareStatement(DBCommands.SQL_FIND_SERVICE_BY_ID);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                service = parseService(resultSet);
            }
        }catch (SQLException ex){
            LOG.error(Messages.ERR_CANNOT_OBTAIN_SERVICE_BY_ID);
        } finally {
        close(connection, preparedStatement, resultSet);
    }
        return service;
    }


    @Override
    public Service findServiceByName(String name)  {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Service service=null;
        try{
            connection = getConnection();
            preparedStatement = connection.prepareStatement(DBCommands.SQL_FIND_SERVICE_BY_NAME);
            preparedStatement.setString(1,name);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                service = parseService(resultSet);
            }
        }catch (SQLException ex){
            LOG.error(Messages.ERR_CANNOT_OBTAIN_SERVICE_BY_NAME);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return service;

    }

    @Override
    public Service parseService(ResultSet rs) throws SQLException {
        Service service = new Service();
        service.setId(rs.getInt(Fields.ENTITY_ID));
        service.setName(rs.getString(Fields.SERVICE_NAME));
        return service;
    }


    @Override
    public List<Service> getAllServices()  {
        List<Service> services = new ArrayList();
        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(DBCommands.SQL_FIND_ALL_SERVICES);
            while (rs.next()) {
                Service service = parseService(rs);
                services.add(service);
            }
        } catch (SQLException e) {
             LOG.error(Messages.ERR_CANNOT_OBTAIN_ALL_SERVICES, e);
        } finally {
            close(con, stmt, rs);
        }

        return services;
    }

}
