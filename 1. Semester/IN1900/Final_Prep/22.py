import numpy as np

def midpoint(f, x, h):

    fval = f(x)
    derivative = (f(x+h) - f(x - h)) / 2*h

    return fval, derivative

func = lambda x: np.cos(x)
x = 0
h = 0.001

f, f_derived = midpoint(func, x , h)
