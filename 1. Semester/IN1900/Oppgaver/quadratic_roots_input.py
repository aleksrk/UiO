import sys
from math import sqrt

a = input('Input a value for a:') # commandline prompts 3 times for respective values for a, b and c
a = float(a)

b = input('Input a value for b:')
b = float(b)

c = input('Input a value for c:')
c = float(c)

disc = sqrt( b**2 - 4 * a * c) # pre-calculating discriminant
x1 = ( -b - disc) / (2 * a)
print(f"{x1:.2f}")
x2 = (- b + disc) / (2 * a)
print(f"{x2:.2f}")

"""
kvale@Aleksanders-MBP Oppgaver % python3 quadratic_roots_input.py
Input a value for a:2
Input a value for b:3
Input a value for c:-1
-1.78
0.28
"""
