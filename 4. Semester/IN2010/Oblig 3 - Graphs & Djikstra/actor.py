from collections import defaultdict

class Actor:

    def __init__(self, id, name):
        self.id = id
        self.name = name
        self.movies = set()

    def addMovie(self, id):
        self.movies.add(id)

    def getMovies(self):
        return self.movies

    def __lt__(self, other):
        return self.id == other.id
