import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class RunBruteForce{
    Scanner sc = new Scanner(System.in);
        Random rn = new Random();
        
        //Getting the number of cities from the user
        int n = 0;
        //loop just in the case that the user does not input a number
        while (true) {
            System.out.print("Enter the number of cities: ");
            //Check if the next input is an integer
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                break; //Exit the loop when a valid integer is entered
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                sc.next(); //Clear the invalid input
            }
       }
    
        City[] cities = new City[n];
        
             //Choosing between random input and user input
             System.out.print("Enter 'r' for random input and 'u' for user input: ");
             String choice;
        
             //In the case that the user does not input 'r' or 'u'
             while (true) {
                 choice = sc.next().toLowerCase();//To allow uppercase inputs (R and N) 
                if (choice.equals("r") || choice.equals("u")) {  
                  break;
            }
            System.out.print("Invalid input. Enter 'r' for random input and 'u' for user input: ");
        }
        
        if (choice.equals("r")) {
            randomInput(cities, rn);
        } else if (choice.equals("u")) {
            userInput(cities, sc);
        }
        
        long startTime = System.nanoTime();
        
        int[] permutation = new int[n];
        for (int i = 0; i < n; i++) {
            permutation[i] = i;
        }
        
        //The Bruteforce search
        double minDistance = Double.MAX_VALUE;
        int[] bestPermutation = null;
        
        long calculationTime = 0;//long to measure time
        //The time before the Brute Force calculation
        long startTimeCalculation = System.nanoTime();
        do {
            
            
            //Calculating the total distance for the current TSP permutation
            double tourDistance = calculateTourDistance(cities, permutation);
            
            //The time after TSP calculation
            long endTimeCalculation = System.nanoTime();
            calculationTime += (endTimeCalculation - startTimeCalculation);
            //Checking if the current permutation has a shorter distance than the previous one
            if (tourDistance < minDistance) {
                //Updating the minDistance
                minDistance = tourDistance;
                bestPermutation = Arrays.copyOf(permutation, permutation.length);
            }
        } while (nextPermutation(permutation));
        
        // Measure of the final execution time
        long finalTime = System.nanoTime();

        // Output of the execution time and calculation time
        System.out.println("Execution time: " + (finalTime - startTime) / 1000000);
        System.out.println("Calculation time: " + calculationTime / 1000000);
        
        //Output the best route
        System.out.println("Shortest route:");
        
        //Loop to show each coordinate
        for (int i = 0; i < bestPermutation.length; i++) {
            int cityIndex = bestPermutation[i];
            City city = cities[cityIndex];
            System.out.println("(" + city.getX() + ", " + city.getY() + ")");
        }
        
        //Output the total distance of the best route
        System.out.println("Total Distance: " + minDistance);
    }
}
