import numpy as np
import matplotlib.pyplot as plt
from SEIR import Region, ProblemSEIR, SolverSEIR

class RegionInteraction(Region):

    def __init__(self, name, S_0, E2_0, lat, long):

        self.lat = lat * (np.pi / 180) #degrees
        self.long = long * (np.pi / 180) #degrees
        Region.__init__(self, name, S_0, E2_0)

    def distance(self, other):

        lat_i, long_i, lat_j, long_j = self.lat, self.long, other.lat, other.long
        R_earth = 64

        val = (np.sin(lat_i)*np.sin(lat_j) + np.cos(lat_i)*np.cos(lat_j)) * (abs(long_i - long_j))

        if 0 < val < 1:
            self.distance = R_earth * np.arccos(val)
            return self.distance

class ProblemInteraction(ProblemSEIR):

    def __init__(self, region, area_name, beta, r_ia = 0.1, r_e2 = 1.25,
                lmbda_1 = 0.33, lmbda_2 = 0.5, p_a = 0.4, mu = 0.2):
                ProblemSEIR.__init__(self, region, beta, r_ia, r_e2, lmbda_1, lmbda_2, p_a, mu)

    def get_population(self):
        pop = 0
        for i in self.region:
            pop += i.get_population()

        return pop

    def set_initial_condition(self):
        self.initial_condition = 0
        for i in self.region:
            self.initial_condition += self.region.initial_condition

    def __call__(self, u, t):
        n = len(self.region)
        SEIR_list = []
        for i in len(n):
            SEIR_list[i] = [S_i, E1_i, E2_i, I_i, Ia_i, R_i]:
        SEIR_list = [u[i:i+6] for i in range(0, len(u), 6)]

        E2_list = [u[i] for i in range(2, len(u), 6)]
        Ia_list = [u[i] for i in range(4, len(u), 6)]
        derivative = []
        for i in range(n):
            S, E1, E2, I, Ia, R = SEIR_list[i]
            dS = 0
            for j in range(n):
                E2_other = E2_list[j]
                Ia_other = Ia_list[j]
                beta = beta(t)
                dS  = -beta*S*I/N - r_ia*beta*S*Ia/N - r_e2*beta*S*E2/N
                dE1 = beta*S*I/N + r_ia*beta*S*Ia/N + r_e2*beta*S*E2/N - lmbda_1*E1
                dE2 = lmbda_1*(1-p_a)*E1 - lmbda_2*E2
                dI  = lmbda_2*E2 - mu*I
                dIa = lmbda_1*p_a*E1 - mu*Ia
                dR  = mu*(I + Ia)

            # put the values in the end of derivative
        return derivative



if __name__ == '__main__':
    innlandet = RegionInteraction('Innlandet', S_0 = 371385, E2_0 = 0, lat = 60.7945, long = 11.0680)
    oslo = RegionInteraction('Oslo', S_0 = 693494 , E2_0 = 100, lat = 59.9, long = 10.8)
    print(oslo.distance(innlandet))
