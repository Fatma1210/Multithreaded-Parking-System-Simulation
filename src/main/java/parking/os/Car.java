package parking.os;

public class Car extends Thread {
    private int gateNumber ;
    private int carNumber ;
    private int arrivalTime;
    private int parkingDuration ;
    private ParkingLot parkingLot ;

    public Car(int gateNumber , int carNumber , int arrivalTime , int parkingDuration,ParkingLot parkingLot) {
       this.gateNumber = gateNumber ;
       this.carNumber = carNumber ;
       this.arrivalTime = arrivalTime ;
       this.parkingDuration = parkingDuration ;
       this.parkingLot = parkingLot ;
   }
    public void getCarInfo (){
        System.out.println("Gate Number: " + gateNumber);
        System.out.println("Car Number: " + carNumber);
        System.out.println("Arrival Time: " + arrivalTime);
        System.out.println("Parking duration: " + parkingDuration);

    }

    @Override
    public void run() {
        try{
            Thread.sleep(arrivalTime*1000);
            System.out.println("Car " + carNumber + " from Gate " + gateNumber + " arrived at time " + arrivalTime);
            long waitStartTime = System.nanoTime();
            boolean waitFlag= false;
            if((4 - parkingLot.getNumberOfSpots())==4) {
                System.out.println("Car " + carNumber + " from Gate " + gateNumber + " waiting for a spot.");
                waitFlag = true;
            }
            long waitEndTime=0;
            while (!parkingLot.CheckAvailability()){
                waitEndTime = System.nanoTime();
            }
            long waitedTime =  ((waitEndTime - waitStartTime) / 1000000000);
            if(waitFlag){
                System.out.println("Car " + carNumber + " from Gate " + gateNumber + " parked after waiting for "
                        + waitedTime + " units of time. (Parking Status: " + (4 - parkingLot.getNumberOfSpots()) + " spots occupied)");
            }
            else{
                System.out.println("Car " + carNumber + " from Gate " + gateNumber + " parked. "
                        + "(Parking Status: " + (4 - parkingLot.getNumberOfSpots()) + " spots occupied)");
            }
            Thread.sleep(parkingDuration*1000);
            parkingLot.leave();
            System.out.println("Car " + carNumber + " from Gate " + gateNumber + " left after " + parkingDuration
                    + " units of time. (Parking Status: " + (4 - parkingLot.getNumberOfSpots()) + " spots occupied)");
        }catch (InterruptedException e){
            System.out.println("Car " + carNumber + " was interrupted.");
        }
    }
}
