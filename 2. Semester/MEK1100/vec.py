import numpy as np
import matplotlib.pyplot as plt
from velfield import velfield

n = 30

x, y, u, v = velfield(n)
plt.quiver(x[::2, ::2], y[::2, ::2], u[::2, ::2], v[::2, ::2])
plt.xlabel('x')
plt.ylabel('y')
plt.show()
