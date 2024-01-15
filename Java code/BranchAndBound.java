import java.util.*;

public class BranchAndBound{
//This code uses the City class from the  
    static int N = 0;//Number of cities
    static int final_path[];//Array for the final solution
    static boolean visited[];
    
    //Function to copy temporary a solution to the final solution
    static void copyToFinal(int curr_path[]) {//Copies the current path to the final path array, including the starting city at the end
         for (int i = 0; i < N; i++)
            final_path[i] = curr_path[i];
        final_path[N] = curr_path[0];
    }
    
    static void TSPRec(int adj[][], int level, int curr_path[]) {//Recursive function to explore the solution space and find a valid TSP path
        if (level == N) {//Base case
            copyToFinal(curr_path);
            return;
        }
    
        for (int i = 0; i < N; i++) {
            if (adj[curr_path[level - 1]][i] != 0 && !visited[i]) {//To check till the edge cases
                curr_path[level] = i;
                visited[i] = true;
                TSPRec(adj, level + 1, curr_path);
                visited[i] = false;//Backtrack
            }
        }
    }
    
    static void TSP(int adj[][]) {//set up initial conditions, and use the recursive function
        int curr_path[] = new int[N + 1];
        Arrays.fill(curr_path, -1);
        Arrays.fill(visited, false);
        
        visited[0] = true;
        curr_path[0] = 0;
        
        TSPRec(adj, 1, curr_path);
    }
    
    //User input into coordinates (n)
    public static void userInput(City[] cities, Scanner sc) {
        for (int i = 0; i < cities.length; i++) {
            System.out.print("\nEnter x coordinate for city " + (i + 1) + ": ");
            int x = sc.nextInt();
            System.out.print("Enter y coordinate for city " + (i + 1) + ": ");
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
        
        int n = 0;
        while (true) {
            System.out.print("Enter the number of cities: ");
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                break;
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                sc.next();
            }
        }
        
        N = n;
        final_path = new int[N + 1];
        visited = new boolean[N];
        City[] cities = new City[N];
        
        //Choosing between random input and user input
        System.out.print("Enter 'r' for random input and 'u' for user input: ");
        String choice;
        
        while (true) {
            choice = sc.next().toLowerCase();
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
        long endTime = System.nanoTime();
        int adj[][] = new int[N][N];
        
        //Fill the adjacency matrix based on the city coordinates
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i != j) {
                    adj[i][j] = (int) City.calculateDistance(cities[i], cities[j]);
                }
            }
        }
        
        TSP(adj);
        
        System.out.println("Optimal Path:");
        for (int i = 0; i <= N; i++) {
            int cityIndex = final_path[i];//%n to go to the next line.
            System.out.printf("City %d (%d, %d)%n", cityIndex + 1, cities[cityIndex].getX(), cities[cityIndex].getY());
        }
        
        long executionTime = (endTime - startTime) / 10000000;
        System.out.println("\nExecution time: " + executionTime);
        
        sc.close();
    }
}
