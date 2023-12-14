public class BruteForceApproach {
    public static void main(String[] args) {
        int[] A_To_B = {2, 3, 4, 5, 6, 7, 8};//The different distances from one place to another
        int[] A_To_C = {15, 14, 13, 12, 11, 10, 9};
        int[] B_To_C = {0, 22, 21, 17, 20, -5, 19};

        int n = A_To_B.length;

        int optimal_Route_A_To_B = Integer.MAX_VALUE;//Integer.Max.Value to find the minimum from the array
        int optimal_Route_A_To_C = Integer.MAX_VALUE;
        int optimal_Route_B_To_C = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            // To find the optimal route for each case
            if (A_To_B[i] > 0 && A_To_B[i] < optimal_Route_A_To_B) {
                optimal_Route_A_To_B = A_To_B[i];
            }

            if (A_To_C[i] > 0 && A_To_C[i] < optimal_Route_A_To_C) {
                optimal_Route_A_To_C = A_To_C[i];
            }

            if (B_To_C[i] > 0 &&  B_To_C[i] < optimal_Route_B_To_C) {
                optimal_Route_B_To_C = B_To_C[i];
            }
        }

        System.out.println("Optimal Route for A to B is: " + optimal_Route_A_To_B);// To show the user the shortest route
        System.out.println("Optimal Route for A to C is: " + optimal_Route_A_To_C);
        System.out.println("Optimal Route for B to C is: " + optimal_Route_B_To_C);
    }
}