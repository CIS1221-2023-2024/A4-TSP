import TSP_Project as tsp
import random as rng
import pandas as pd

def random_test(random_cities, method):
    """
    Parameters:
    - random_cities (list): List of cities represented as (x, y) coordinates.
    - method (function): algorithm to be tested.

    Returns:
    - list: List of tuples containing the number of cities, minimum distance, and execution time.
    """
    data = []
    #Varying the number of cities
    for num_cities in range(3, len(random_cities) + 1):
        cities = random_cities[:num_cities]
        results = tsp.measure_execution_time(method, cities)
        data.append((num_cities, round(results[0][1], 3), round(results[1], 3)))
    return data

#Generates random cities
#Up to 9 cities since Brute Force and Branch and Bround approaches take several minutes afterwards
cities = [(rng.randint(0,100), rng.randint(0,100)) for i in range(9)]
print("List of cities which will be tested: ",cities)

#Creates DataFrames for each algorithm
frame_bf = pd.DataFrame(data=random_test(cities, tsp.tsp_brute_force), columns=["NumCities", "MinDistBF", "TimeBF"])
frame_bb = pd.DataFrame(data=random_test(cities, tsp.branch_and_bound_tsp), columns=["NumCities", "MinDistBB", "TimeBB"])
frame_nn = pd.DataFrame(data=random_test(cities, tsp.nearest_neighbor_tsp), columns=["NumCities", "MinDistNN", "TimeNN"])


#Merges the DataFrames
final_frame = pd.merge(pd.merge(frame_bb, frame_bf), frame_nn)
print(final_frame)