import java.util.Scanner;

/**
 * --------------------------------------------Lab Task 1: Smart Energy Billing System----------------------------------------------
 * This class represents a bill generated for a customer.
 */
class Bill {
    private int units;
    private double totalCost;
    private String breakdown;

    /**
     *Thia ia the constructor for Bill
     * @param units Units consumed
     * @param totalCost Total bill amount
     * @param breakdown Detailed breakdown of bill
     */
    public Bill(int units, double totalCost, String breakdown) {
        this.units = units;
        this.totalCost = totalCost;
        this.breakdown = breakdown;
    }

    /**
     * This method displays the bill.
     */
    public void displayBill() {
        System.out.println("\n--- Bill Breakdown ---");
        System.out.println(breakdown);
        System.out.println("---------------------------------");
        System.out.println("Total Bill = " + totalCost + " PKR");
        System.out.println("---------------------------------");
    }
}

/**
 * This is the Abstract class representing a customer.
 */
abstract class Customer {
    protected int id;
    protected String name;
    protected int units;
    /**
     * Thsi is the constructor for Customer.
     */
    public Customer(int id, String name, int units) {
        this.id = id;
        this.name = name;
        this.units = units;
    }

    /**
     * Thsi is the Abstract method to generate bill
     */
    public abstract Bill generateBill();

    /**
     * Thsi is the method to
     * Display customer info
     */
    public void displayInfo(String type) {
        System.out.println("\n=================================");
        System.out.println("Customer ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("Units Consumed: " + units);
    }
}

/**
 *This is the  Residential Customer class that is the child class .
 */
class ResidentialCustomer extends Customer {
  /**
   * Thsi is the constructor for subclass.
   * @param id
   * @param name
   * @param units
   */
    public ResidentialCustomer(int id, String name, int units) {
        super(id, name, units);
    }

    @Override
    public Bill generateBill() {

        int first = Math.min(units, 100);
        int remaining = Math.max(0, units - 100);

        double cost1 = first * 5;
        double cost2 = remaining * 8;
        double subtotal = cost1 + cost2;
        double subsidy = subtotal * 0.10;
        double total = subtotal - subsidy;

        String breakdown =
                "First 100 units @ 5 = " + cost1 + "\n" +
                "Remaining units @ 8 = " + cost2 + "\n" +
                "Subtotal = " + subtotal + "\n" +
                "Subsidy (-10%) = -" + subsidy;

        return new Bill(units, total, breakdown);
    }
}

/**
 * This is the Commercial Customer class which is a subclass.
 */
class CommercialCustomer extends Customer {

    public CommercialCustomer(int id, String name, int units) {
        super(id, name, units);
    }

    @Override
    public Bill generateBill() {

        double base = units * 10;
        double service = 200;
        double tax = (base + service) * 0.05;
        double total = base + service + tax;

        String breakdown =
                "All units @ 10 = " + base + "\n" +
                "Service Charges = " + service + "\n" +
                "Tax (5%) = " + tax;

        return new Bill(units, total, breakdown);
    }
}

/**
 * This is the Industrial Customer class.
 */
class IndustrialCustomer extends Customer {

    public IndustrialCustomer(int id, String name, int units) {
        super(id, name, units);
    }

    @Override
    public Bill generateBill() {

        double base = units * 12;
        double peak = 300;
        double tax = (base + peak) * 0.10;
        double total = base + peak + tax;

        String breakdown =
                "All units @ 12 = " + base + "\n" +
                "Peak Charges = " + peak + "\n" +
                "Tax (10%) = " + tax;

        return new Bill(units, total, breakdown);
    }
}

/**
 * This is the Main class to run the system.
 */
public class Task1 {
    /**
     * This is the main method. Program execution starts from here.
     * @param args
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("========== ELECTRICITY BILLING SYSTEM ==========\n");
        System.out.print("Enter number of customers: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline
        Customer[] customers = new Customer[n];
        /* 
        s-------- Taking INPUT from the user --------
         */
        for (int i = 0; i < n; i++) {

            System.out.println("\nEnter details for Customer " + (i + 1));

            System.out.print("ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Type (Residential/Commercial/Industrial): ");
            String type = sc.nextLine();

            System.out.print("Units Consumed: ");
            int units = sc.nextInt();
            sc.nextLine();

            if (type.equalsIgnoreCase("Residential")) {
                customers[i] = new ResidentialCustomer(id, name, units);
            } else if (type.equalsIgnoreCase("Commercial")) {
                customers[i] = new CommercialCustomer(id, name, units);
            } else {
                customers[i] = new IndustrialCustomer(id, name, units);
            }
        }

        /* -------- OUTPUT -------- */
        for (Customer c : customers) {

            if (c instanceof ResidentialCustomer)
                c.displayInfo("Residential");
            else if (c instanceof CommercialCustomer)
                c.displayInfo("Commercial");
            else
                c.displayInfo("Industrial");

            Bill bill = c.generateBill();  // Polymorphism
            bill.displayBill();
        }

        System.out.println("\n===============================================");
        System.out.println("Total Customers Processed: " + n);
        System.out.println("===============================================");

        sc.close();
    }
}