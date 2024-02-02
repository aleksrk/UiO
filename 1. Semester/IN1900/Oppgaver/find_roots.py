# Problem 2.4
from math import sqrt
a = 6.0
b = -5.0
c = 1.0
disc = sqrt( b**2 - 4 * a * c) #pre-calculating discriminant

x1 = ( -b - disc) / (2 * a)
print(f"{x1:.2f}")
x2 = (- b + disc) / (2 * a)
print(f"{x2:.2f}")

"""
kvale@Aleksanders-MacBook-Pro Oppgaver % python3 find_roots.py
0.33
0.50
"""
