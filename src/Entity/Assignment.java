package Entity;

import java.util.HashMap;

public class Assignment {
    private Driver driver;
    private HashMap<Route, Integer> assign;
    private int totalRoute;
    private float totalDistance;

    public Assignment() {
    }

    public Assignment(Driver driver, HashMap<Route, Integer> assign) {
        this.driver = driver;
        this.assign = assign;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public HashMap<Route, Integer> getAssign() {
        return assign;
    }

    public void setAssign(HashMap<Route, Integer> assign) {
        this.assign = assign;
    }

    public int getTotalRoute() {
        return totalRoute;
    }

    public void setTotalRoute(int totalRoute) {
        this.totalRoute = totalRoute;
    }

    public float getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(float totalDistance) {
        this.totalDistance = totalDistance;
    }
}
