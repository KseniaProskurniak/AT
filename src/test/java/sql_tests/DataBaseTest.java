package sql_tests;

import org.testng.annotations.Test;
import redmain.db.managers.Manager;
import redmain.db.request.DataBaseConnection;

import static redmain.db.managers.Manager.*;

public class DataBaseTest {

    private DataBaseConnection dbConnection;

    @Test
    public void basicSqlTest() {
        dbConnection.executeQuery("SELECT * FROM projects");
    }
}
