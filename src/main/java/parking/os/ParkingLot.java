package parking.os;
import java.util.concurrent.Semaphore;
public class ParkingLot {
    private int numberOfSpots;
    private final Semaphore semaphore;
    public ParkingLot(Semaphore semaphore) {
        this.numberOfSpots = 4;
        this.semaphore = new Semaphore(numberOfSpots);
    }

    public boolean CheckAvailability() throws InterruptedException {
        if (semaphore.availablePermits() > 0) {
            semaphore.acquire();
            return true;
        } else {
           semaphore.acquire();
            return false;
        }
    }
    public void leave(){
        semaphore.release();
    }
    public int getNumberOfSpots(){
        return semaphore.availablePermits();
    }

}
