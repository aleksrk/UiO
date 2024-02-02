import numpy as np



x = np.linspace(0,2,21)

y = np.zeros(len(x))

f = lambda x: x**2 -4



for x_ in x:

    y.append(f(x_))
