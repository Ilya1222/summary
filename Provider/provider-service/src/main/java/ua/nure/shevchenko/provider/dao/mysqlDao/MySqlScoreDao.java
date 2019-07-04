package ua.nure.shevchenko.provider.dao.mysqlDao;

import org.apache.log4j.Logger;
import ua.nure.shevchenko.provider.constans.DBCommands;
import ua.nure.shevchenko.provider.constans.Fields;
import ua.nure.shevchenko.provider.constans.Messages;
import ua.nure.shevchenko.provider.dao.entityDaoInterface.ScoreDao;
import ua.nure.shevchenko.provider.entity.Score;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlScoreDao extends MySqlUserDao implements ScoreDao {

    private static final Logger LOG = Logger.getLogger(MySqlUserDao.class);

    @Override
    public Score findScoreById(int id)  {
        Connection connection = null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Score score = null;
        try{
            connection=getConnection();
            preparedStatement = connection.prepareStatement(DBCommands.SQL_FIND_SCORE_BY_ID);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
             score = parseScore(resultSet);
            }
        }catch (SQLException ex){
            LOG.error(Messages.ERR_CANNOT_OBTAIN_SCORE_BY_ID);
        }finally {
            close(connection,preparedStatement,resultSet);
        }
        return score;
    }

    @Override
    public Score parseScore(ResultSet rs) throws SQLException {
        Score score = new Score();
        score.setId(rs.getInt(Fields.ENTITY_ID));
        score.setBalance(rs.getDouble(Fields.SCORE_BALANCE));
        return score;
    }

    @Override
    public long addScore(Score score){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int counter =1 ;
        long id = 0;

        try{
            connection=getConnection();
            preparedStatement = connection.prepareStatement(DBCommands.SQL_ADD_SCORE,PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setDouble(counter++,score.getBalance());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                id =(long) resultSet.getInt(1);
            }
        }catch(SQLException ex){
            LOG.error(Messages.ERR_CANNOT_CREATE_SCORE, ex);
        }finally {
            close(preparedStatement);
            close(resultSet);
            close(connection);
        }
        return id;
    }

    @Override
    public boolean updateScore(Score score) {
        Connection con = getConnection();
        PreparedStatement pstmt=null;
        boolean result = false;
        try {
            pstmt = con.prepareStatement(DBCommands.SQL_UPDATE_SCORE);
            pstmt.setDouble(1,score.getBalance());
            pstmt.setLong(2,score.getId());
            pstmt.executeUpdate();
            result = true;
        } catch (SQLException e) {
            rollBack(con);
            LOG.error(Messages.ERR_CANNOT_UPDATE_SCORE, e);
        }finally {
            close(pstmt);
            close(con);
        }

        return result;
    }

    @Override
    public List<Score> getAllScores()  {
        List<Score> scores = new ArrayList<>();
        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(DBCommands.SQL_FIND_ALL_USERS);
            while (rs.next()) {
                Score score = parseScore(rs);
                scores.add(score);
            }
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_SCORES, e);

        } finally {
            close(con, stmt, rs);
        }

        return scores;
    }
}
