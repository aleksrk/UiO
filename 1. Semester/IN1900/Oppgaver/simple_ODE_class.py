import numpy as np
import matplotlib.pyplot as plt

class ForwardEuler_v1:

    def __init__(self, f, U0, T, N):
        self.f, self.U0, self.T, self.N = f, U0, T, N
        self.dt = T/N
        self.u = np.zeros(self.N+1)
        self.t = np.zeros(self.N+1)

    def solve(self):
        """Compute solution for 0 <= t <= T."""
        self.u[0] = float(self.U0)
        for n in range(self.N):
            self.n = n
            self.t[n+1] = self.t[n] + self.dt
            self.u[n+1] = self.advance()
        return self.u, self.t

    def advance(self):
        """Advance the solution one time step."""
        # Create local variables to get rid of "self." in
        # the numerical formula
        u, dt, f, n, t = self.u, self.dt, self.f, self.n, self.t
        unew = u[n] + dt*f(u[n], t[n])
        return unew

class func:

    def __init__(self, U0):
        self.U0 = U0

    def __call__(self, u, t):
        return u / 5 # general function u' = u / 5

    def plot(self, N):
        variable = ForwardEuler_v1(problem, problem.U0, 20, N)
        u, t = variable.solve()
        plt.plot(t, u, '--', label = f'dt = {20/N}')

problem = func(0.1)
problem.plot(4)

nvals = (10, 25, 50, 100)
for n in nvals:
    problem.plot(n)

exact = lambda u, t: 0.1*np.exp(0.2*t)
t_exact = np.linspace(0, 20, 1000)
u_exact = exact(0, t_exact)

plt.plot(t_exact, u_exact, label='exact solution')
plt.legend()
plt.show()

"""
kvale@Aleksanders-MBP Oppgaver % python3 simple_ODE_class.py
"""
