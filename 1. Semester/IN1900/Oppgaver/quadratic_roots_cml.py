import sys
from math import sqrt

a = float(sys.argv[1]) # pull values for a, b and c from commandline while converting input to float
b = float(sys.argv[2])
c = float(sys.argv[3])

disc = sqrt( b**2 - 4 * a * c) # pre-calculating discriminant
x1 = ( -b - disc) / (2 * a)
print(f"{x1:.2f}")
x2 = (- b + disc) / (2 * a)
print(f"{x2:.2f}")

"""
kvale@Aleksanders-MBP Oppgaver % python3 quadratic_roots_cml.py 1 -3 2
1.00
2.00
"""
