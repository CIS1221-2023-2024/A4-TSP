Travelling Salesman Problem (TSP):

The objective of the Travelling Salesman Problem is to determine the shortest path that visits each city once and returns to the starting point within a given collection of citiesand the distances between each pair.
The Travelling Salesman Problem or TSP as it is generally known today appears to have been first studied by mathematicians starting in the 1930s by Karl Menger in Vienna and Harvard. 
Despite the complexity of the problem, the TSP still has its applications in many industries. An example of this is: The logistics industry. 
TSP solutions may increase last-mile efficiency in the logistics sector, this can be done by using the TSP as a kind of Vehicle Routing Problem or VRP.
A last mile delivery occurs when items are dispatched from a transportation hub to the final client. Another use for the TSP is the Google Maps route planner.
Delivery companies may easily and efficiently plan routes through the use of near-optimal solutions found in a fraction of the time using real-world TSP and VRP solvers, 
which employ route optimisation algorithms.

There are many ways to solve the Travelling Salesman Problem, 3 main methods include:

#1. The brute-force approach:
The Brute Force approach, also known as the Naïve approach finds the shortest unique solution by processing and comparing every possible combination of routes or paths. 
In order to solve the TSP using the Brute-Force method the total number of routes needs to be calculated, then those routes need to be drawn and listed. 
The most effective way to do this is to determine the distance of each route and then choose the shortest one. 
However, this is only effective for simple problems and is rarely helpful. 

#2. The branch and bound method:
The branch and bound method begins when a starting route is created, from the starting route the starting point in a collection of cities is added. 
The path is gradually extended one paint at a time, by carefully analysing various combinations. 
The algorithm then determines the length of the current route and compares it to the best route discovered thus far,every time a new point is added. 
If the present route takes longer than the ideal route, it "bounds" or prunes that branch of the exploration because it will not result in a better answer. 
The efficiency of the algorithm is largely dependent on this pruning. The search space is reduced by eliminating undesirable paths, 
allowing the algorithm to concentrate on investigating only the most promising ones. This procedure keeps going until every path is investigated and the shortest one is determined
to be the best way to solve the traveling salesman problem. 
Branch and bound is an effective greedy approach for tackling NP-hard optimization problems like the travelling salesman problem.


#3. The nearest neighbour method:
The Nearest Neighbour method is implemented by beginning at a randomly chosen starting point, the next unexplored route is then located and added to the sequence.
Once every route is included in the algorithm, the process of locating the closest unexplored route is continued. To finish the loop, the algorithm goes back to the starting point. 
The Nearest Neighbour Method is not always the best way to solve the TSP. Because, in massive and complicated cases its results tend to be longer than the best path. 
However, when a fast and fairly accurate solution is required for a fairly sized data set, the Nearest Neighbour algorithm is usable when attempting to solve the travelling salesman problem.
This algorithm can be used efficiently to quickly produce an acceptable starting solution, which can then be used into a more complex search algorithm to improve the answer 
until an established stopping point is achieved. 




Sources:
https://www.routific.com/blog/travelling-salesman-problem#:~:text=The%20Traveling%20Salesman%20Problem%20(TSP,and%20optionally%20an%20ending%20point.
https://www.geeksforgeeks.org/travelling-salesman-problem-using-dynamic-programming/
