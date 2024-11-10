package parking.os;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

import static parking.os.Car.printDetails;

public class Main {
    public static void main(String[] args) {
        String fileLocation = "C:\\Users\\DELL\\IdeaProjects\\Multithreaded-Parking-System-Simulation1\\input.txt";
        File inputFile = new File(fileLocation);

        ParkingLot parkingLot = new ParkingLot(); // No need to pass semaphore here

        List<Car> cars = new ArrayList<>();

        try (Scanner sc = new Scanner(inputFile)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                String[] parts = line.replaceAll("[^0-9 ]", "").trim().split("\\s+");

                if (parts.length < 4) {
                    System.out.println("Invalid input format: " + line);
                    continue;
                }

                try {
                    int gateNumber = Integer.parseInt(parts[0]);
                    int carNumber = Integer.parseInt(parts[1]);
                    int arrivalTime = Integer.parseInt(parts[2]);
                    int parkingDuration = Integer.parseInt(parts[3]);

                    Car car = new Car(gateNumber, carNumber, arrivalTime, parkingDuration, parkingLot);
                    cars.add(car);
                    car.start();

                } catch (NumberFormatException e) {
                    System.out.println("Error parsing numbers in line: " + line);
                }
            }

            for (Car car : cars) {
                car.join();
            }

            Car.printDetails(parkingLot);

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileLocation);
        } catch (InterruptedException e) {
            System.out.println("Main thread was interrupted.");
        }
    }
}
