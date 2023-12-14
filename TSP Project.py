from itertools import permutations
import numpy as np


distances = np.array([   #Arbitrary integer values, Matrix Size can be larger
    [80, 10, 60, 38],
    [100, 38, 37, 76],
    [5, 65, 54, 80],
    [80, 135, 30, 21]
])

cities = [(0, 0), (10, 10), (15, 5), (20, 0)] # This for the Nearest Neighbor method, Arbitrary values


def calculate_total_distance(order, distances):
    total_distance = 0
    for i in range(len(order) - 1):
        total_distance += distances[order[i]][order[i + 1]]
    total_distance += distances[order[-1]][order[0]]  # Return to the starting city
    return total_distance

def tsp_brute_force(distances):
    num_cities = len(distances)
    cities = list(range(num_cities))
    min_distance = float('inf')
    best_order = None

    for perm in permutations(cities):
        current_distance = calculate_total_distance(perm, distances)
        if current_distance < min_distance:
            min_distance = current_distance
            best_order = perm

    return best_order, min_distance
#----- Brute force end _________


#______ Branch and Bound Start ---------
def reduce_matrix(matrix):
    # Reduce each row
    for i in range(matrix.shape[0]):
        min_val = np.min(matrix[i, :])
        if min_val != np.inf:
            matrix[i, :] -= min_val

    # Reduce each column
    for j in range(matrix.shape[1]):
        min_val = np.min(matrix[:, j])
        if min_val != np.inf:
            matrix[:, j] -= min_val

def branch_and_bound_tsp(distances):
    num_cities = len(distances)
    reduced_matrix = np.copy(distances)
    reduce_matrix(reduced_matrix)

    # Initialize the initial node
    root_node = {
        'order': [],
        'reduced_matrix': reduced_matrix,
        'bound': 0,
    }

    # Priority queue to store nodes
    node_queue = [root_node]
    minimum_distance = float('inf')
    optimal_order = None

    while node_queue:
        current_node = node_queue.pop(0)
        if not current_node['order']:
            current_node['order'] = [0]

        if len(current_node['order']) == num_cities:
            current_distance = calculate_total_distance(current_node['order'], distances)
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
def calculate_distance(city1, city2):
    return np.linalg.norm(np.array(city1) - np.array(city2)) # Calculates Euclidean Distance between 2 cities.


def nearest_neighbor_tsp(cities): # This starts from the first city, iteratively selects the nearest unvisited city and forms a tour.
    num_cities = len(cities)
    visited = [False] * num_cities
    tour = []


    current_city = cities[0] # Starting from the first city
    tour.append(current_city)
    visited[0] = True

    for _ in range(num_cities - 1):
        min_distance = float('inf')
        nearest_city = None

        for i in range(1, num_cities): # Find the nearest unvisited city
            if not visited[i]:
                distance = calculate_distance(current_city, cities[i])
                if distance < min_distance:
                    min_distance = distance
                    nearest_city = cities[i]

        current_city = nearest_city   # Move to the nearest city
        tour.append(current_city)
        visited[cities.index(current_city)] = True

    tour.append(tour[0])  # Return to the starting city to complete the tour

    return tour


tour = nearest_neighbor_tsp(cities)
total_distance = sum(calculate_distance(tour[i], tour[i + 1]) for i in range(len(tour) - 1))
optimal_order, minimum_distance = branch_and_bound_tsp(distances)
best_order, min_distance = tsp_brute_force(distances)


print(f"Using the Brute Force Method : \nBest Order: {best_order}") # Brute Force Output
print(f"Minimum Distance: {min_distance}")

print(f"Using the Branch and Bound Method : \nBest Order: {optimal_order}") # B&B Output
print(f"Minimum Distance: {minimum_distance}")

print(f"Using the Nearest Neighbour Method \nBest Order: {tour}") # N.N. Output
print(f"Total Distance: {total_distance}")
