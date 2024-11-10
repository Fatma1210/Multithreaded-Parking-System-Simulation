package parking.os;

public class Car {
    private int gateNumber ;
    private int carNumber ;
    private int arrivalTime;
    private int parkingDuration ;

   public Car(int gateNumber , int carNumber , int arrivalTime , int parkingDuration){
       this.gateNumber = gateNumber ;
       this.carNumber = carNumber ;
       this.arrivalTime = arrivalTime ;
       this.parkingDuration = parkingDuration ;
   }
    public void getCarInfo (){
        System.out.println("Gate Number: " + gateNumber);
        System.out.println("Car Number: " + carNumber);
        System.out.println("Arrival Time: " + arrivalTime);
        System.out.println("Parking duration: " + parkingDuration);

    }
}
