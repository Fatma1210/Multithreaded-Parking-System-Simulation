package parking.os;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String fileLocation = "D:\\Fatma\\Collegue\\3rd Year\\CS\\1st Semester\\Operating Systems\\Assigments\\Parking-System-Simulation\\input.txt";
        File inputFile = new File(fileLocation);

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
                    Car car = new Car(gateNumber , carNumber , arrivalTime , parkingDuration);
                    car.getCarInfo();

                } catch (NumberFormatException e) {
                    System.out.println("Error parsing numbers in line: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileLocation);
        }
    }
}
