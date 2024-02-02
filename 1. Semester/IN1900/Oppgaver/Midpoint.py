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

class ForwardEuler(ODESolver):

    def advance(self):
        u, f, n, t = self.u, self.f, self.n, self.t

        dt = t[n+1] - t[n]
        unew = u[n] + dt*f(u[n], t[n])
        return unew

class Midpoint(ODESolver):

    def advance(self):
        u, f, n, t = self.u, self.f, self.n, self.t

        dt = t[n+1] - t[n]
        k1 = f(u[n], t[n])
        k2 = f(u[n] + (dt/2)*k1, t[n] + dt/2)
        unew = u[n] + dt*k2
        return unew

def f(u, t):
    return np.cos(t) - t*np.sin(t)

time_points = np.linspace(0, 4*np.pi, 20)

mp = Midpoint(f)
mp.set_initial_condition(U0=0)
u, t = mp.solve(time_points)
plt.plot(t, u, label='Midpoint Method')

fe = ForwardEuler(f)
fe.set_initial_condition(U0=0)
u1, t1 = fe.solve(time_points)
plt.plot(t1, u1, label='ForwardEuler Method')

exact = lambda u, t: t*np.cos(t)
t_exact = np.linspace(0,4*np.pi,1000)
u_exact = exact(0, t_exact)
plt.plot(t_exact, u_exact, label='exact solution')

plt.legend()
plt.show()
