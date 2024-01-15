import java.util.*;

public class BranchAndBound {
    static int N = 0;//Number of cities
    static int final_path[] = new int[N + 1];//Array for final solution
    static boolean visited[] = new boolean[N];//To not go to the same city twice
    
    // Function to copy temporary a solution to the final solution
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
                visited[i] = false; // Backtrack
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
}