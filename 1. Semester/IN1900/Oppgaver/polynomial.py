#Problem 9.2 Implement Polynomials as a Class

class Quadratic:

    def __init__(self, q):
        self.c0 = q[0]
        self.c1 = q[1]
        self.c2 = q[2]

    def __call__(self, x):
        return self.c0 + self.c1 * x + self.c2 * x**2

    def __str__(self):
        return f'{self.c0} + {self.c1}x + {self.c2}x**2'

coeff = (1,3,2)
a = Quadratic(coeff)
print(a)
b = a(1)
c = a(2)
print(b, c)


class Cubic(Quadratic):

    def __init__(self, q):
        Quadratic.__init__(self, q)
        self.c3 = q[3]

    def __call__(self, x):
        return Quadratic.__call__(self, x) + self.c3* x**3

    def derivative(self):
        df = (self.c1, 2*self.c2, 3*self.c3)
        return Quadratic(df)
        
    def __str__(self):
        return f'{self.c0} + {self.c1}x + {self.c2}x**2 + {self.c3}x**3'

coeff2 = (1,3,2,4)
cube = Cubic(coeff2)
print(cube)
d = cube(1)
e = cube(2)
print(d, e)
cubeder = cube.derivative()
print(cubeder)

"""
kvale@Aleksanders-MBP Oppgaver % python3 polynomial.py
1 + 3x + 2x**2
6 15
1 + 3x + 2x**2 + 4x**3
10 47
3 + 4x + 12x**2
"""
