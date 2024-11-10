package parking.os;

import java.util.concurrent.Semaphore;

public class Car extends Thread {
    private int gateNumber;
    private int carNumber;
    private int arrivalTime;
    private int parkingDuration;
    private ParkingLot parkingLot;
    private static int totalCarsServed = 0;
    private static int currentCarsInParking = 0;
    private static int Gate1Cars;
    private static int Gate2Cars;
    private static int Gate3Cars;


    public Car(int gateNumber, int carNumber, int arrivalTime, int parkingDuration, ParkingLot parkingLot) {
        this.gateNumber = gateNumber;
        this.carNumber = carNumber;
        this.arrivalTime = arrivalTime;
        this.parkingDuration = parkingDuration;
        this.parkingLot = parkingLot;
    }
    public synchronized void incrementCarNumForEachGate(int gateNumber) {
        if(gateNumber ==1){
            Gate1Cars++;
        }
        else if(gateNumber ==2){
            Gate2Cars++;
        }
        else if(gateNumber ==3){
            Gate3Cars++;
        }
    }

    @Override
    public void run() {
        try {
            // Simulate arrival time
            Thread.sleep(arrivalTime * 1000L);  // Sleep for arrival time in seconds

            System.out.println("Car " + carNumber + " from Gate " + gateNumber + " arrived at time " + arrivalTime);

            // Try to park
            if (parkingLot.CheckAvailability()) {
                synchronized (this) {
                    currentCarsInParking++;
                    System.out.println("Car " + carNumber + " from Gate " + gateNumber + " parked. (Parking Status: " + currentCarsInParking + " spots occupied)");
                }

                // Simulate parking duration
                Thread.sleep(parkingDuration * 1000L);  // Sleep for parking duration in seconds

                // Leaving the parking lot
                synchronized (this) {
                    currentCarsInParking--;
                    System.out.println("Car " + carNumber + " from Gate " + gateNumber + " left after " + parkingDuration + " units of time. (Parking Status: " + currentCarsInParking + " spots occupied)");
                    totalCarsServed++;
                    incrementCarNumForEachGate(gateNumber);
                }

                // Release the parking spot
                parkingLot.leave();
            } else {
                System.out.println("Car " + carNumber + " from Gate " + gateNumber + " waiting for a spot.");

                // Calculate wait time until a spot becomes available
                long waitTime = parkingLot.calculateWaitTime();

                synchronized (this) {
                    currentCarsInParking++;
                    System.out.println("Car " + carNumber + " from Gate " + gateNumber + " parked after waiting for " + waitTime + " units of time. (Parking Status: " + currentCarsInParking + " spots occupied)");
                }

                // Simulate parking duration
                Thread.sleep(parkingDuration * 1000L);  // Sleep for parking duration in seconds

                // Leaving the parking lot
                synchronized (this) {
                    currentCarsInParking--;
                    System.out.println("Car " + carNumber + " from Gate " + gateNumber + " left after " + parkingDuration + " units of time. (Parking Status: " + currentCarsInParking + " spots occupied)");
                    totalCarsServed++;
                    incrementCarNumForEachGate(gateNumber);
                }

                // Release the parking spot
                parkingLot.leave();
            }
        } catch (InterruptedException e) {
            System.out.println("Car " + carNumber + " was interrupted.");
        }
    }

    public static void printDetails(ParkingLot parkingLot) {
        System.out.println("Total Cars Served: " + totalCarsServed);
        System.out.println("Current Cars in Parking: " + currentCarsInParking);
        System.out.println("Details: ");
        System.out.println("- Gate 1 served " + Gate1Cars+ " cars.");
        System.out.println("- Gate 2 served " + Gate2Cars  + " cars.");
        System.out.println("- Gate 3 served " + Gate3Cars  + " cars.");
    }
}
