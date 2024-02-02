#Problem 8.9 A class for coordinates
from math import sqrt
from decimal import *

class Coords:

    def __init__(self, x, y, z):
        self.x, self.y, self.z = x, y, z

    def __str__(self):
        return f'({self.x:.2f}, {self.y:.2f}, {self.z:.2f})'

    def __len__(self):
        return 3

    def __abs__(self):
        return Decimal(sqrt(self.x**2 + self.y**2 + self.z**2)).quantize(Decimal('.01'))

    def __add__(self, other):
        return Coords(self.x + other.x, self.y + other.y, self.z + other.z)

    def __sub__(self, other):
        return Coords(self.x - other.x, self.y - other.y, self.z - other.z)


sqrt3 = sqrt(3)
close = Coords(1/sqrt3, 1/sqrt3, 1/sqrt3)
far = Coords(3/sqrt3, 15/sqrt3, 21/sqrt3)

print(close)
print(far)

print(f"The class represents coordinates in {len(close)} dimensions")
print(f"The distance from the centre to the point close is {abs(close)}")
print(f"The distance from the centre to the point far is {abs(far)}")

further = close + far
print(f"The coordinates further are at {further}")
distance = abs(far - close)
print(f"The distance from far to close is {distance}")
centre = further - further
print(f"The coordinates at the centre are {centre}")

"""
kvale@Aleksanders-MBP Oppgaver % python3 Coords.py
(0.58, 0.58, 0.58)
(1.73, 8.66, 12.12)
The class represents coordinates in 3 dimensions
The distance from the centre to the point close is 1.00
The distance from the centre to the point far is 15.00
The coordinates further are at (2.31, 9.24, 12.70)
The distance from far to close is 14.14
The coordinates at the centre are (0.00, 0.00, 0.00)
"""
