package Service;

import Entity.Assignment;
import Entity.Driver;
import Entity.Route;
import MainRun.MainRun;
import Util.Util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;

public class AssignmentService {

    public static Util util = new Util();
    public static DriverService driverService = new DriverService();
    public static RouteService routeService = new RouteService();

    public boolean checkDriversAndRoutes(){
        return !util.isCollectionEmpty(MainRun.drivers) && !util.isCollectionEmpty(MainRun.routes);
    }

    public void assign() {
        if(!checkDriversAndRoutes()){
            System.out.println("You must input driver and routes firstly !");
            return;
        }

        boolean check;
        List<Assignment> tempAssignment = new ArrayList<>();
        for(int i = 0; i < MainRun.drivers.size();i++){
            String driverName = MainRun.drivers.get(i).getName();
            System.out.println("Assign for " + driverName);
            int assignRouteNum = util.checkChoice("Enter number of route assigned for " +driverName,0,MainRun.routes.size());
            if (assignRouteNum == 0 ) continue;

            HashMap<Route,Integer> tempAssign = null;
            for(int j = 0 ;j<assignRouteNum;j++){
                Route route = routeService.find_Route(MainRun.routes);
                System.out.println(route.toString());

                int turn = util.checkChoice("Enter turn " + driverName + " ride this route: ",0, 15-turn(MainRun.assignments.get(i).getAssign()));
                tempAssign.put(route,turn);
            }
            Assignment assignment = new Assignment(MainRun.drivers.get(i), tempAssign);
            tempAssignment.add(assignment);
            assignment.setTotalRoute(assignRouteNum);
            MainRun.assignments.add(assignment);
        }
    MainRun.assignmentDAO.inputNewAssign(tempAssignment);
    }

    public void print_AssignmenList(List<Assignment> a) {
        System.out.printf("%-20s| %-10s|\n", "Driver Name", "Distance");
        for (int i = 0; i < a.size(); i++) {
            System.out.printf("%-20s| %-10d|\n", a.get(i).getDriver().getName(), total_distance(a.get(i).getAssign()));
        }
    }

//    public void output_AssignmentFile(ArrayList<Assignment> as) throws FileNotFoundException {
//        FileOutputStream fo = new FileOutputStream("Assignment List.txt");
//        try (PrintWriter out = new PrintWriter(fo)) {
//            out.print(print_Assignment(as));
//        } catch (Exception e) {
//            System.err.println("Error");
//        }
//    }

    protected int turn(HashMap<Route, Integer> assign) {
        int sum = 0;
        Collection<Integer> values = assign.values();
        Iterator<Integer> iter = values.iterator();
        while (iter.hasNext()) {
            sum += iter.next();
        }
        return sum;
    }

    public void print_AssignList(List<Assignment> a) {
        System.out.println("");
        for (int i = 0; i < a.size(); i++) {
            System.out.println(driverService.print_driver(a.get(i).getDriver()));
            Set<Route> keySet = a.get(i).getAssign().keySet();
            System.out.println("Assignment list:");
            for (Route key : keySet) {
                System.out.printf("Route id: %d - %d turns\n", key.getRoute_id(), a.get(i).getAssign().get(key));
            }
            System.out.println("-------------------------------------------------------------\n");
        }
    }

    String print_Assignment(ArrayList<Assignment> as) {
        String out = "";
        for (int i = 0; i < as.size(); i++) {
            out += driverService.print_driver(as.get(i).getDriver()) + "\n";
            Set<Route> keySet = as.get(i).getAssign().keySet();
            out += "Assignment List: \n";
            for (Route key : keySet) {
                out += String.format("Route id: %d - %d turns\n", key.getRoute_id(), as.get(i).getAssign().get(key));
            }
            out += String.format("Total Distance: %d\n", total_distance(as.get(i).getAssign()));
            out += "-------------------------------------------------------------\n";
        }
        return out;
    }

    protected int total_distance(HashMap<Route, Integer> a) {
        int sum = 0;
        Set<Route> keySet = a.keySet();
        for (Route key : keySet) {
            int distance = key.getDistance() * a.get(key);
            sum += distance;
        }
        return sum;
    }

    public void sort_by_name(List<Assignment> a) {
        for (int i = 0; i < a.size() - 1; i++) {
            for (int j = i + 1; j < a.size(); j++) {
                if (a.get(i).getDriver().getName().compareTo(a.get(j).getDriver().getName()) > 0) {
                    Assignment temp = a.get(i);
                    a.set(i, a.get(j));
                    a.set(j, temp);
                }
            }
        }
        print_AssignList(a);
    }

    public void sort_by_turn(List<Assignment> a) {
        for (int i = 0; i < a.size() - 1; i++) {
            for (int j = i + 1; j < a.size(); j++) {
                if (turn(a.get(i).getAssign()) > turn(a.get(j).getAssign())) {
                    Assignment temp = a.get(i);
                    a.set(i, a.get(j));
                    a.set(j, temp);
                }
            }
        }
        print_AssignList(a);
    }
}
