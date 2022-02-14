package Repository;

import Constant.DatabaseConstant;
import Entity.Assignment;
import Entity.Driver;
import Entity.Route;
import Util.DbConnection;
import Util.Util;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class AssignmentDAO {

    private static final String DRIVING_TABLE_NAME = "ASSIGNMENT";

    private static final String DRIVER_ID = "DRIVER_ID";
    private static final String ROUTE_ID = "ROUTE_ID";
    private static final String ROUND_TRIP_NUMBER = "TURN";

    public static final Connection connection;


    static {
        connection = DbConnection.open(DatabaseConstant.DRIVER_STRING, DatabaseConstant.URL, DatabaseConstant.USERNAME, DatabaseConstant.PASSWORD);
    }

    public List<Assignment> getAssignments() {
        List<Assignment> assignments = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = String.format("SELECT d.ID, d.NAME, d.ADDRESS, d.PHONE, d.D_LEVEL, r.ROUTE_ID, r.DISTANCE, r.STATIONS" +
                    "FROM %s as, %s d, %s r " +
                    "WHERE as.DRIVER_ID = d.ID AND as.ROUTE_ID = r.ROUTE_ID", DRIVING_TABLE_NAME, DriverDAO.DRIVER_TABLE_NAME, RouteDAO.ROUTE_TABLE_NAME);

            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int driverID = resultSet.getInt(DRIVER_ID);
                String name = resultSet.getString(DriverDAO.NAME);
                String address = resultSet.getString(DriverDAO.ADDRESS);
                String phoneNumber = resultSet.getString(DriverDAO.PHONE_NUMBER);
                String level = resultSet.getString(DriverDAO.LEVEL);
                Driver driver = new Driver(driverID, name, address, phoneNumber, level);

                int routeID = resultSet.getInt(ROUTE_ID);
                int range = resultSet.getInt(RouteDAO.RANGE);
                int stopNumber = resultSet.getInt(RouteDAO.STOP_NUMBER);
                Route route = new Route(routeID, range, stopNumber);
                int turn = resultSet.getInt(ROUND_TRIP_NUMBER);

                HashMap<Route,Integer> tempAssign = null;
                tempAssign.put(route,turn);
                Assignment assignment = findDriver(assignments,driverID);

                if(Util.isObjectEmpty(assignment)){
                    Assignment assignment1 = new Assignment(driver,tempAssign);
                    assignments.add(assignment1);
                }else {
                    int idx = assignments.indexOf(assignment);
                    assignments.get(idx).getAssign().putAll(tempAssign);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbConnection.closeConnection(resultSet,preparedStatement,null);
        }
        return assignments;
    }

    private Assignment findDriver(List<Assignment> assignments, int driverID) {
        List<Assignment> assignmentList = assignments.stream().filter(assignment -> assignment.getDriver().getDriver_id() == driverID).collect(Collectors.toList());
        if (!Util.isCollectionEmpty(assignmentList)){
            assignmentList.get(0);
        }
        return null;
    }

    public void inputNewAssign(List<Assignment> tempAssignment) {
        if(Util.isCollectionEmpty(tempAssignment)){
            return;
        }
        tempAssignment.forEach(this::inputNewAssign);
    }

    public void inputNewAssign(Assignment assignment){
        if(Util.isObjectEmpty(assignment)){
            return;
        }
        int driverID = assignment.getDriver().getDriver_id();
        assignment.getAssign().forEach((route, integer) -> {
            PreparedStatement preparedStatement = null;
            try {
                String query = "INSERT INTO " + DRIVING_TABLE_NAME + "VALUES (?, ?, ?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, driverID);
                preparedStatement.setInt(2, assignment.getTotalRoute());
                preparedStatement.setFloat(3, assignment.getTotalDistance());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
