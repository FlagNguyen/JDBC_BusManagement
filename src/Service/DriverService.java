package Service;

import Entity.Assignment;
import Entity.Driver;
import MainRun.MainRun;
import Repository.DriverDAO;
import Util.Util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DriverService {

    public static Util util = new Util();

    public void add_Driver() throws IOException {
        int driverQuantity = util.checkInterger("Enter number drivers you want to add: ");
        List<Driver> tempDriver = new ArrayList<>();
        for(int i = 0; i < driverQuantity;i++){
            Driver driver = new Driver();
            driver.inputInfo();
            tempDriver.add(driver);
        }
        MainRun.drivers.addAll(tempDriver);
        MainRun.driverDAO.inputNewDriver(tempDriver);
    }

//    public void output_DriverFile(ArrayList<Driver> ds) throws FileNotFoundException {
//        FileOutputStream fo = new FileOutputStream("Driver List.txt");
//        try (PrintWriter out = new PrintWriter(fo)) {
//            out.print(print_drivers(ds));
//        } catch (Exception e) {
//            System.err.println("Error");
//        }
//    }

    protected String print_driver(Driver driver) {
        return String.format("%-5d| %-20s| %-10s| %-12s| %-5s|", driver.getDriver_id(), driver.getName(), driver.getAddress(), driver.getPhone(), driver.getLevel());
    }

    public void print_driverList() {
        Iterator<Driver> iter = MainRun.drivers.iterator();
        System.out.println(String.format("%-5s| %-20s| %-10s| %-12s| %-5s|", "ID", "Full Name", "Address", "Phone", "Level"));
        while (iter.hasNext()) {
            System.out.println(print_driver(iter.next()));
        }
    }

    protected String print_drivers(ArrayList<Driver> drivers) {
        String out = "";
        Iterator<Driver> iter = drivers.iterator();
        out = String.format("%-5s| %-20s| %-10s| %-12s| %-5s|\n", "ID", "Full Name", "Address", "Phone", "Level");
        while (iter.hasNext()) {
            out += print_driver(iter.next()) + "\n";
        }
        return out;
    }

    protected Driver find_Driver(ArrayList<Driver> drivers) {

        do {
            int id = util.checkInterger("Enter driver's id: ");
            for (int i = 0; i < drivers.size(); i++) {
                if (drivers.get(i).getDriver_id() == id) {
                    return drivers.get(i);
                }
            }
            System.err.println("This driver doesn't exist in system !");
        } while (true);
    }

    protected boolean find_Driver(ArrayList<Assignment> assignments, Driver driver) {
        for (int i = 0; i < assignments.size(); i++) {
            if (assignments.get(i).getDriver().equals(driver)) {
                return true;
            }
        }
        return false;
    }
}
