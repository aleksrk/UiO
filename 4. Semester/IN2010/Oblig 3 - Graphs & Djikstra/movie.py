class Movie:

    def __init__(self, id, title, rating):
        self.id = id
        self.title = title
        self.rating = rating
        self.actors = set()

    def addActor(self, id):
        self.actors.add(id)

    def getActors(self):
        return self.actors
