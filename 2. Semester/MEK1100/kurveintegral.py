
#Vi har en kurve i rommet rvektor(t) = ti + tj + 2t^3/2k, t mellom [0, 1]

import numpy as np
import sympy as sp
import matplotlib.pyplot as plt
from sympy.vector import CoordSys3D

#Start med å plotte kurven i 3D rom. Dette kan man gjøre med matplotlib på følgende måte

ax = plt.axes(projection='3d')
ti = np.linspace(0, 1, 100)
ax.plot3D(ti, ti, 2*ti**1.5)

#Lag et koordinatsystem og posisjonsvektoren r

t = sp.Symbol('t', real=True)
N = CoordSys3D('N')
r = t*N.i + t*N.j + 2*t**sp.Rational(3,2)*N.k

#Finn så den deriverte av vektor r med hensyn på t

drdt = r.diff(t, 1)
print(drdt)

#Integrer for å finne buelengden

sp.integrate(sp.sqrt(drdt.dot(drdt)), (t, 0, 1))
