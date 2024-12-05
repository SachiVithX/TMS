import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private final Queue<String> tickets;
    private final int maxCapacity;

    public TicketPool(int maxCapacity) {
        this.tickets = new LinkedList<>();
        this.maxCapacity = maxCapacity;
    }

    // Add tickets to the pool
    public synchronized void addTicket(String ticket) throws InterruptedException {
        while (tickets.size() >= maxCapacity) {
            wait(); // Wait if the pool is full
        }
        tickets.add(ticket);
        System.out.println("Ticket Added: " + ticket + " | Current Tickets: " + tickets.size());
        notifyAll(); // Notify consumers that tickets are available
    }

    // Remove tickets from the pool
    public synchronized String removeTicket() throws InterruptedException {
        while (tickets.isEmpty()) {
            wait(); // Wait if the pool is empty
        }
        String ticket = tickets.poll();
        System.out.println("Ticket Purchased: " + ticket + " | Remaining Tickets: " + tickets.size());
        notifyAll(); // Notify producers that space is available
        return ticket;
    }
}
