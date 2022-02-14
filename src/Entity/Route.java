package Entity;

import Util.Util;

public class Route {

    public static int AUTO_ID = 100;

    private int route_id;
    private int distance;
    private int stations;

    Util util = new Util();

    public Route() {
    }

    public Route(int route_id, int distance, int stations) {
        this.route_id = route_id;
        this.distance = distance;
        this.stations = stations;
    }

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getStations() {
        return stations;
    }

    public void setStations(int stations) {
        this.stations = stations;
    }

    public static int getAutoId() {
        return AUTO_ID;
    }

    public static void setAutoId(int autoId) {
        AUTO_ID = autoId;
    }

    @Override
    public String toString() {
        return String.format("%-5s| %-13d| %-7d|", getRoute_id(), getDistance(), getStations());
    }

    public void inputInfo(){
        this.setRoute_id(Route.AUTO_ID);
        this.distance = util.checkInterger("Enter route's distance: ");
        this.stations = util.checkInterger("Enter station's number: ");
        Route.AUTO_ID++;
    }

}
