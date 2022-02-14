package Repository;

import Constant.DatabaseConstant;
import Entity.Route;
import Util.DbConnection;
import Util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteDAO {
    public static final String ROUTE_TABLE_NAME = "ROUTE";

    public static final String ID = "ROUTE_ID";
    public static final String RANGE = "DISTANCE";
    public static final String STOP_NUMBER = "STATIONS";

    Util util = new Util();

    private static final Connection connection;

    static {
        connection = DbConnection.open(DatabaseConstant.DRIVER_STRING, DatabaseConstant.URL, DatabaseConstant.USERNAME, DatabaseConstant.PASSWORD);
    }

    public void inputNewRoute(Route route) {
        if (util.isObjectEmpty(route)) {
            return;
        }
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT INTO " + ROUTE_TABLE_NAME + " VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, route.getRoute_id());
            preparedStatement.setFloat(2, route.getDistance());
            preparedStatement.setInt(3, route.getStations());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbConnection.closeConnection(null, preparedStatement, null);
        }
    }

    public void inputNewRoute(List<Route> routes) {
        if(util.isObjectEmpty(routes)){
            return;
        }
        routes.forEach(this::inputNewRoute);
    }



    public List<Route> getRoute(){
        List<Route> routes = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM " + ROUTE_TABLE_NAME;
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            routes = new ArrayList<>();
            while (resultSet.next()){
                int id = resultSet.getInt(ID);
                int distance = resultSet.getInt(RANGE);
                int stations = resultSet.getInt(STOP_NUMBER);
                Route route = new Route(id, distance,stations);
                if(util.isObjectEmpty(route)) continue;
                routes.add(route);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbConnection.closeConnection(resultSet,preparedStatement,null);
        }
        return routes;
    }
}
