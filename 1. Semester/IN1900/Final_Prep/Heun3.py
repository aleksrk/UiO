from ODESolver import ODESolver
import numpy as np

class Heun3(ODESolver):
    def advance(self):

        u, f, n, t = self.u, self.f, self.n, self.t
        dt = t[n+1] - t[n]
        K1 = dt * f(u[n], t[n])
        K2 = dt * f(u[n] + (1/3)*K1, t[n] + (1/3)*dt)
        K3 = dt * f(u[n] + (2/3)*K2, t[n] + (2/3)*dt)
        unew = u[n] + (1/4)*K1 + (3/4)*K3
        return unew
