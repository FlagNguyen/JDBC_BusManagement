package Service;

import Entity.Route;
import MainRun.MainRun;
import Util.Util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RouteService {

    public static Util util = new Util();

    public void add_Route() {
        int routeQuantity = util.checkInterger("Enter route's number you want to add: ");
        List<Route> tempRoute = new ArrayList<>();
        for(int i =0; i<routeQuantity;i++){
            Route route = new Route();
            route.inputInfo();
            tempRoute.add(route);
        }
        MainRun.routes.addAll(tempRoute);
        MainRun.routeDAO.inputNewRoute(tempRoute);
    }

//    public void output_RouteFile(ArrayList<Route> rs) throws FileNotFoundException {
//        FileOutputStream fo = new FileOutputStream("Route List.txt");
//        try (PrintWriter out = new PrintWriter(fo)) {
//            out.print(print_routes(rs));
//        } catch (Exception e) {
//            System.err.println("Error");
//        }
//    }
    public void print_routeList() {
        Iterator<Route> i = MainRun.routes.iterator();
        System.out.println(String.format("%-5s| %-10s| %-5s|", "ID", "Distance (km)", "Station"));
        while (i.hasNext()) {
            System.out.println(i.next().toString());
        }
    }

    protected String print_routes(ArrayList<Route> rs) {
        String out = "";
        Iterator<Route> i = rs.iterator();
        out = String.format("%-5s| %-10s| %-5s|\n", "ID", "Distance (km)", "Station");
        while (i.hasNext()) {
            out += i.next().toString() + "\n";
        }
        return out;
    }

    protected Route find_Route(List<Route> routes) {
        do {
            int id = util.checkInterger("Enter route's id: ");
            for (int i = 0; i < routes.size(); i++) {
                if (routes.get(i).getRoute_id() == id) {
                    return routes.get(i);
                }
            }
            System.err.println("This route doesn't exist in system !");
        } while (true);
    }
}
