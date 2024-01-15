import java.util.Scanner;
import java.util.Random;

public class RunNearestNeighbor{
    //User input into coordinates (n)
    public static void userInput(City[] cities, Scanner sc) {
        for (int i = 0; i < cities.length; i++) {
            System.out.print("\nEnter x coordinate for city " + (i + 1) + ": ");
            int x = sc.nextInt();
            System.out.print("\nEnter y coordinate for city " + (i + 1) + ": ");
            int y = sc.nextInt();
            cities[i] = new City(x, y);
        }
    }

    //Making random coordinates for the random input (r)
    public static void randomInput(City[] cities, Random rn) {
        for (int i = 0; i < cities.length; i++) {
            cities[i] = new City(rn.nextInt(100), rn.nextInt(100));
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rn = new Random();
        
        //Getting the number of cities from the user
        int n = 0;
        //Loop just in the case that the user does not input a number
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
        
        long startTime = System.nanoTime();//The time before the calculation
        int[] tour = new int[n];//The order the cities are visited
        boolean[] visited = new boolean[n];//Cities that are already visited
        
        //Start from the first city
        tour[0] = 0;
        visited[0] = true;
        
        //Loop till reaching the last city
        for (int i = 1; i < n; i++) {
            int currentCity = tour[i - 1];
            //To determine the nearest unvisited city
            int nearestCity = NearestNeighborTSP.findNearestCity(currentCity, cities, visited);
            //Add the nearest unvisited city to the tour
            tour[i] = nearestCity;
            //Result stored in nearestCity
            visited[nearestCity] = true;
        }
        double tourDistance = NearestNeighborTSP.calculateTourDistance(cities, tour);
        
        long finalTime = System.nanoTime();
        
        System.out.println("Execution time: " + (finalTime - startTime) / 1000000);
        System.out.println("Shortest route:");
        
        for (int i = 0; i < tour.length; i++) {
            int cityIndex = tour[i];
            City city = cities[cityIndex];
            System.out.println("(" + city.getX() + ", " + city.getY() + ")");
        }
        
        System.out.println("Total Distance: " + tourDistance);
    }
}