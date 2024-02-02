import numpy as np
import matplotlib.pyplot as plt

class ODESolver:

    def __init__(self, f):
        self.f = f

    def advance(self):
        """Advance solution one time step."""
        raise NotImplementedError # implement in subclass

    def set_initial_condition(self, U0):
        self.U0 = float(U0)

    def solve(self, time_points):
        self.d = 0
        self.t = np.asarray(time_points)
        N = len(self.t)
        self.u = np.zeros(N)
        # Assume that self.t[0] corresponds to self.U0
        self.u[0] = self.U0

        # Time loop
        for n in range(N-1):
            self.n = n
            self.u[n+1] = self.advance()
        return self.u, self.t

class Midpoint(ODESolver):

    def advance(self):
        u, f, n, t = self.u, self.f, self.n, self.t

        dt = t[n+1] - t[n]
        k1 = f(u[n], t[n])
        k2 = f(u[n] + (dt/2)*k1, t[n] + dt/2)
        unew = u[n] + dt*k2
        return unew

class Heuns(ODESolver):

    def advance(self):
        u, f, n, t = self.u, self.f, self.n, self.t

        dt = t[n+1] - t[n]
        k1 = f(u[n], t[n])
        k2 = f(u[n] + dt*k1, t[n]+dt)
        unew = u[n] + dt*(k1/2 + k2/2)
        return unew

class RungeKutta4(ODESolver):

    def advance(self):
        u, f, n, t = self.u, self.f, self.n, self.t

        dt = t[n+1] - t[n]
        dt2 = dt/2.0
        k1 = f(u[n], t[n])
        k2 = f(u[n] + dt2*k1, t[n] + dt2)
        k3 = f(u[n] + dt2*k2, t[n] + dt2)
        k4 = f(u[n] + dt*k3, t[n] + dt)
        unew = u[n] + (dt/6.0)*(k1 + 2*k2 + 2*k3 + k4)
        return unew

def plot_analytical():
    analytical = lambda u, t: t*np.sin(t) + 2*np.cos(t)
    t_exact = np.linspace(0,8*np.pi,1000)
    u_exact = analytical(0, t_exact)
    plt.plot(t_exact, u_exact, label='exact solution')

def f(u, t):
    return t*np.cos(t) - np.sin(t)

U0 = 2
time_points = np.linspace(0,8*np.pi,20)
nvals = (20, 25, 50, 150)
i = 1
methods = {Midpoint : 'Midpoint', Heuns : 'Heuns', RungeKutta4 : 'RungeKutta4'}

for method in methods:
    plt.subplot(3,1,i)
    plt.title(methods[method])
    i += 1
    val = method(f)
    val.set_initial_condition(U0)
    for n in nvals:
        u, t = val.solve(np.linspace(0, 8*np.pi, n))
        plt.plot(t, u, label=f'{n} points')
    plot_analytical()
    plt.legend(loc='lower right', fontsize=5)

plt.show()
