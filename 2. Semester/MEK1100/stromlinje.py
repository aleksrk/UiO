import numpy as np
import matplotlib.pyplot as plt

x, y = np.meshgrid(np.arange(-10,10,0.05), np.arange(-10,10,0.05))

z = x*y + 1/2(x**2-y**2)
plt.contour(x,y,z, 8)
plt.xlabel('x')
plt.ylabel('y')
plt.show()
