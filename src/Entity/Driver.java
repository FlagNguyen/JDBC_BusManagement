package Entity;

import java.io.IOException;

public class Driver extends Person {
    public static int AUTO_ID = 10000;
    private int driver_id;
    private String level;

    private static final String LEVEL_A = "Level A";
    private static final String LEVEL_B = "Level B";
    private static final String LEVEL_C = "Level C";
    private static final String LEVEL_D = "Level D";
    private static final String LEVEL_E = "Level E";
    private static final String LEVEL_F = "Level F";

    public Driver(int driver_id, String level, String name, String address, String phone) {
        super(name, address, phone);
        this.driver_id = driver_id;
        this.level = level;
    }

    public Driver(int driver_id, String level) {
        this.driver_id = driver_id;
        this.level = level;
    }

    public Driver() {
    }

    public static int getAutoId() {
        return AUTO_ID;
    }

    public static void setAutoId(int autoId) {
        AUTO_ID = autoId;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public static String getLevelA() {
        return LEVEL_A;
    }

    public static String getLevelB() {
        return LEVEL_B;
    }

    public static String getLevelC() {
        return LEVEL_C;
    }

    public static String getLevelD() {
        return LEVEL_D;
    }

    public static String getLevelE() {
        return LEVEL_E;
    }

    public static String getLevelF() {
        return LEVEL_F;
    }

    public void inputInfo() throws IOException {
        this.setDriver_id(Driver.AUTO_ID);
        super.inputInfo();
        System.out.println("Driver's level: ");
        System.out.println("1.Level A");
        System.out.println("2.Level B");
        System.out.println("3.Level C");
        System.out.println("4.Level D");
        System.out.println("5.Level E");
        System.out.println("6.Level F");
        int level = util.checkChoice("Enter drive's level (1-6): ",1,6);

        switch (level){
            case 1:
                this.setLevel(Driver.LEVEL_A);
                break;
            case 2:
                this.setLevel(Driver.LEVEL_B);
                break;
            case 3:
                this.setLevel(Driver.LEVEL_C);
                break;
            case 4:
                this.setLevel(Driver.LEVEL_D);
                break;
            case 5:
                this.setLevel(Driver.LEVEL_E);
                break;
            case 6:
                this.setLevel(Driver.LEVEL_F);
                break;
        }
        Driver.AUTO_ID++;
    }

}
