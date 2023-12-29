public class BruteForceApproach {
    // Method to calculate the distance of going through every city.
    public static double calculateTourDistance(City[] cities, int[] permutation) {
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
    public static boolean nextPermutation(int[] array) {
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
}