package redmine.db;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;
@Slf4j

public class DataBaseConnection {
    private String dbHost;
    private Integer dbPort;
    private String dbUser;
    private String dbPass;
    private String dbName;
    private Connection connection;

    public DataBaseConnection() {
        initVariables();
        connect();
    }

    public Connection getConnection() {
        return connection;
    }

    private void initVariables() {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/dbconfig.propirties");
            properties.load(fileInputStream);
            dbHost = properties.getProperty("db.host");
            dbPort = Integer.parseInt(properties.getProperty("db.port"));
            dbUser = properties.getProperty("db.user");
            dbPass = properties.getProperty("db.pass");
            dbName = properties.getProperty("db.name");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    private void connect() {
        Class.forName("org.postgresql.Driver");
        String url = String.format("jdbc:postgresql://%s:%d/%s?user=%s&password=%s", dbHost, dbPort, dbName, dbUser, dbPass);
        connection = DriverManager.getConnection(url);
    }

    @SneakyThrows
    public List<Map<String, Object>> executeQuery(String query) {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        int count = resultSet.getMetaData().getColumnCount();
        List<String> columnNames = new ArrayList<>();
        List<Map<String, Object>> result = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            String columnName = resultSet.getMetaData().getColumnName(i);
            columnNames.add(columnName);
        }
        while (resultSet.next()) {
            Map<String, Object> columnData = new TreeMap<>();
            for (String columnName : columnNames) {
                Object value = resultSet.getObject(columnName);
                columnData.put(columnName, value);
            }
            result.add(columnData);
        }
        return result;
    }

    @SneakyThrows
    public List<Map<String, Object>> executePreparedQuery(String query, Object... parameters) {
        PreparedStatement statement = connection.prepareStatement(query);
        int index = 1;
        for (Object object : parameters) {
            statement.setObject(index++, object);
        }
        log.debug("statement: {}", statement);
        ResultSet resultSet = statement.executeQuery();
        int count = resultSet.getMetaData().getColumnCount();
        List<String> columnNames = new ArrayList<>();
        List<Map<String, Object>> result = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            String columnName = resultSet.getMetaData().getColumnName(i);
            columnNames.add(columnName);
        }
        while (resultSet.next()) {
            Map<String, Object> columnData = new TreeMap<>();
            for (String columnName : columnNames) {
                Object value = resultSet.getObject(columnName);
                columnData.put(columnName, value);
            }
            result.add(columnData);
        }
        return result;
    }
}
