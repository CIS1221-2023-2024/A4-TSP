import java.util.Arrays;
public class BruteForceApproach {
   public static class City {
        private int x; // X coordinate
        private int y; // Y coordinate

        // Constructor
        City(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // Getters and Setters
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

        // Method to calculate the distance between two cities.
        public static double calculateDistance(City a, City b) {
            return Math.sqrt(Math.pow(b.y - a.y, 2) + Math.pow(b.x - a.x, 2));
        }
    }

    // Method to calculate the distance of going through every city.
    private static double calculateTourDistance(City[] cities, int[] permutation) {
        double totalDistance = 0;

        // going through each pair of consecutive cities in the permutation
        for (int i = 0; i < permutation.length - 1; i++) {
            int cityIndex1 = permutation[i];
            int cityIndex2 = permutation[i + 1];

            // Use the calculateDistance method from the City class to calculate the distance
            totalDistance += City.calculateDistance(cities[cityIndex1], cities[cityIndex2]);
        }

        // Add the distance from the last city back to the starting city
        totalDistance += City.calculateDistance(cities[permutation[permutation.length - 1]], cities[permutation[0]]);

        return totalDistance;
    }

    // Method to generate the next permutation
    private static boolean nextPermutation(int[] array) {
        //finding the largest permutation
        int i = array.length - 1;
        while (i > 0 && array[i - 1] >= array[i]) {
            i--;
        }
        if (i <= 0) {
            return false;
        }
        
        // to find the second largest
        int j = array.length - 1;
        while (array[j] <= array[i - 1]) {
            j--;
        }

        // Swapping the array[i-1] and array[j] to get it to the order of smallest to largest
        int temp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = temp;

        // Checking if the order of the array is correct
        j = array.length - 1;
        while (i < j) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }

        return true;
    }

    private static City[] getBestCycle(City[] cities, int[] permutation) {
        City[] cycle = new City[cities.length];
        for (int i = 0; i < permutation.length; i++) {
            cycle[i] = cities[permutation[i]];
        }
        return cycle;
    }

    public static void main(String[] args) {
        // city coordinates
        City[] cities = {
                new City(0, 0),
                new City(1, 2),
                new City(2, 4),
                new City(2, 3)
        };

        int n = cities.length; // number of cities
        int[] permutation = new int[n];
        for (int i = 0; i < n; i++) {
            permutation[i] = i;
        }

        double minDistance = Double.MAX_VALUE;
        int[] bestPermutation = null;

        do {
            double tourDistance = calculateTourDistance(cities, permutation);
            if (tourDistance < minDistance) {
                minDistance = tourDistance;
                bestPermutation = Arrays.copyOf(permutation, permutation.length);//getting each permutation
            }
        } while (nextPermutation(permutation));

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
