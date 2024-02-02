#Problem A.6 Lotka-Volterra two species model
import numpy as np
import matplotlib.pyplot as plt

n, a, b, c, e = 500, 0.04, 0.1, 0.005, 0.2

R_x = np.linspace(0,n-1,n)
R_y = np.zeros(n)
R_y[0] = 100

F_x = np.linspace(0,n-1,n)
F_y = np.zeros(n)
F_y[0] = 20

for i in range(len(R_x) - 1):
    R_y[i+1] = R_y[i] + a * R_y[i] - c * R_y[i] * F_y[i]
    F_y[i+1] = F_y[i] + e * c * R_y[i] * F_y[i] - b * F_y[i]

plt.plot(R_x, R_y, label='Rabbits')
plt.plot(F_x, F_y, label='Foxies')
plt.xlabel('Number of weeks')
plt.ylabel('Population')
plt.legend()
plt.show()

"""
kvale@Aleksanders-MBP Oppgaver % python3 Lotka_Volterra.py
"""
