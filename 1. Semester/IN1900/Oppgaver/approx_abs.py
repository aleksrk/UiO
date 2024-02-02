#Problem 6.13 Approximate

import matplotlib.pyplot as plt
import numpy as np

def abs_approx(x , N):
    f_x = 0
    for i in range(1, N+1):
        f_x += (np.cos((2*i - 1)*x)) / (2*i-1)**2

    return np.pi/2 - (4/np.pi)*f_x

x = np.linspace(-1*np.pi, np.pi, 100)
y_1 = abs_approx(x,1)
y_2 = abs_approx(x,2)
y_3 = abs_approx(x,3)
y_4 = abs_approx(x,4)
y_exact = np.abs(x)

plt.plot(x, y_1, label='N=1')
plt.plot(x, y_2, label='N=2')
plt.plot(x, y_3, label='N=3')
plt.plot(x, y_4, label='N=4')
plt.plot(x, y_exact, label='abs exact')

plt.legend()
plt.show()

"""
Kjøreeksempel:
kvale@Aleksanders-MBP Oppgaver % python3 approx_abs.py

Plot er vedlagt i approxplots.png
"""
