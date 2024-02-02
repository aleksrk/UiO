import numpy as np
import matplotlib.pyplot as plt

def ForwardEuler(f, U0, T, N):
    t = np.zeros(N+1)
    u = np.zeros(N+1) # u[n] is the solution at time t[n]
    u[0] = U0
    dt = T/N
    for n in range(N):
        t[n+1] = t[n] + dt
        u[n+1] = u[n] + dt*f(u[n], t[n])
    return u, t

def f(u, t):
    return u / 5 # returns the solution of u' given a value of u

def plot_func(N): # plots the differential equation given a value N for delta t
    u2, t2 = ForwardEuler(f, U0, T, N)
    plt.plot(t2, u2, '--', label = f'dt = {20/N}')

U0 = 0.1
T = 20
N = 4
u, t = ForwardEuler(f, U0, T, N)
plot_func(N)

exact = lambda u, t: 0.1*np.exp(0.2*t) # analytical solution of differential equation
t_exact = np.linspace(0, 20, 1000)
u_exact = exact(u, t_exact)

nvals = (10, 25, 50, 100)
for n in nvals:
    plot_func(n)

plt.plot(t_exact, u_exact, label='exact solution')
plt.legend()
plt.show()

"""
kvale@Aleksanders-MBP Oppgaver % python3 simple_ODE_func.py
"""
