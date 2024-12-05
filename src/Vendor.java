public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;
    private static int ticketCounter = 1; // Static to ensure unique ticket numbers

    public Vendor(TicketPool ticketPool, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String ticket = "Ticket-" + ticketCounter++;
                ticketPool.addTicket(ticket);
                Thread.sleep(ticketReleaseRate); // Simulate time taken to release tickets
            }
        } catch (InterruptedException e) {
            System.out.println("Vendor stopped.");
        }
    }
}
