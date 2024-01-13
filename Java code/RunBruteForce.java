import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class RunBruteForce{
    static Scanner sc = new Scanner(System.in);
    static Random rn = new Random(); 

    private static void userInput(City[] cities){
        for(int i = 0; i < cities.length; i++){
            System.out.print("\nEnter x coordinate: ");
            int x = sc.nextInt();
            System.out.print("\nEnter y coordinate: ");
            int y = sc.nextInt();
            cities[i] = new City(x,y);
        }
    }

    private static void randomInput(City[] cities){
        for(int i = 0; i < cities.length; i++){
            cities[i] = new City(rn.nextInt(100),rn.nextInt(100));
        }
    }
    public static void main(String[] args) {

        //Getting the number of cities from user
        System.out.print("Enter the number of cities: ");
        int n = sc.nextInt();
        City[] cities = new City[n];
        //Choosing between random and user input
        System.out.print("Enter 'r' for random input and 'u' for user input: ");
        String choice = sc.next();
        if(choice.equals("r")){
            randomInput(cities);
        }else if(choice.equals("u")){
            userInput(cities);
        }

        long StartTime = System.nanoTime();

        int[] permutation = new int[n];
        for (int i = 0; i < n; i++) {
            permutation[i] = i;
        }

        double minDistance = Double.MAX_VALUE;
        int[] bestPermutation = null;

        int calculationTime = 0;
        do {
            calculationTime -= System.nanoTime();
            double tourDistance = BruteForceApproach.calculateTourDistance(cities, permutation);
            calculationTime += System.nanoTime();
            if (tourDistance < minDistance) {
                minDistance = tourDistance;
                bestPermutation = Arrays.copyOf(permutation, permutation.length);//getting each permutation
            }
        } while (BruteForceApproach.nextPermutation(permutation));

        long FinalTime = System.nanoTime();
        System.out.println("Execution time: "+(FinalTime-StartTime)/1000000);
        System.out.println("Calculation time: "+calculationTime/1000000);

        // Output the best route
        System.out.println("Shortest route:");

        for (int i = 0; i < bestPermutation.length; i++) {
            int cityIndex = bestPermutation[i];
            City city = cities[cityIndex];
            System.out.println("(" + city.getX() + ", " + city.getY() + ")");
        }

        // Output the total distance of the best route
        System.out.println("Total Distance: " + minDistance);
    }
}