import numpy as np
import matplotlib.pyplot as plt

m = 5 # kg mass
l_0 = 0.5 # meter length of spring
h = 0.3 # meter height from spring level to cylinder
k = 500 # N/m constant
x_1 = 0.75 # meter starting position in the x direction
time = 10 # seconds
dt = 0.0001 # seconds
g = 9.81 # gravity
my_d = 0.05 # frictionconstant for dynamic friction

n = int(np.ceil(time/dt))

t = np.zeros(n)
x = np.zeros(n)
v = np.zeros(n)
a = np.zeros(n)

x[0] = x_1
a[0] = (-k*x[0]*(1 - (l_0 / ( np.sqrt(x[0]**2 + h**2) )) ) ) / m

for i in range(0, n-1):
    N = np.abs(k*h*(1 - (l_0 / np.sqrt(x[i]**2 + h**2))) + m*g)
    if v[i] < 0:
        f_d = my_d * N
    else:
        f_d = -my_d * N
    a[i+1] = (-k*x[i]*(1 - (l_0 / ( np.sqrt(x[i]**2 + h**2) )) ) + f_d ) / m
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

K = (1/2)*m*v**2

plt.plot(x, K)
plt.xlabel('x [m]')
plt.ylabel('KE [J]')
plt.show()
