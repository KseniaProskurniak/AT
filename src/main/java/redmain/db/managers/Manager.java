package redmain.db.managers;


public class Manager {
    private static DataBaseConnection dbConnection = new DataBaseConnection();

    public static DataBaseConnection getConnection() {
        return dbConnection;
    }
}

