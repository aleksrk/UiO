#Problem 6.2 Fill arrays; vectorized version
from math import log
import numpy as np

def f(x):
    return np.log(x)

x = np.linspace(1, 10, 101)
y = f(x)
