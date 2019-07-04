package ua.nure.shevchenko.provider.dao.entityDaoInterface;

import ua.nure.shevchenko.provider.entity.Score;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ScoreDao {
    /**
     * Finds score in a database by its id.
     *
     * @param id
     *            of a user to find.
     * @return Score object.
     *
     */
    Score findScoreById(int id);

    /**
     * Parses all Scores fields from database to Score object.
     *
     * @param rs
     *            ResultSet of values from database.
     * @return Score object.
     * @throws SQLException
     */
    Score parseScore(ResultSet rs) throws SQLException;

    /**
     * Inserts a Score into database.
     *
     * @param score
     *            to be inserted.
     * @return <b>true</b> if user has been successfully inserted in database or
     *         <b>false</b> if not.
     *
     */
   long addScore(Score score);

    /**
     * Updates a user in database.
     *
     * @param score
     *            to update.
     * @return <b>true</b> if user has been successfully updated or <b>false</b>
     *         if not.
     *
     */
     boolean updateScore(Score score);

    /**
     * Finds all scores in a database.
     *
     * @return List of Score objects.
     */
    List<Score> getAllScores() ;

}
