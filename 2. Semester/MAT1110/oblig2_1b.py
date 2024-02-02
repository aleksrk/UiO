import numpy as np
import matplotlib.pyplot as plt

l = 2
I = np.linspace(0, 2, 20)
x, y, = np.meshgrid(I, I , indexing = 'ij')
xa = np.sqrt(l**2 - y**2)
vx = -y/l
vy = xa/l

plt.quiver(x, y, vx, vy)
plt.xlabel('x')
plt.ylabel('y')
plt.show()
