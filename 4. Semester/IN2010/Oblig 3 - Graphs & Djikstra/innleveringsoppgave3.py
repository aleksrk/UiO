from graph import Graph
import time

if __name__ == '__main__':

    #Exercise 1 - Build graph
    start = time.time()

    G = Graph("actors.tsv", "movies.tsv")
    nodes = G.count_nodes()
    edges = G.count_edges()
    print("Exercise 1 \n")
    print("Nodes: " + str(nodes))
    print("Edges: " + str(edges))

    stop = time.time()
    print("\nExercise 1 finished, time elapsed: " + str(int(stop-start)) + " seconds")


    #Exercise 2 - Six degrees of IMDB
    start = time.time()

    print("\nExercise 2\n")
    actor_to_actor = [("nm2255973", "nm0000460"),
                    ("nm0424060", "nm0000243"),
                    ("nm4689420", "nm0000365"),
                    ("nm0000288", "nm0001401"),
                    ("nm0031483", "nm0931324")]

    for pair in actor_to_actor:
        shortest_path = G.bfs_shortest_path_between(*pair)
        G.print_path(shortest_path)

    stop = time.time()
    print("\nExercise 2 finished, time elapsed: " + str(int(stop-start)) + " seconds")
    #Exercise 3 - Chillest road
    start = time.time()

    print("\nExercise 3\n")
    for pair in actor_to_actor:
        shortest_path = G.djikstra_shortest_path_between(*pair)
        G.print_path_weighted(shortest_path)

    stop = time.time()
    print("\nExercise 3 finished, time elapsed: " + str(int(stop-start)) + " seconds")

    #Exercise 4 - Compontents
    start = time.time()

    print("\nExercise 4\n")
    G.count_components()

    stop = time.time()
    print("\nExercise 4 finished, time elapsed: " + str(int(stop-start)) + " seconds")
