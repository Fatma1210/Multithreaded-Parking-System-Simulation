package parking.os;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String fileLocation = "D:\\Fatma\\Collegue\\3rd Year\\CS\\1st Semester\\Operating Systems\\Assigments\\Multithreaded-Parking-System-Simulation\\Multithreaded-Parking-System-Simulation\\Parking-System-Simulation\\input.txt";
        File inputFile = new File(fileLocation) ;
        Scanner sc = new Scanner(inputFile) ;
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            Car car = new Car() ;
            int spaceCnt = 0 ;
            for(int i = 0 ; i < line.length() ; ++i){
                if(spaceCnt == 1 && line.charAt(i - 1) == ' '){
                    car.setGateNumber(Integer.parseInt(String.valueOf(line.charAt(i))));
                }
                else if(spaceCnt == 3 && line.charAt(i - 1) == ' '){
                    car.setCarNumber(Integer.parseInt(String.valueOf(line.charAt(i))));
                } else if (spaceCnt == 5 && line.charAt(i - 1) == ' ') {
                    car.setArrivalTime(Integer.parseInt(String.valueOf(line.charAt(i))));
                }
                else if(spaceCnt == 7 && line.charAt(i - 1) == ' '){
                    car.setParkingDuration(Integer.parseInt(String.valueOf(line.charAt(i))));
                }
                if(line.charAt(i) == ' ') spaceCnt++;
            }
            car.getCarInfo();
        }

    }
}