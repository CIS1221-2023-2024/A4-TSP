from itertools import permutations
import numpy as np
import time

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

  
    while node_queue: # Main loop: Explore nodes until the priority queue is empty
        
        current_node = node_queue.pop(0) # Pop a node from the front of the queue
        
        if not current_node['order']: # Initialize order if empty
            current_node['order'] = [0]

        if len(current_node['order']) == num_cities: # Check if all cities have been visited
            current_distance = calculate_total_distance(current_node['order'], cities) # Calculate the total distance of the current tour
            
            if current_distance < minimum_distance: # Update optimal tour if the current tour is shorter
                minimum_distance = current_distance
                optimal_order = current_node['order']
            continue # Skip to the next iteration of the loop

        for i in range(num_cities):  # Explore child nodes for each unvisited city
            if i not in current_node['order']:
                
                child_node = {  # Create a child node by adding the unvisited city to the current order
                    'order': current_node['order'] + [i],
                    'reduced_matrix': np.copy(current_node['reduced_matrix']),
                    'bound': 0,
                }
                child_node['bound'] = current_node['bound'] + \
                                      current_node['reduced_matrix'][current_node['order'][-1], i]
                
                reduce_matrix(child_node['reduced_matrix'])  # Reduce the matrix for the child node
                
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
    tour_distance = 0

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

        tour_distance += min_distance
        current_city = nearest_city # Move to the nearest city
        tour.append(current_city)
        visited[cities.index(current_city)] = True

    tour_distance += np.linalg.norm(np.array(tour[-1]) - np.array(tour[0]))
    tour.append(tour[0]) # Return to the starting city to complete the tour
    

    return tour, tour_distance
#----------Nearest Neighbor Method End -------------------

# Execution time measure
def measure_execution_time(method, *args):
    start_time = time.time()
    result = method(*args)
    end_time = time.time()
    execution_time = end_time - start_time

    return result, execution_time
