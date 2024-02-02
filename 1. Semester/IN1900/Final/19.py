from Ralston import Ralston
from ODESolver import ODESolver

import matplotlib.pyplot as plt
import numpy as np

def SIRD(S0, I0, alpha, beta, gamma, T):

    def f(u, t):
        N = I0 + S0
        S, I, R, D = u[0], u[1], u[2], u[3]
        delta_S = -alpha(t)*( (S * I) / N )
        delta_I = alpha(t)*( (S * I) / N ) - beta*I - gamma*I
        delta_R = beta*I
        delta_D = gamma*I

        return delta_S, delta_I, delta_R, delta_D

    solver = Ralston(f)
    initials = [S0, I0, 0, 0]
    solver.set_initial_condition(initials)
    time = np.linspace(0, T, 10*T + 1)
    u, t = solver.solve(time)

    return t, u[:,0], u[:,1], u[:,2], u[:,3]

S0 = 370000; I0 = 30; beta = 0.025; gamma = 0.25
alpha = lambda t: 1.0
T = 100

t, S, I, R, D = SIRD(S0, I0, alpha, beta, gamma, T)

plt.plot(t, S, label='S of (t)')
plt.plot(t, I, label='I of (t)')
plt.plot(t, R, label='R of (t)')
plt.plot(t, D, label='D of (t)')

plt.legend()
plt.show()
