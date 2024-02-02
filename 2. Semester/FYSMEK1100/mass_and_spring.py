import numpy as np
import matplotlib.pyplot as plt

m = 1       # mass in kg
k = 100     # spring constant in N/m
v0 = 1      # initial velocity in m/s
time = 2    # the maximum time we consider in s
dt = 0.0001 # time step in s

n = int(np.ceil(time/dt))

t = np.zeros(n,float)
x = np.zeros(n,float)
v = np.zeros(n,float)

t[0] = 0
x[0] = 0
v[0] = v0


for i in range(0, n-1):
    F = -k*x[i]
    a = F/m
    v[i+1] = v[i] + a*dt
    x[i+1] = x[i] + v[i+1]*dt
    t[i+1] = t[i] + dt

plt.subplot(2,1,1)
plt.plot(t,x)

plt.xlabel('t [s]')
plt.ylabel('x [m]')

plt.subplot(2,1,2)
plt.plot(t,v)
plt.xlabel('t [s]')
plt.ylabel('v [m/s]')

plt.show()
