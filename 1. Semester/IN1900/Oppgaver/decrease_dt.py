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
        return np.cos(6*t) / (1 + t + u)

    def plot(self, N):
        variable = ForwardEuler_v1(problem, problem.U0, 10, N)
        u, t = variable.solve()
        plt.plot(t, u, label = f'dt = {(10 / N):.2f}')

problem = func(1)
nvalues = (20, 30, 35, 40, 50, 100, 1000, 10000)
for n in nvalues:
    problem.plot(n)

plt.legend()
plt.show()

"""
kvale@Aleksanders-MBP Oppgaver % python3 decrease_dt.py
"""
