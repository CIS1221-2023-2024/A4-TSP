public class BruteForceApproach {
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
    
    // Method to generate the next permutation
    public static boolean nextPermutation(int[] array) {
        int i = array.length - 1;
        //Finding the most right element that is smaller than the element to its right
        while (i > 0 && array[i - 1] >= array[i]) {
            i--;
        }
        
        if (i <= 0) {
            return false;//The last permutation is found
        }
        
        //Finding the second smallest
        int j = array.length - 1;
        while (array[j] <= array[i - 1]) {
            j--;
        }
        
        //Swapping the array[i-1] and array[j] to get the coordinates in ascending order for the while loop
        int temp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = temp;
        
        
        //The last element of the array
        j = array.length - 1;
        //loop: keeps swapping till i is smaller than j
        while (i < j) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            //moving i to the downwards(for the example to the right)
            i++;
            //moving j  to the upwards(for the example to the left)
            j--;
        }
        //Example: (0,0),(1,2),(2,4),(2,3) will become (1,2),(0,0),(2,4),(2,3)
        return true;
    }
}