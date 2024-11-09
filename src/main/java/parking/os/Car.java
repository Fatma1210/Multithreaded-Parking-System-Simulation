package parking.os;

public class Car {
    private int gateNumber ;
    private int carNumber ;
    private int arrivalTime;
    private int parkingDuration ;

    public void setCarNumber(int carNumber) {
        this.carNumber = carNumber;
    }

    public void setGateNumber(int gateNumber) {
        this.gateNumber = gateNumber;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setParkingDuration(int parkingDuration) {
        this.parkingDuration = parkingDuration;
    }
    public void getCarInfo (){
        System.out.println("Gate Number: " + gateNumber);
        System.out.println("Car Number: " + carNumber);
        System.out.println("Arrival Time: " + arrivalTime);
        System.out.println("Parking duration: " + parkingDuration);

    }
}
