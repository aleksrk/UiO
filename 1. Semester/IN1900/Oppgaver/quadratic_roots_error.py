import sys
from math import sqrt

try:
    a = float(sys.argv[1])
except IndexError:
    a = input('No command line argument for a, input a new value for a: ')
    a = float(a)

try:
    b = float(sys.argv[2])
except IndexError:
    b = input('No command line argument for b, input a new value for b: ')
    b = float(b)

try:
    c = float(sys.argv[3])
except IndexError:
    c = input('No command line argument for c, input a new value for c: ')
    c = float(c)

disc = sqrt( b**2 - 4 * a * c) # pre-calculating discriminant
x1 = ( -b - disc) / (2 * a)
print(f"{x1:.2f}")
x2 = (- b + disc) / (2 * a)
print(f"{x2:.2f}")

"""
kvale@Aleksanders-MBP Oppgaver % python3 quadratic_roots_error.py 1 -3 2
1.00
2.00
"""
