import numpy as np

class ODESolver:
    def __init__(self, f):

        self.f = lambda u, t: np.asarray(f(u, t), float)

    def set_initial_condition(self, U0):
        if isinstance(U0, (float, int)):
            self.neq = 1
            U0 = float(U0)
        else:
            U0 = np.asarray(U0)
            self.neq = U0.size
        self.U0 = U0

    def solve(self, time_points):
        self.t = np.asarray(time_points)
        N = len(self.t)
        if self.neq == 1:
            self.u = np.zeros(N)
        else:
            self.u = np.zeros((N, self.neq))

        self.u[0] = self.U0

        for n in range(N-1):
            self.n = n
            self.u[n+1] = self.advance()
        return self.u, self.t
