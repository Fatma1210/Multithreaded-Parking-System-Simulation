package parking.os;
import java.util.concurrent.Semaphore;
public class ParkingLot {
    private int numberOfSpots;
    private final Semaphore semaphore;
    public ParkingLot(Semaphore semaphore) {
        int numberOfSpots = 4;
        this.semaphore = new Semaphore(numberOfSpots);
    }

    public boolean CheckAvailability() throws InterruptedException {
        if (semaphore.availablePermits() > 0) {
            semaphore.acquire();
            System.out.print("Car has been parked successfully");
            return true;
        } else {
            System.out.println("No spots available");
            return false;
        }
    }
    public void leave(){
        semaphore.release();
        System.out.println("Car has left the parking lot");
    }

}
