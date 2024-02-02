class F:

    def __init__(self, a, b, c, d):
        self.a, self.b, self.c, self.d = a, b, c, d

    def __call__(self, x):
        return self.a * x**3 + self.b * x**2 + self.c * x + self.d

    def __str__(self):
        return f'{self.a}*x^3 + {self.b}*x^2 + {self.c}*x + {self.d}'


f = F(a=0.0, b=1.0, c=2.0, d=0.0)

x = 2.0

print(f(x))     # Skal skrive ut funksjonsverdien (8.0)

f = F(1.0, 2.0, 3.0, 0.0)
print(f)
