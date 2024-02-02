# importing necessary libraries for plotting and arrays
import numpy as np
import matplotlib.pyplot as plt

m   = 80        # mass in kg
F   = 400       # positive force in x direction of sprinter
p   = 1.293     # air density in kg/m^3
A0  = 0.45      # sprinterens tverrsnitt in m^2
CD  = 1.2       # random constant
w   = 0         # wind speed in m/s     # Ns/m
ti  = 10         # maximum time we consider in s #boltspeed
dt  = 0.0001      # time step in s
exitval = True

n = int(np.ceil(ti/dt))

t = np.zeros(n, float)
x = np.zeros(n, float)
v = np.zeros(n, float)
a = np.zeros(n, float)


#D = lambda v: 1/2*(p*CD*A0*v**2) # first lambda D for previous tasks
D = lambda v: 1/2*(p * CD * A0 * v**2)

for i in range(0, n-1):
    Fnet = F - D(v[i])
    a[i] = Fnet / m
    v[i+1] = v[i] + a[i]*dt
    x[i+1] = x[i] + v[i+1]*dt
    t[i+1] = t[i] + dt
    if x[i] >= 100 and exitval: # printing the time it takes the runner to reach the finish line at 100m
        print(i*dt)
        exitval = False

a[-1] = (F - D(v[-1])) / m

plt.subplot(3,1,1)
plt.plot(t, x)
plt.xlabel('t [s]')
plt.ylabel('x [m]')

plt.subplot(3,1,2)
plt.plot(t, v)
plt.xlabel('t [s]')
plt.ylabel('v [m/s]')

plt.subplot(3,1,3)
plt.plot(t, a)
plt.xlabel('t [s]')
plt.ylabel('a [m/s^2]')

plt.show()
