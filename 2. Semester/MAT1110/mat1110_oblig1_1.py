import matplotlib.pyplot as plt
import numpy as np

a = 2
b = 2
c = 4
a2 = 2
b2 = -2
c2 = 4

I = np.linspace(-5, 5, 20)
x, y = np.meshgrid(I, I, indexing="ij")
plt.subplot(1,2,1)
plt.quiver(x, y,a*x+b*y , c*x-a*y)         # Vektorfelt
plt.contour(x, y, c*x**2 - 2*a*x*y - b*y**2, 8) # Strømlinjer
plt.axis('equal')
plt.title('Delta < 0, a = 2, b = 2, c = 4')

plt.subplot(1,2,2)
plt.quiver(x, y,a2*x+b2*y , c2*x-a2*y)         # Vektorfelt
plt.contour(x, y, c2*x**2 - 2*a2*x*y - b2*y**2, 8) # Strømlinjer
plt.axis('equal')
plt.title('Delta > 0, a = 2, b = -2, c = 4')
plt.show()
