import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Configuration setup
        System.out.println("Enter total ticket capacity:");
        int maxCapacity = scanner.nextInt();

        System.out.println("Enter ticket release rate (in ms):");
        int ticketReleaseRate = scanner.nextInt();

        System.out.println("Enter customer retrieval rate (in ms):");
        int customerRetrievalRate = scanner.nextInt();

        // Initialize the ticket pool
        TicketPool ticketPool = new TicketPool(maxCapacity);

        // Create vendor and customer threads
        Thread vendorThread = new Thread(new Vendor(ticketPool, ticketReleaseRate));
        Thread customerThread = new Thread(new Customer(ticketPool, customerRetrievalRate));

        // Start threads
        System.out.println("Starting the system...");
        vendorThread.start();
        customerThread.start();

        // CLI to manage the system
        while (true) {
            System.out.println("\nCommands: \n1. Stop Vendor\n2. Stop Customer\n3. Exit");
            int choice = scanner.nextInt();

            if (choice == 1) {
                vendorThread.interrupt();
                System.out.println("Vendor stopped.");
            } else if (choice == 2) {
                customerThread.interrupt();
                System.out.println("Customer stopped.");
            } else if (choice == 3) {
                vendorThread.interrupt();
                customerThread.interrupt();
                System.out.println("System shutting down.");
                break;
            } else {
                System.out.println("Invalid command. Try again.");
            }
        }

        scanner.close();
    }
}
