#Problem 6.1 Fill Arrays; loop version
from math import log
import numpy as np

def f(x):
    return np.log(x)

x = np.zeros(101)
y = np.zeros(101)

for i in range(0, 101):
    x[i] = 1 + 0.09*i
    y[i] = f(x[i])
