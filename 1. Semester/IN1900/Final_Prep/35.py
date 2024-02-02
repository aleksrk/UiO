from Heun3 import Heun3
from ODESolver import ODESolver

import matplotlib.pyplot as plt
import numpy as np

def SEIS(S0, E0, p, q, r, T):
    def f(u, t):
        S, E, I = u[0], u[1], u[2]
        dS = -p(t)*S*I + r*I
        dE = p(t)*S*I - q*E
        dI = q*E - r*I
        return dS, dE, dI

    solver = Heun3(f)
    solver.set_initial_condition([S0, E0, 0])
    time = np.linspace(0, T, 10*T+1)
    u, t = solver.solve(time)
    return t, u[:,0], u[:,1], u[:,2]

S0 = 4.0; E0 = 0.2; q = 0.1; r = 0.1
p = lambda t: 0.0233
T = 100

t, S, E, I = SEIS(S0, E0, p, q, r, T)

plt.plot(t, S, label='S of (t)')
plt.plot(t, E, label='E of (t)')
plt.plot(t, I, label='I of (t)')

plt.legend()
plt.show()
