import numpy as np
import matplotlib.pyplot as plt

a = 1
b = 3
t = np.linspace(-b,b,1000)

xfunc = lambda t, a: a*np.arcsinh(t/a)
yfunc = lambda t, a: np.sqrt(t**2 + a**2)

x = xfunc(t, a)
y = yfunc(t, a)

plt.plot(x, y)
plt.xlabel('x')
plt.ylabel('y')
plt.show()
