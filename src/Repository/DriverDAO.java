package Repository;

import Constant.DatabaseConstant;
import Entity.Driver;
import Util.DbConnection;
import Util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverDAO {
    public static final String DRIVER_TABLE_NAME = "DRIVERS";

    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String ADDRESS = "ADDRESS";
    public static final String PHONE_NUMBER = "PHONE";
    public static final String LEVEL = "D_LEVEL";

    public static final Connection connection;

    static {
        connection = DbConnection.open(DatabaseConstant.DRIVER_STRING, DatabaseConstant.URL, DatabaseConstant.USERNAME, DatabaseConstant.PASSWORD);
    }

    public void inputNewDriver(Driver driver) {
        if (Util.isObjectEmpty(driver)) {
            return;
        }

        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT INTO " + DRIVER_TABLE_NAME + " VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, driver.getDriver_id());
            preparedStatement.setString(2, driver.getName());
            preparedStatement.setString(3, driver.getAddress());
            preparedStatement.setString(4, driver.getPhone());
            preparedStatement.setString(5, driver.getLevel());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbConnection.closeConnection(null, preparedStatement, null);
        }
    }

    public void inputNewDriver(List<Driver> drivers) {
        if (Util.isCollectionEmpty(drivers)) {
            return;
        }
        drivers.forEach(this::inputNewDriver);
    }

    public List<Driver> getDrivers() {
        List<Driver> drivers = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM " + DRIVER_TABLE_NAME;
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            drivers = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(ID);
                String name = resultSet.getString(NAME);
                String address = resultSet.getString(ADDRESS);
                String phone = resultSet.getString(PHONE_NUMBER);
                String level = resultSet.getString(LEVEL);
                Driver driver = new Driver(id, name, address, phone, level);
                if (Util.isObjectEmpty(driver)) continue;
                drivers.add(driver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbConnection.closeConnection(resultSet, preparedStatement, null);
        }
        return drivers;
    }
}
