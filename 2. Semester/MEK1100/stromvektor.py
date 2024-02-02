import numpy as np
import matplotlib.pyplot as plt

n = 30

a = np.zeros(n)
x = np.linspace(-10, 10, n)
y = np.linspace(-10, 10, n)

plt.quiver(x, a, a, -np.sin(y))
plt.quiver(a, y, np.sin(x), a)
plt.xlabel('x')
plt.ylabel('y')
plt.show()
