import numpy as np
import matplotlib.pyplot as plt

m = 5 # kg mass
l_0 = 0.5 # meter length of spring
h = 0.3 # meter height from spring level to cylinder
k = 500 # N/m constant
x_1 = 0.65 # meter starting position in the x direction
time = 10 # seconds
dt = 0.0001 # seconds

n = int(np.ceil(time/dt))

t = np.zeros(n)
x = np.zeros(n)
v = np.zeros(n)
a = np.zeros(n)

x[0] = x_1
a[0] = (-k*x[0]*(1 - (l_0 / ( np.sqrt(x[0]**2 + h**2) )) ) ) / m


for i in range(0, n-1):
    a[i+1] = (-k*x[i]*(1 - (l_0 / ( np.sqrt(x[i]**2 + h**2) )) ) ) / m
    v[i+1] = v[i] + a[i]*dt
    x[i+1] = x[i] + v[i+1]*dt
    t[i+1] = t[i] + dt

plt.subplot(2,1,1)
plt.ylabel('x [m]')
plt.xlabel('t [s]')
plt.plot(t, x)
plt.tight_layout()

plt.subplot(2,1,2)
plt.ylabel('v [m/s]')
plt.xlabel('t [s]')
plt.plot(t, v)
plt.tight_layout()

plt.show()
