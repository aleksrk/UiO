import numpy as np
import matplotlib.pyplot as plt

m = 1.0     # kg
k = 10000   # N/m
mu = 0.3    # frictioncoefficitent
g = 9.81    #N
R = 0.15    # m
h = 1.0     # m
time = 1    # seconds
dt = 0.0001  # seconds
x0 = 0
y0 = h

n = int(np.ceil(time/dt))

t = np.zeros(n)
x = np.zeros(n)
y = np.zeros(n)
vx = np.zeros(n)
vy = np.zeros(n)
alpha_z = np.zeros(n)

x[0] = x0
y[0] = h
vx[0] = 3 # m/s
vy[0] = 0
alpha_z[0] = 0

for i in range(0, n-1):
    if y[i] < R:
        N = k*(R - y[i])**(3/2)
        ax = -mu*N / m
        ay = (N / m) - g
        vaks = -((3*mu*N)/(2*m*R))
    else:
        N = 1
        ax = 0
        ay = (N / m) - g
        vaks = 0
    alpha_z[i+1] = alpha_z[i] + vaks*dt
    vx[i+1] = vx[i] + ax*dt
    vy[i+1] = vy[i] + ay*dt
    x[i+1] = x[i] + vx[i+1]*dt
    y[i+1] = y[i] + vy[i+1]*dt
    t[i+1] = t[i] + dt

plt.subplot(3,1,1)
plt.plot(t, x, label='x(t)')
plt.plot(t, y, label='y(t)')
plt.xlabel('t')
plt.ylabel('s')
plt.legend()
plt.subplot(3,1,2)
plt.plot(t, vx, label='{v_x}(t)')
plt.plot(t, vy, label='{v_y}(t)')
plt.xlabel('t')
plt.ylabel('fart')
plt.legend()
plt.subplot(3,1,3)
plt.plot(t, alpha_z, label='vinkelfart(t)')
plt.xlabel('t')
plt.ylabel('vinkelfart')
plt.legend()

plt.show()
