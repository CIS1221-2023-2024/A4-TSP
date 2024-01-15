public class NearestNeighborTSP{
    
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
} 