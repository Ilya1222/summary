package ua.nure.shevchenko.provider.service;

import ua.nure.shevchenko.provider.dao.mysqlDao.MySqlScoreDao;
import ua.nure.shevchenko.provider.dao.mysqlDao.MySqlUserDao;
import ua.nure.shevchenko.provider.entity.Score;
import ua.nure.shevchenko.provider.entity.User;

public class ClientService {



    public Score getScoreById (int id){

        MySqlScoreDao dao = new MySqlScoreDao();

        Score score = dao.findScoreById(id);

        return score;

    }


    /**
     * Produce replenish score
     *
     * @param score
     *
     * @param amount
     *
     * @return Score object.
     *
     */
    public Score doReplenish(Score score , double amount){

        double res = score.getBalance()+amount;
        score.setBalance(res);
        return score;
    }


    public void updateScore(Score score){

        MySqlScoreDao dao = new MySqlScoreDao();

        dao.updateScore(score);
    }


    /**
     * Change user block status in a data base
     *
     * @param user
     *
     * @param block
     *
     */
    public void changeStatus(User user , boolean block ){

        MySqlUserDao userDao = new MySqlUserDao();

        user.setBlocked(block);

        userDao.updateBlockStatus(user);

    }


    /**
     * Create new score.
     *
     * @return id score.
     *
     */
    public long createScore(){
        MySqlScoreDao dao = new MySqlScoreDao();

        Score score = new Score(0);

        long id = dao.addScore(score);

        return id;
    }

}
