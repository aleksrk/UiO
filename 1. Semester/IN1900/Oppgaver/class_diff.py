# Problem 8.7 Numerical approximations of the derivative
import numpy as np
import matplotlib.pyplot as plt

class Diff:

    def __init__(self, f):
        self.f = f

    def diff1(self, x, h):
        f = self.f
        return (f(x+h) - f(x)) / h

    def diff2(self, x, h):
        f = self.f
        return (f(x+h) - f(x-h)) / (2*h)

    def diff3(self, x, h):
        f = self.f
        return (-f(x+2*h) + 8*f(x+h) - 8*f(x-h) + f(x-2*h)) / (12*h)

func = lambda x: np.sin(2*np.pi * x)
fderived = lambda x: 2*np.pi * np.cos(2*np.pi*x)
f = Diff(func)

x = np.linspace(-1,1,1000)
h_val = (0.9, 0.6, 0.3, 0.1)
d = (f.diff1, f.diff2, f.diff3)
dstrings = ('diff1', 'diff2', 'diff3')
i = 1

for method, name in zip(d, dstrings):

    plt.subplot(2,2,i)
    i += 1

    for h in h_val:
        plt.plot(x, method(x, h), "--", label=f'{name}, h={h}')

    plt.plot(x, fderived(x), label='actual value'  )
    plt.legend(loc='lower right', fontsize=5)

plt.show()

"""
kvale@Aleksanders-MBP Oppgaver % python3 class_diff.py
"""
