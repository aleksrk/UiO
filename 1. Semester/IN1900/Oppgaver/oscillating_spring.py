#Problem 6.4 Oscillating Spring
import matplotlib.pyplot as plt
import numpy as np
def y(t):
    A = 0.3 #meter
    k = 4 #kg per second to the power of negative 2
    m = 9 #kg
    fc = 0.15 #seconds to the power of negative 1 -- friction coefficient
    computed = A * np.e**(-fc * t) * np.cos(np.sqrt((k / m) * t))
    return computed

t_array = np.zeros(101)
y_array = np.zeros(101)

for i in range(0, 101):
    t_array[i] = 0.25*i
    y_array[i] = y(t_array[i])

plt.plot(t_array, y_array, label='for loop')

t_array = np.linspace(0,25,101)
y_array = y(t_array)

plt.plot(t_array, y_array, label='linspace')
plt.xlabel('time in seconds')
plt.ylabel('position in meter')
plt.legend()
plt.show()

"""
Kjøreeksempel:
kvale@Aleksanders-MBP Oppgaver % python3 oscillating_spring.py
plotting er lagt ved i fil plot.png
"""
