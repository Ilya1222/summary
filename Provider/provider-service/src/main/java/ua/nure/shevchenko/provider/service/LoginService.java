package ua.nure.shevchenko.provider.service;

import ua.nure.shevchenko.provider.constans.Page;
import ua.nure.shevchenko.provider.dao.mysqlDao.MySqlUserDao;
import ua.nure.shevchenko.provider.entity.User;
import ua.nure.shevchenko.provider.utils.PasswordHash;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class LoginService {

    MySqlUserDao dao = new MySqlUserDao();


    /**
     * Checks if there is such a client
     *
     * @param login
     *
     * @param password
     *
     * @return  <b>true</b> if user  has exist
     *      *         <b>false</b> if not.
     *
     */
    public boolean isExist(String login, String password) {
        User user = getUser(login);

        String passHash = null;

        if (user == null) {
            return false;
        } else {

            try {
                passHash = PasswordHash.hash(password);
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return (passHash.equals(user.getPassword()) && login.equals(user.getLogin()));
        }

    }

    public User getUser(String login){
        return dao.findUserByLogin(login);
    }

    User getUser(long id){

        return dao.findUserById((int)id);
    }


    public List<User> getAllUser(){
        return dao.getAllUsers();
    }



    /**
     * Returns page by user role
     *
     * @param user
     *
     * @return  String
     *
     */
    public String getPage(User user){
        String page;
         if (user.getRoleId() < 2) {
            page = Page.ADMIN_PAGE;
         }else {
             page  =  Page.CLIENT_PAGE;
         }
        return page;
    }


}
