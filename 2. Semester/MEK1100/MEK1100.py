#KONTUR OG PILPLOTT
import numpy as np
import matplotlib.pyplot as plt
N = 40
I = np.linspace(-1 , 1, 40)
x, y = np.meshgrid(I, I, indexing='ij') #indexing gjør at x varierer i y retning, og y i x retning, skjønte ingenting av det men whatevs

CS = plt.contour(x, y, x**2+y**2) # contour plotter sirklene i guess?
plt.clabel(CS, inline= 1, fontsize= 10, fmt='%1.2f', colors='k') # setter navn på sirklene

z = (0.1, 0.4, 0.8, 1.3) #introduserer z for egendefinert spacing mellom sirkler, dvs hva T0 er for hver sirkel.
CS2 = plt.contour(x, y, x**2+y**2, z)
plt.clabel(CS2, inline= 1, fontsize= 10, fmt='%1.2f', colors='k')

plt.contourf(x, y, x**2+y**2, 10) # fylte konturplott med fancy lgbt friendly farger


plt.quiver(x, y, x**2+y**2)
plt.quiver(x[::2, ::2], y[::2, ::2], x[::2, ::2]**2, y[::2, ::2]**2)

plt.quiver(x[::2, ::2], y[::2, ::2], -y[::2, ::2], x[::2, ::2])
plt.gca().axis('equal')
plt.show()
