"""
import math # importing the math module
r = math.sqrt(2)
import math.sqrt # can specifically import just the sqrt part of the module
r = sqrt(2)
from math import * # import everything in the math module
print(r)
"""

from math import sqrt, exp, pi
m = 0
s = 2
x = 1.0
f = 1/(sqrt(2*pi)*s) * exp(-0.5*((x-m)/s)**2)
print(f)
