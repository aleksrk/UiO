from actor import Actor
from movie import Movie
from collections import defaultdict
from collections import deque
from collections import Counter
from heapq import heappush, heappop

class Graph:
    """A class to store and work with graph datastructures

    Attributes:
        movies (dict): Stores all movie objects
        actors (dict): Stores all actor objects
        edges (defaultdict(list)): Stores all edges between actor objects
        weights (dict): Stores pairs of actors that starred in the same movie

    """
    movies = dict()
    actors = dict()
    edges = defaultdict(list)
    weights = dict()

    def __init__(self, filepath_actors, filepath_movies):
        """Default class constructor.

        Args:
            filepath_actors (str): The filepath to the actors file
            filepath_movies (str): The filepath to the actors file

        """

        self.read_movies_CSV(filepath_movies)
        self.read_actors_CSV(filepath_actors)
        self.build_graph()


    def read_actors_CSV(self, filepath):
        """Read the actor file.

        Reads the actorfile from given path and stores it in class variable actors

        Args:
            filepath (str): The filepath to the actors file

        """
        readlines = open(filepath, "r")
        for line in readlines:

            newline = line.split("\t")
            new_actor = Actor(newline[0], newline[1])

            for i in range(2, len(newline)):
                current_movie = newline[i].rstrip("\n")

                if current_movie in self.movies:
                    new_actor.addMovie(self.movies[current_movie])
                    self.movies[current_movie].addActor(new_actor)

            self.actors[newline[0]] = new_actor

    def read_movies_CSV(self, filepath):
        """Read the movie file.

        Reads the moviefile from given path and stores it in class variable movies

        Args:
            filepath (str): The filepath to the actors file

        """
        readlines = open(filepath, "r")

        for line in readlines:
            id, name, rating,_ = line.split("\t");
            self.movies[id] = Movie(id, name, rating)

    def build_graph(self):
        """Build the graph using the dictionaries genereted in the read methods"""

        self.edges = defaultdict(list)
        self.weights = defaultdict(list)

        for outer_actor in self.actors.values():
            for shared_movie in outer_actor.getMovies():
                for inner_actor in shared_movie.actors:
                    if inner_actor == outer_actor:
                        pass
                    else:
                        self.edges[outer_actor].append(inner_actor)
                        self.weights[(outer_actor, inner_actor)].append(shared_movie)

    def count_edges(self) -> int:
        """Count the amount of edges in the graph

        Returns:
            int: The amount of edges

        """
        total_edges = 0
        for _,E in self.edges.items():
            total_edges += len(E)

        return int(total_edges / 2)

    def count_nodes(self) -> int:
        """Count the amount of nodes in the graph

        Returns:
            int: The amount of nodes

        """
        return len(self.actors)

    def bfs_shortest_paths_from(self, s):
        """Implementation of the Breadth-first search algorithm

        Args:
            s (str): The actor_id to start the search at

        Returns:
            parents (dict): Dictionary of the parents of s

        """
        actor = self.actors[s]
        parents = {actor : None}
        queue = deque([actor])

        while queue:
            v = deque.popleft(queue)
            for u in self.edges[v]:
                if u not in parents:
                    parents[u] = v
                    queue.append(u)
        return parents

    def bfs_shortest_path_between(self, s, t):
        """Extension of the Breadth-first search algorithm to support paths between specific nodes

        Args:
            s (str): The actor_id to start the search at
            t (str): The actor_id to find the shortest way to

        Returns:
            path (list): The shortest path from s to t

        """
        actor1 = self.actors[s]
        actor2 = self.actors[t]
        parents = self.bfs_shortest_paths_from(s)
        v = actor2
        path = []

        if actor2 not in parents:
            return path

        while v:
            path.append(v)
            v = parents[v]
        return path[::-1]

    def print_path(self, path):
        """Neatly print the path and movies from actor a to b

        Args:
            path (list): A list of actor objects to print

        """
        print(path[0].name)
        for i in range(0, len(path)-1):
            mov = self.weights[(path[i], path[i+1])]
            print("===[ " + mov[0].title + " " + mov[0].rating + " ] ===> " + path[i+1].name)
        print("\n")


    def djikstra_shortest_paths_from(self, s):
        """Implementation of the Djikstra search algorithm

        Args:
            s (str): The actor_id to start the search at

        Returns:
            parents (dict): All the parents of s
        """
        actor = self.actors[s]
        Q = [(0, actor)]
        D = defaultdict(lambda: float('inf'))
        parents = {actor : None}
        D[actor] = 0

        while Q:
            cost, v = heappop(Q)
            for u in self.edges[v]:
                weight = 0
                for mov in self.weights[(v, u)]:
                    if float(mov.rating) > weight:
                        weight = float(mov.rating)
                c = cost + 10 - weight
                if c < D[u]:
                    D[u] = c
                    heappush(Q, (c, u))
                    parents[u] = v

        return parents

    def djikstra_shortest_path_between(self, s, t):
        """Extension of the Djikstra-first search algorithm to support paths between specific nodes

        Args:
            s (str): The actor_id to start the search at
            t (str): The actor_id to find the shortest way to

        Returns:
            path (list): The shortest path from s to t

        """
        actor1 = self.actors[s]
        actor2 = self.actors[t]
        parents = self.djikstra_shortest_paths_from(s)
        v = actor2
        path = []

        if actor2 not in parents:
            return path

        while v:
            path.append(v)
            v = parents[v]
        return path[::-1]

    def print_path_weighted(self, path):
        """Neatly print the path and movies from actor a to b

        Args:
            path (list): A list of actor objects to print

        """
        print(path[0].name)
        weight = 0.0
        for i in range(0, len(path)-1):
            movie = None
            w = 0
            for mov in self.weights[(path[i], path[i+1])]:
                if float(mov.rating) > w:
                    w = float(mov.rating)
                    movie = mov
            print("===[ " + movie.title + " " + movie.rating + " ] ===> " + path[i+1].name)
            weight += 10 - float(w)
        print("Total weight: " + str(weight))

    def bfs(self, start):
        """Slightly different implementation of the Breadth-first algorithm

        Args:
            start (str): The actor_id to start the search at

        Returns:
            size (int): The amount of nodes read in the search
            visit (set): A set giving all actors visited in the search
        """
        visit = set([start])
        queue = deque([start])
        size = 0

        while queue:
            v = deque.popleft(queue)
            size += 1
            for u in self.edges[v]:
                if u not in visit:
                    visit.add(u)
                    queue.append(u)
        return size, visit

    def count_components(self):
        """Count the amount of components in the graph and print to console"""

        components = []
        visited = set([])

        for act in self.actors.values():
            if act not in visited:
                size = 0
                size, v = self.bfs(act)
                visited = visited.union(v)
                components.append(size)

        counts = Counter(components)
        c = sorted([list(i) for i in counts.items()])
        c.reverse()
        for key,value in c:
            print("There are " + str(value) + " components of size " + str(key))
