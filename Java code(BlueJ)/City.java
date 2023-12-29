public class City{
    private int x; //X coordinate
    private int y; //Y coordinate
    
    //Constructor
    City(int x, int y){
        this.x = x;
        this.y = y;
    }

    //Getters and Setters
    void setX(int x){
        this.x = x;
    }
    void setY(int y){
        this.y = y;
    }
    int getX(){
        return x;
    }
    int getY(){
        return y;
    }
    
    //Method to calculate the distance between two cities.
    public static double calculateDistance(City a, City b){
        return Math.sqrt(Math.pow(b.y-a.y,2)+Math.pow(b.x-a.x,2));
    }
}