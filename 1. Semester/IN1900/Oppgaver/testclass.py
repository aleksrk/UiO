class fuck:

    def __init__(self, u, t):
        self.u = u
        self.t = t


fuckme = fuck(2, 3)

print(fuckme.u)


class fucktwo:

    def __init__(self, region):

        self.region = region

    def __call__(self):
        return self.region.u

fuckmetwo = fucktwo(fuckme)

print(fuckmetwo())
