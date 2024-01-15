import java.util.Random;
import java.util.Scanner;

public class RunBranchAndBound {
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
    /*
    public static void main(String[] args) {
        
        Get cities from user using random or user input.
        Create the matrix by using the list given.        

        int adj[][] = new int[number of cities][number of cities];
        BranchAndBound.N = number of cities

        BranchAndBound.TSP(adj);

        System.out.printf("Optimal Path:");
        for (int i = 0; i <= BranchAndBound.N; i++) {
            System.out.printf("%d ", BranchAndBound.final_path[i]);
        }
    }
    */
}