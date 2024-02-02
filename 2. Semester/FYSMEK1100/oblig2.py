import numpy as np
import matplotlib.pyplot as plt

m = 0.1 # kg
k = 200 # N/m
L_0 = 1 # m
g = 9.81 #N
thetha = np.pi/6 #degrees
time = 10 # seconds
dt = 0.001 # seconds
x0 = np.sin(thetha)
y0 = -np.cos(thetha)

n = int(np.ceil(time/dt))

t = np.zeros(n)
r = np.zeros((n,2))
v = np.zeros((n,2))
a = np.zeros((n,2))

r[0] = np.array([x0, y0])
a[0, 1] = -g
print(r[0, 1])

for i in range(0, n-1):

	r_abs = np.linalg.norm(r[i, :])
	ay = ( -g -(k * (r_abs - L_0) *r[i, 1] ) / (r_abs * m) )
	ax = -(k * (r_abs - L_0) *r[i, 0] ) / (r_abs * m)
	a[i, :] = np.array([ax, ay])
	v[i+1, :] = v[i, :] + a[i, :]*dt
	r[i+1, :] = r[i, :] + v[i+1, :]*dt
	t[i+1] = t[i] + dt

plt.plot(r[:, 0], r[:, 1])
plt.xlabel("x [m]")
plt.ylabel("y [m]")
plt.show()

plt.subplot(3,1,1)
plt.ylabel('y [m]')
plt.xlabel('x [m]')
plt.plot(r[:, 0],r[:, 1])
plt.tight_layout()

plt.subplot(3,1,2)
plt.ylabel('Hastighet [m/s]')
plt.plot(t,v[:, 0], label='v_x')
plt.plot(t,v[:, 1], label='v_y')
plt.legend()

plt.subplot(3,1,3)                                                              #
plt.ylabel('Akselerasjon [m/s^2]')
plt.plot(t,a[:, 0], label='a_x')
plt.plot(t,a[:, 1], label='a_y')
plt.legend()

plt.tight_layout()
plt.show()
