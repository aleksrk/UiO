from ODESolver import ODESolver
import numpy as np
import matplotlib.pyplot as plt

class Heun3(ODESolver):
    def advance(self):

        u, f, n, t = self.u, self.f, self.n, self.t
        dt = t[n+1] - t[n]
        K1 = dt * f(u[n], t[n])
        K2 = dt * f(u[n] + (1/3)*K1, t[n] + (1/3)*dt)
        K3 = dt * f(u[n] + (2/3)*K2, t[n] + (2/3)*dt)
        unew = u[n] + (1/4)*K1 + (3/4)*K3
        return unew

def rhs(u, t):
    a = 1.0
    R = 50.0
    return a * u*(1 - u/R)


solver = Heun3(rhs)
time = np.linspace(0,20,201)
solver.set_initial_condition(0.1)
u, t = solver.solve(time)

plt.plot(t, u)
plt.show()
