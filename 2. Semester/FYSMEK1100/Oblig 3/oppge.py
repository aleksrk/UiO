import numpy as np
import matplotlib.pyplot as plt

l_0 = 0.5 # meter length of spring
h = 0.3 # meter height from spring level to cylinder
k = 500 # N/m constant

L = lambda x, h: np.sqrt(x**2 + h**2)

x = np.linspace(-0.75, 0.75, 100)
F = -k*x*(1 - (l_0 / ( L(x,h) ) ) )

plt.plot(x, F, label='F/x')
plt.xlabel('x[m]')
plt.ylabel('F[N]')
plt.legend()
plt.show()
