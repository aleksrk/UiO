import numpy as np
import matplotlib.pyplot as plt

I = np.linspace(-1, 1, 20)

xi, yi = np.meshgrid(I, I, indexing ='ij')
plt.quiver(xi, yi, -yi, xi)
plt.contour(xi, yi, xi**2+y**2, 8)
plt.axis('equal')

import sympy as sp
from sympy.vector import CoordSys3D
x, y = sp.symbols('x,y', real=True)
omega = sp.Symbol('omega', real=True)
N = CoordSys3D('N')
u = -N.x*omega*N.i +  N.y*omega*N.j
r = N.x*N.i + N.y*N.j

r.cross(u)

w = omega*N.k
w.cross(r)

beta =  (sp.exp(-((x-3*sp.pi/4)**2+(y-sp.pi/2)**2)))
        +sp.exp(-((x-3*sp.pi/4)**2+)y-3*sp.pi/2)**2))
        -sp.exp(-((x-3*sp.pi/2)**2+(y-3*sp.pi/2)**2))/2)

I = np.linspace(0, 2*np.pi, 100)
xi, yi = np.meshgrid(I, I, indexing='ij')
betai = sp.lambdify((x, y), beta)(xi, yi)
plt.contourf(xi, yi, betai, 100)

z = (-0.4, -0.2, -0.05, -0.01, 0, 0.01, 0.05, 0.2, 0.4, 0.8)
CS = plt.contour(xi, yi, betai, z)
plt.clabel(CS, inline=1, fontsize=8, fmt='%1.3f', colors='k')

dbeta_x = beta.diff(x, 1)
dbeta_y = beta.diff(y, 1)
u = dbeta_x*N.j - dbeta_y*N.i
print(u)

dbx = sp.lambdify((x, y), dbeta_x)(xi, yi)
dby = sp.lambdify((x, y), dbeta_y)(xi, yi)
CS = plt.contour(xi, yi, betai, 15)
