from ODESolver import ODESolver
import numpy as np
import matplotlib.pyplot as plt

class Ralston(ODESolver):

    def advance(self):

        u, f, n, t = self.u, self.f, self.n, self.t
        dt = t[n+1] - t[n]
        k1 = f(u[n], t[n])
        k2 = f(u[n] + (2/3)*dt*k1, t[n] + (2/3)*dt)
        unew = u[n] + dt*( (1/4)*k1 + (3/4)*k2 )

        return unew


rhs = lambda u,t: -u

solver = Ralston(rhs)

solver.set_initial_condition(1.0)

time = np.linspace(0,10,101)

u, t = solver.solve(time)

print(u, t)
