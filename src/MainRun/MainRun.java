package MainRun;

import Entity.Assignment;
import Entity.Driver;
import Entity.Route;
import Repository.AssignmentDAO;
import Repository.DriverDAO;
import Repository.RouteDAO;
import Service.AssignmentService;
import Service.DriverService;
import Service.RouteService;
import Util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainRun {

    public static List<Driver> drivers = new ArrayList<>();
    public static List<Route> routes = new ArrayList<>();
    public static List<Assignment> assignments = new ArrayList<>();

    public static final DriverDAO driverDAO =  new DriverDAO();
    public static final RouteDAO routeDAO = new RouteDAO();
    public static final AssignmentDAO assignmentDAO = new AssignmentDAO();

    public static final DriverService driverService = new DriverService();
    public static final RouteService routeService = new RouteService();
    public static final AssignmentService assignmentService = new AssignmentService();

    public static final Util util = new Util();

    public static void main(String[] args) throws IOException {
        System.out.println("-----Bus Management-----");
        init();
        menu();
    }

    public static void menu() throws IOException {
        while (true) {
            System.out.println("1.  Enter new driver");
            System.out.println("2.  Enter new route");
            System.out.println("3.  Assign for driver");
            System.out.println("4.  Sort");
            System.out.println("5.  Print assginment list");
            System.out.println("6.  Exit");

            int choice = util.checkChoice("Enter your choice: ", 1, 6);

            switch (choice) {
                case 1:
                    driverService.add_Driver();
                    driverService.print_driverList();
                    break;
                case 2:
                    routeService.add_Route();
                    routeService.print_routeList();
                    break;
                case 3:
                    assignmentService.assign();
                    assignmentService.print_AssignmenList(assignments);
                    break;
                case 4:
                    System.out.println("1.  Sort by name");
                    System.out.println("2.  Sort by turn");
                    int c = util.checkChoice("Enter your choice: ",1,2);
                    if(c==1){
                        assignmentService.sort_by_name(assignments);
                    }else {
                        assignmentService.sort_by_turn(assignments);
                    }
                    break;
                case 5:
                    assignmentService.print_AssignmenList(assignments);
                    break;
                case 6:
                    return;
            }
        }
    }

    private static void init(){
        drivers = !Util.isCollectionEmpty(driverDAO.getDrivers()) ? driverDAO.getDrivers() : new ArrayList<>();
        if(Util.isCollectionEmpty(drivers)){
            Driver.AUTO_ID = 10000;
        }else {
            drivers.sort(Comparator.comparing(Driver::getDriver_id));
            Driver.AUTO_ID = drivers.get(drivers.size()-1).getDriver_id()+1;
        }

        routes = !Util.isCollectionEmpty(routeDAO.getRoute()) ? routeDAO.getRoute() : new ArrayList<>();
        if(Util.isCollectionEmpty(routes)){
            Route.AUTO_ID = 100;
        }else {
            routes.sort(Comparator.comparing(Route::getRoute_id));
            Route.AUTO_ID = routes.get(routes.size() - 1).getRoute_id() + 1;
        }

        assignments = !Util.isCollectionEmpty(assignmentDAO.getAssignments()) ? assignmentDAO.getAssignments() : new ArrayList<>();
    }
}
