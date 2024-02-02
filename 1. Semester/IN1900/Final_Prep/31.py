class F:

    def __init__(self, a, b, c):
        self.a, self.b, self.c = a, b, c

    def __call__(self, x):
        return self.a * x**2 + self.b * x + self.c

    def __str__(self):
        return f'{self.a} * x^2 + {self.b} * x + {self.c}'


f = F(a=1.0, b=2.0, c=0.0)

x = 2.0

print(f(x))     # Skal skrive ut funksjonsverdien (8.0)

print(f)
