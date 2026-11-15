import java.util.*;

/* ---------------- BILL CLASS ---------------- */
class Bill {
    double amount;
/**
 * Thsi si the constructor for bill class.
 * @param amount
 */
    public Bill(double amount) {
        this.amount = amount;
    }
/**
 * Thsi is the method to display the information.
 * @param vehicleNo
 * @param type
 */
    public void display(String vehicleNo, String type) {
        System.out.println("\n--- Bill Breakdown ---");
        System.out.println("Vehicle: " + vehicleNo + " (" + type + ")");
        System.out.println("Total Bill = " + amount + " PKR");
        System.out.println("-----------------------------------------\n");
    }
}
/**
 * This is the abstract class vehicle.
 */
abstract class Vehicle {
    String number;
    int entryHour;
    int exitHour;
/**
 * Thsi si the constructor for class Vehicle.
 * @param number
 * @param entryHour
 */
    public Vehicle(String number, int entryHour) {
        this.number = number;
        this.entryHour = entryHour;
    }
/**
 * Setter method to set exit time.
 * @param exitHour
 */
    public void setExitTime(int exitHour) {
        this.exitHour = exitHour;
    }
/**
 * Getter method 
 * @return
 */
    public int getDuration() {
        return exitHour - entryHour;
    }

    public abstract Bill calculateBill();
    public abstract String getType();
}

/**
 * This is the subclass bill.
 */
class Bike extends Vehicle {
    public Bike(String number, int entryHour) {
        super(number, entryHour);
    }

    public Bill calculateBill() {
        return new Bill(getDuration() * 20);
    }

    public String getType() {
        return "Bike";
    }
}

/* ---------------- CAR ---------------- */
class Car extends Vehicle {
    public Car(String number, int entryHour) {
        super(number, entryHour);
    }

    public Bill calculateBill() {
        return new Bill(getDuration() * 50);
    }

    public String getType() {
        return "Car";
    }
}

/**
 * This is the class truck that is the child class.
 */
class Truck extends Vehicle {
    public Truck(String number, int entryHour) {
        super(number, entryHour);
    }

    public Bill calculateBill() {
        return new Bill((getDuration() * 100) + 200);
    }

    public String getType() {
        return "Truck";
    }
}

/* ---------------- PARKING LOT ---------------- */
class ParkingLot {

    List<String> smallSlots = new ArrayList<>(Arrays.asList("S1", "S2"));
    List<String> mediumSlots = new ArrayList<>(Arrays.asList("M1", "M2"));
    List<String> largeSlots = new ArrayList<>(Arrays.asList("L1", "L2"));

    List<Vehicle> vehicles = new ArrayList<>();

    public String assignSlot(Vehicle v) {

        if (v instanceof Bike && !smallSlots.isEmpty()) {
            return smallSlots.remove(0);
        }

        if (v instanceof Car && !mediumSlots.isEmpty()) {
            return mediumSlots.remove(0);
        }

        if (v instanceof Truck && !largeSlots.isEmpty()) {
            return largeSlots.remove(0);
        }

        return "No Slot Available";
    }

    public void parkVehicle(Vehicle v) {
        String slot = assignSlot(v);
        vehicles.add(v);
        System.out.println("\nVehicle Entered:");
        System.out.println("Type: " + v.getType());
        System.out.println("Number: " + v.number);
        System.out.println("Assigned Slot: " + slot);
        System.out.println("Entry Time: " + v.entryHour + ":00");
        System.out.println("-----------------------------------------");
    }
    public void exitVehicle(Vehicle v, int exitHour) {
        v.setExitTime(exitHour);
        System.out.println("\nVehicle Exit:");
        System.out.println("Vehicle: " + v.number + " (" + v.getType() + ")");
        System.out.println("Exit Time: " + exitHour + ":00");
        System.out.println("Duration: " + v.getDuration() + " hours");

        Bill b = v.calculateBill();
        b.display(v.number, v.getType());
    }

    public void summary() {
        System.out.println("=========================================");
        System.out.println("Total Vehicles Parked: " + vehicles.size());
        System.out.println("=========================================");
    }
}

/**
* This is the main class.
*/
public class Task2 {
    /**
     * This is the main method. Program execution starts from here.
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ParkingLot lot = new ParkingLot();
        System.out.println("========== SMART PARKING SYSTEM ==========\n");
        System.out.print("Enter number of vehicles: ");
        int n = sc.nextInt();

        Vehicle[] arr = new Vehicle[n];

        /* ---------------- ENTRY ---------------- */
        for (int i = 0; i < n; i++) {

            System.out.println("\nEnter details for Vehicle " + (i + 1));

            System.out.print("Type (Bike/Car/Truck): ");
            String type = sc.next();

            System.out.print("Vehicle Number: ");
            String number = sc.next();

            System.out.print("Entry Time (hour): ");
            int entry = sc.nextInt();

            if (type.equalsIgnoreCase("Bike")) {
                arr[i] = new Bike(number, entry);
            } else if (type.equalsIgnoreCase("Car")) {
                arr[i] = new Car(number, entry);
            } else {
                arr[i] = new Truck(number, entry);
            }

            lot.parkVehicle(arr[i]);
        }

        /* ---------------- EXIT ---------------- */
        System.out.println("\n=========================================");
        System.out.println("            VEHICLE EXIT");
        System.out.println("=========================================");

        for (int i = 0; i < n; i++) {
            System.out.print("\nEnter exit time for " + arr[i].number + ": ");
            int exit = sc.nextInt();

            lot.exitVehicle(arr[i], exit);
        }

        lot.summary();
        sc.close();
    }
}