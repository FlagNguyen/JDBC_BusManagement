package Entity;

import Util.Util;

import java.io.IOException;

public class Person {
    private String name;
    private String address;
    private String phone;

    Util util = new Util();

    public Person(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void inputInfo() throws IOException {
        this.name = util.standardlizeString(util.checkString("Enter driver's name: "));
        this.address = util.standardlizeString(util.checkString("Enter driver's address: "));
        String phone = "";
        do {
            phone = util.checkString("Enter driver's phonenumber: ");
            if (phone.matches("[0-9]+") && phone.length() >= 5 && phone.length() <= 10) {
                break;
            }
            System.err.println("Phone number must be integer and has length 5-10");
        } while (true);
        this.phone = phone;
    }
}
