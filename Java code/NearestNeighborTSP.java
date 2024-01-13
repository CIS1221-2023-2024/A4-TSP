import java.util.Random;
import java.util.Scanner;

public class NearestNeighborTSP{
    
    public static class City {
        private int x; //X coordinate
        private int y; //Y coordinate
        
        //Constructor
        City(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        //Getters and Setters
        void setX(int x) {
            this.x = x;
        }
        
        void setY(int y) {
            this.y = y;
        }
        
        int getX() {
            return x;
        }
        
        int getY() {
            return y;
        }
        
        //Method to calculate the distance between two cities.
        public static double calculateDistance(City a, City b) {
            return Math.sqrt(Math.pow(b.y - a.y, 2) + Math.pow(b.x - a.x, 2));
        }
    }
    
    //Method to calculate the distance of going through every city.
    public static double calculateTourDistance(City[] cities, int[] permutation) {
        double totalDistance = 0;
        
        //Going through each pair of consecutive cities in the permutation
        for (int i = 0; i < permutation.length - 1; i++) {
            int cityIndex1 = permutation[i];
            int cityIndex2 = permutation[i + 1];
            
            //Use the calculateDistance method from the City class to calculate the distance
            totalDistance += City.calculateDistance(cities[cityIndex1], cities[cityIndex2]);
        }
        
        //Add the distance from the last city back to the starting city
        totalDistance += City.calculateDistance(cities[permutation[permutation.length - 1]], cities[permutation[0]]);
        
        return totalDistance;
    }
    
    //Checking if a city has been visited.
    public static int findNearestCity(int currentCity, City[] cities, boolean[] visited) {
        int nearestCity = -1;//-1,since that no nearest city has been found yet.
        double minDistance = Double.MAX_VALUE;
        
        //For loop to go through all cities in the array
        for (int i = 0; i < cities.length; i++) {
            //Making sure the city isn't visited yet or the current one
            if (!visited[i] && i != currentCity) {
                double distance = City.calculateDistance(cities[currentCity], cities[i]);
                //If both conditions are true, it calculates the distance between the cities
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestCity = i;
                }
            }
        }
        
        return nearestCity;
    }
    
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
            int nearestCity = findNearestCity(currentCity, cities, visited);
            //Add the nearest unvisited city to the tour
            tour[i] = nearestCity;
            //Result stored in nearestCity
            visited[nearestCity] = true;
        }
        double tourDistance = calculateTourDistance(cities, tour);
        
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