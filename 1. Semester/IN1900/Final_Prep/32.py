import numpy as np

def heun3(f, U0, T, n):
    u = np.zeros(n)
    u[0] = U0
    t = np.linspace(0, T, n)
    dt = t[1] - t[0]

    for i in range(1, n-1):
        K1 = dt * f(u[i], t[i])
        K2 = dt * f(u[i] + (1/3)*K1, t[i] + (1/3)*dt)
        K3 = dt * f(u[i] + (2/3)*K2, t[i] + (2/3)*dt)
        u[i] = u[i-1] + (1/4)*K1 + (3/4)*K3

    return u, t


f = lambda x, t: x**2 + t

fe = heun3(f, 0, 3, 100)
