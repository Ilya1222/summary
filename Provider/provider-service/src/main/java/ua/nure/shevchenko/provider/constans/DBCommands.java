package ua.nure.shevchenko.provider.constans;

import sun.security.provider.PolicySpiFile;

public final class DBCommands {
    //User
    public static final String SQL_ADD_USER = "INSERT INTO users (login, password, role_id, first_name, sur_name, last_name ,email, phone_number , score_id , isBlocked) VALUES ( ? , ?, ?, ?, ?, ?, ?, ? ,? ,? )";
    public static final String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    public static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    public static final String SQL_UPDATE_USER = "UPDATE users SET isBlocked = ? WHERE id = ?";
    public static final String SQL_FIND_ALL_USERS = "SELECT * FROM users WHERE role_id = 2";

    //Score
    public static final String SQL_FIND_SCORE_BY_ID = "SELECT * FROM scores WHERE id = ?";
    public static final String SQL_ADD_SCORE = "INSERT INTO scores(balance) VALUES (?) ";
    public static final String SQL_UPDATE_SCORE = "UPDATE scores SET balance = ? WHERE id = ?  ";
    public static final String SQL_FIND_ALL_SCORES = "SELECT * FROM scores";

    //Service
    public static final String SQL_FIND_SERVICE_BY_ID = "SELECT * FROM services WHERE id = ?";
    public static final String SQL_FIND_SERVICE_BY_NAME = "SELECT * FROM services WHERE service_name = ?";
    public static final String SQL_FIND_ALL_SERVICES = "SELECT * FROM services";

    //Tariff
    public static final String SQL_ADD_TARIFF = "INSERT INTO tariffs (tariff_name, specification, price, service_id  ) VALUES ( ?,?,?,?)";
    public static final String SQL_FIND_TARIFF_BY_ID = "SELECT * FROM tariffs WHERE id = ?";
    public static final String SQL_FIND_TARIFF_BY_NAME = "SELECT * FROM tariffs WHERE tariff_name = ?";
    public static final String SQL_FIND_TARIFF_BY_SERVICE = "SELECT * FROM tariffs WHERE service_id = ?";
    public static final String SQL_UPDATE_TARIFF = "UPDATE tariffs SET price = ? WHERE tariff_name = ?";
    public static final String SQL_FIND_ALL_TARIFFS = "SELECT * FROM tariffs";
    public static final String SQL_DELETE_TARIFF = "DELETE FROM tariffs WHERE tariff_name =?";


    //TariffConnectionInfo

    public static final String SQL_ADD_INFORMATION_LIST = " INSERT INTO tariff_connection_info ( user_id ,tariff_id, tariff_name ,service_id,  period , money , isDebt ) VALUES ( ? , ? ,?, ?, ?, ? , ?)";
    public static final String SQL_FIND_INFORMATION_LIST_BY_TARIFF = " SELECT * FROM tariff_connection_info WHERE tariff_id = ? ";
    public static final String SQL_FIND_INFORMATION_LIST_BY_PERIOD = " SELECT * FROM tariff_connection_info WHERE period =  ?";
    public static final String SQL_FIND_INFORMATION_LIST_BY_DEBT = " SELECT * FROM tariff_connection_info WHERE isDebt = 1";
    public static final String SQL_FIND_INFORMATION_LIST_BY_USER = " SELECT * FROM tariff_connection_info WHERE user_id = ?";
    public static final String SQL_FIND_INFORMATION_LIST_BY_USER_DEBT = " SELECT * FROM tariff_connection_info WHERE user_id = ? and isDebt = true ";
    public static final String SQL_UPDATE_INFO = "UPDATE tariff_connection_info SET tariff_id = ?, tariff_name = ?, period = ? , money = ? WHERE id = ?";
    public static final String SQL_UPDATE_INFO_DEBT = "UPDATE tariff_connection_info SET isDebt = ? WHERE id = ?";
    public static final String SQL_DELETE_INFO = "DELETE FROM tariff_connection_info WHERE id = ?";
    public static final String SQL_FIND_INFORMATION_LIST_BY_ALL = " SELECT * FROM tariff_connection_info";
}
