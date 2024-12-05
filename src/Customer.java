public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final int customerRetrievalRate;

    public Customer(TicketPool ticketPool, int customerRetrievalRate) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public void run() {
        try {
            while (true) {
                ticketPool.removeTicket();
                Thread.sleep(customerRetrievalRate); // Simulate time between purchases
            }
        } catch (InterruptedException e) {
            System.out.println("Customer stopped.");
        }
    }
}
