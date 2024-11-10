package parking.os;
import java.util.concurrent.Semaphore;
public class ParkingLot {
    private int numberOfSpots;
    private final Semaphore semaphore;
    public ParkingLot() {
        this.numberOfSpots = 4; // default number of parking spots
        this.semaphore = new Semaphore(numberOfSpots);
    }

    public boolean CheckAvailability() throws InterruptedException {
        if (semaphore.availablePermits() > 0) {
            semaphore.acquire();
            return true;
        } else {
           return false;
        }
    }
    public long calculateWaitTime() throws InterruptedException {
        long startTime = System.nanoTime(); // Start time in nanoseconds
        semaphore.acquire(); // Block until a spot is available
        long endTime = System.nanoTime(); // End time in nanoseconds
        return (endTime - startTime) / 1000000; // Convert to milliseconds
    }

    public void leave(){
        semaphore.release();
    }
    public int getNumberOfSpots(){
        return semaphore.availablePermits();
    }

}
