from itertools import permutations
import numpy as np
import time

cities = [(0, 0), (10, 10), (15, 5), (20, 0), (16,5), (12,23), (26,1), (18, 13)]  # City coordinates in the form (x,y)

def calculate_total_distance(order, cities): # Function to calculate the total distance of a tour
    total_distance = 0
    for i in range(len(order) - 1):
        total_distance += np.linalg.norm(np.array(cities[order[i]]) - np.array(cities[order[i + 1]])) # Calculate the Euclidean distance between two cities
    total_distance += np.linalg.norm(
        np.array(cities[order[-1]]) - np.array(cities[order[0]]))  # Return to the starting city
    return total_distance


def tsp_brute_force(cities):
    num_cities = len(cities)
    city_indices = list(range(num_cities))
    min_distance = float('inf')
    best_order = None

    for perm in permutations(city_indices): # Generate all possible permutations of city indices
        current_distance = calculate_total_distance(perm, cities)

        if current_distance < min_distance: # Update the best order if a shorter distance is found
            min_distance = current_distance
            best_order = perm

    return best_order, min_distance
#----- Brute force end _________


#______ Branch and Bound Start ---------
def reduce_matrix(matrix):

    for i in range(matrix.shape[0]):  # Reduce rows
        min_val = np.min(matrix[i, :])
        if min_val != np.inf:
            matrix[i, :] -= min_val

    for j in range(matrix.shape[1]): # Reduce columns
        min_val = np.min(matrix[:, j])
        if min_val != np.inf:
            matrix[:, j] -= min_val


def branch_and_bound_tsp(cities):
    num_cities = len(cities)

    distances = np.array( # Calculate the distances between cities
        [[np.linalg.norm(np.array(cities[i]) - np.array(cities[j])) for j in range(num_cities)] for i in
         range(num_cities)])

    reduced_matrix = np.copy(distances)
    reduce_matrix(reduced_matrix)

    root_node = { # Initialize the root node
        'order': [],
        'reduced_matrix': reduced_matrix,
        'bound': 0,
    }

    node_queue = [root_node] # Priority queue to store nodes
    minimum_distance = float('inf')
    optimal_order = None

    while node_queue:
        current_node = node_queue.pop(0)
        if not current_node['order']:
            current_node['order'] = [0]

        if len(current_node['order']) == num_cities:
            current_distance = calculate_total_distance(current_node['order'], cities)
            if current_distance < minimum_distance:
                minimum_distance = current_distance
                optimal_order = current_node['order']
            continue

        for i in range(num_cities):
            if i not in current_node['order']:
                child_node = {
                    'order': current_node['order'] + [i],
                    'reduced_matrix': np.copy(current_node['reduced_matrix']),
                    'bound': 0,
                }
                child_node['bound'] = current_node['bound'] + \
                                      current_node['reduced_matrix'][current_node['order'][-1], i]
                reduce_matrix(child_node['reduced_matrix'])
                child_node['bound'] += np.sum(np.min(child_node['reduced_matrix'], axis=1))
                if child_node['bound'] < minimum_distance:
                    node_queue.append(child_node)

    return optimal_order, minimum_distance

#---------- Branch and Bound End-------


#----------Nearest Neighbor Method Start -------------------
def nearest_neighbor_tsp(cities):
    num_cities = len(cities)
    visited = [False] * num_cities
    tour = []

    current_city = cities[0] # Starting from the first city
    tour.append(current_city)
    visited[0] = True

    for _ in range(num_cities - 1):
        min_distance = float('inf')
        nearest_city = None

        for i in range(1, num_cities):
            if not visited[i]:
                distance = np.linalg.norm(np.array(current_city) - np.array(cities[i]))
                if distance < min_distance:
                    min_distance = distance
                    nearest_city = cities[i]

        current_city = nearest_city # Move to the nearest city
        tour.append(current_city)
        visited[cities.index(current_city)] = True

    tour.append(tour[0]) # Return to the starting city to complete the tour

    return tour
#----------Nearest Neighbor Method End -------------------

# Execution time measure
def measure_execution_time(method, *args):
    start_time = time.time()
    result = method(*args)
    end_time = time.time()
    execution_time = end_time - start_time

    return result, execution_time

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
