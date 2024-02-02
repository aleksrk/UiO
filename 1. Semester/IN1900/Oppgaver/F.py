# Problem 8.3 Make a function class
import numpy as np
import matplotlib.pyplot as plt

class F:
    def __init__(self, n, m):
        self.n = n
        self.m = m

    def __call__(self, x):
        return np.sin(self.n*x) * np.cos(self.m*x)

u = F(3,10)
v = F(1,np.pi)
x = np.linspace(0,2*np.pi,1000)

plt.plot(u(x), v(x), label='u vs x')
plt.legend()
plt.show()

"""
kvale@Aleksanders-MBP Oppgaver % python3 F.py
"""
