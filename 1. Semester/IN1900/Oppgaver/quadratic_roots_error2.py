import sys
from math import sqrt

a = input('Input a value for a: ') # commandline prompts 3 times for respective values for a, b and c
a = float(a)

b = input('Input a value for b: ')
b = float(b)

c = input('Input a value for c: ')
c = float(c)

try:
    disc = sqrt( b**2 - 4 * a * c)
except ValueError:
    print('You have entered values for the function with no real solution.')
    print('The program will exit, please try again with different values for a, b or c.')
    exit()
 # pre-calculating discriminant
x1 = ( -b - disc) / (2 * a)
print(f"{x1:.2f}")
x2 = (- b + disc) / (2 * a)
print(f"{x2:.2f}")

"""
kvale@Aleksanders-MBP Oppgaver % python3 quadratic_roots_error2.py
Input a value for a: 1
Input a value for b: 1
Input a value for c: 1
You have entered values for the function with no real solution.
The program will exit, please try again with different values for a, b or c.
kvale@Aleksanders-MBP Oppgaver % python3 quadratic_roots_error2.py
Input a value for a: 1
Input a value for b: -3
Input a value for c: 2
1.00
2.00
"""
