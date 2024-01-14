# A4-TSP Challenger's Report

## Project Overview

**Project Name**: A4-TSP

**Date of Review**: 13/01/2024

**Challenger**: Giorgio Bellia

The A4-TSP is a project that focuses on algorithms to solve the Travelling Salesman Problem, these algorithms are explored using Python and Java. The Travelling SalesMan Problem is an [NP-Hard](https://en.wikipedia.org/wiki/NP-hardness) problem which scope is to find the shortest path to go through a set of cities and going back to the original one.
In this report, I critically review the project considering three main aspects: [Project Structure](#project-structure), [code quality](#code-quality) and [functionality](#functionality). Also, I provide recommendations for future enhancements and [personal proposals](#code-changes). At the end of the report I also attached a [to-do list](#to-do-list) for the team members.


### Project Structure

The repository structure is basic and easy to understand. There are two files, one for each programming language. The README file must be improved. However, the Wiki is highly detailed, it contains an explanation of the nature of TSP and also details on each algorithm used. 

Some recommendations for improvement, in the Java directory any file that has to do with compiltation should be removed. (i.e. leave only .java files).
The README should have clear instructions on how to execute and use each algorithm. Therefore, provide instructions for the command line execution. 
I propose to change your code so that the program first asks for the number of cities to enter and then for the coordinates of the cities. [Here](#user-input) is my code. 

### Code Quality
- ***Python***
In this script, the problem is solved using functional programming. Each algorithm is implemented by a function and it is well structured. In addition, there are utility functions to enhance the program and to avoid redundant code. I propose to remove [these](#remove) lines of code so that the script is entirely functional and instead use the program by calling functions. Furthermore, I think the code could benefit from more comments, especially in those parts where mathematical computations are happening.

- ***Java***
In this implementation, each algorithm is represented by functions in a class(object) which should then be used by a runner. However, this is the case only for the brute force approach. Apart from that, each class is divided into well structured functions enhancing readability and comprehension, which should be improved by adding more comments. Finally,  the object class City must not be inserted in each algorithm class but rather used by creating references.

### Functionality 
- ***Python***
In order to test the functionality of the code, I used an [online Tsp solver](https://www.lancaster.ac.uk/fas/psych/software/TSP/TSP.html) and compared the script's output with the online solver's one. The Brute Force algorithm and the Branch and Bound algorithm worked accurately. However, for both algorithm ten cities is the maximum feasible input; after that, computation time takes from 15 minutes onward. On the other hand, the Nearest Neighbour algorithm, although at the expense of accuracy, can compute thousands of cities in few seconds.
Here is the table showing the results given the following list [(57, 30), (41, 32), (76, 73), (31, 60), (51, 51), (26, 87), (46, 42), (34, 56), (59, 67), (80, 91)]:

| NumCities | MinDistBB | TimeBB   | MinDistBF | TimeBF   | MinDistNN | TimeNN |
|-----------|-----------|----------|-----------|----------|-----------|--------|
| 3         | 117.042   | 0.000    | 117.042   | 0.000    | 117.042   | 0.0    |
| 4         | 139.707   | 0.000    | 139.707   | 0.003    | 139.707   | 0.0    |
| 5         | 147.839   | 0.000    | 147.839   | 0.006    | 153.378   | 0.0    |
| 6         | 180.381   | 0.032    | 180.381   | 0.044    | 185.920   | 0.0    |
| 7         | 185.115   | 0.262    | 185.115   | 0.345    | 185.925   | 0.0    |
| 8         | 185.268   | 2.007    | 185.268   | 3.044    | 186.713   | 0.0    |
| 9         | 187.883   | 18.835   | 187.883   | 23.635   | 191.406   | 0.0    |
| 10        | 208.547   | 338.011  | 208.547   | 246.760  | 228.026   | 0.0    |


- ***Java*** 
Currently, it can not be tested since the Branch and Bound algorithm make use of the matrix and no information on how to retrieve the matrix is given.
## To-do List
Here is a list to summarise the tasks to do, some of this tasks were tackled by me in my branch. Which I will merge to the main once you tell me you agree with my changes.
- [ ] Add execution and use instructions on the README
- [ ] Add more detailed comments
- [ ] Fixes on code quality
- [ ] Create runners for the java classes
- [ ] Modify the Branch and Bound java algorithm such that the matrix is not calculated by the user

## Code Changes
#### User Input 

```python
cities  =  list()
#Flag for the input
flagInt  =  False
#While input is not an integer
while(not  flagInt):
	try:
		#Number of cities
		n  =  int(input("Enter the number of cities"))
		for  i  in  range(n):
			#Adding a tuple (x value, y value)
			cities.append((int(input("X value")),int(input("Y value"))))
		#If no exception is raised, inputs were all integers
		flagInt  =  True
	#If an exception is raised flag is set to false and an error message is printed
	except  ValueError:
		flagInt  =  False
		print("Enter an integer") 
```

```java
import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //Flag for the input
        boolean flagInt = false;
        //While input is not an integer
        while(!flagInt){
            try{
                System.out.print("Enter the number of cities: ");
                //Creates and array of size given by the user
                City[] cities = new City[sc.nextInt()];
                for (int i=0; i<cities.length ; i++){
                    System.out.println("\nEnter the x and y values: ");
                    //Adds a new city with x,y values from the user
                    cities[i] = new City(sc.nextInt(),sc.nextInt());    
                }
                //If no exception is caught, inputs were integers
                flagInt = true;
            }catch(Exception e){
                //If an exception is caught, some input was not an integer
                flagInt = false;
                System.out.println("Enter an integer");
                sc.next();
            }
        }
    }
}
```
#### Remove 
```python
result_brute_force, time_brute_force = measure_execution_time(tsp_brute_force, cities)
result_branch_bound, time_branch_bound = measure_execution_time(branch_and_bound_tsp, cities)
result_nearest_neighbor, time_nearest_neighbor = measure_execution_time(nearest_neighbor_tsp, cities)

#Tuple unpacking
best_order_brute_force, min_distance_brute_force = result_brute_force
optimal_order_branch_bound, minimum_distance_branch_bound = result_branch_bound
tour_nearest_neighbor = result_nearest_neighbor

print(f"Using the Brute Force Method:\nBest Order: {best_order_brute_force}")
print(f"Minimum Distance: {min_distance_brute_force}")
print(f"Execution Time: {time_brute_force} seconds\n")

print(f"Using the Branch and Bound Method:\nBest Order: {optimal_order_branch_bound}")
print(f"Minimum Distance: {minimum_distance_branch_bound}")
print(f"Execution Time: {time_branch_bound} seconds\n")

print(f"Using the Nearest Neighbor Method:\nBest Order: {tour_nearest_neighbor}")
print(f"Total Distance: {sum(np.linalg.norm(np.array(tour_nearest_neighbor[i]) - np.array(tour_nearest_neighbor[i + 1])) for i in range(len(tour_nearest_neighbor) - 1))}")
print(f"Execution Time: {time_nearest_neighbor} seconds")
```
