#Problem A.4. Finding pi with Newton's method
import numpy as np

def f(initial0):
    return initial0 - ((np.sin(initial0)/np.cos(initial0)))


x_0 = 3.14
x_1 = f(x_0)
x_2 = f(x_1)
pi_real = np.pi

print(x_0)
print(x_1)
print(x_2)
print(f'{pi_real:.13f}')

"""
Kjøreeksempel:
kvale@Aleksanders-MBP Oppgaver % python3 finding_pi.py
3.14
3.1415926549364075
3.141592653589793
3.1415926535898
"""
