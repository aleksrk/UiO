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

def f(u, t):
    return u / 5 # returns the solution of u' given a value of u

exact = lambda u, t: 0.1*np.exp(0.2*t) # analytical solution of differential equation
t_exact = np.linspace(0, 20, 1000)
u_exact = exact(0, t_exact)

U0 = 0.1
time_points = np.linspace(0,20,5)

fe = ForwardEuler(f)
fe.set_initial_condition(U0)
u, t = fe.solve(time_points)
plt.plot(t, u, label='dt = 5')

nvals = (10, 25, 50, 100)
for n in nvals:
    time_points = np.linspace(0,20,n)
    u1, t1 = fe.solve(time_points)
    plt.plot(t1, u1, label=f'dt = {20/n}')

plt.plot(t_exact, u_exact, label='exact solution')
plt.legend()
plt.show()
