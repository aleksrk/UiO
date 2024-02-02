# FYS-MEK 1110
# Dette er eksempelet med en komet som går i bane rundt sola,
# fra Andreas Goergens forelesning den 4. februar 2020.
# Hvilken komet er dette?? Er det Halleys komet? (periode 75-76 år for passering av Jorda)
# Her skal vi bruke gravitasjonsloven,
# F_G = -gamma *M*m/r**2 û_r
# Akselerasjonen til kometen er gitt ved
# a = - gamma M*r_vektor/r**3
# Cecilie, 11 Feb 2020
# Last update: 30 Jan 2021
# a.c.larsen@fys.uio.no

# Importer nyttige Python-bibliotek
import numpy as np
import matplotlib.pyplot as plt

# Definerer konstanter og variabler vi vil trenge
M     = 1.99e+30	# Solas masse i kg
gamma = 6.673e-11 	# gravitasjonskonstanten i m**3/(kg*s**2)
gammaM = gamma*M 	# ganger sammen disse konstantene med det samme, sparer litt regne-tid i løkka seinere
R     = 1.5e+11		# Avstand fra massesenter til kometen til massesenter til sola (som er origo)
r0 = R*np.array([1., 0.])	# initiell r-vektor, tatt som R ganget med enhetsvektoren i x-retning
# Her lager vi start-hastigheten ved t0 = 0 s
v0_abs = 1.e+4	# absoluttverdien av hastighetsvektoren ved t=0s. Med denne blir banen en ellipse
#v0_abs = 3.e+4	# absoluttverdien av hastighetsvektoren ved t=0s. Med denne blir banen sirkelformet
#v0_abs = 4.5e+4	# absoluttverdien av hastighetsvektoren ved t=0s. Med denne forsvinner kometen ut av solsystemet
# v0 er definert til kun å ha en komponent i y-retn. ved t=0s
v0 = v0_abs*np.array([0., 1.])   # np.array([0, 1]) er enhetsvektoren i y-retning
time = 60*60*24*365*1.5	# maximum tid vi ser på i sekunder: 60 sekunder * 60 minutter * 24 timer * 365 dager * 15 = 1.5 år
dt = 10000.	# tidssteg i sekunder - pass på at det er lite nok! Dette må sees relativt til størrelsesordenen på hastighetsvektoren

# Her bruker vi numpys ceil-funksjon for å bestemme antall elementer vi vil ha i vektorene og matrisene.
# "The ceil of a scalar b is the smallest integer i such that i>= b"
# https://www.geeksforgeeks.org/numpy-ceil-python/
n = int(np.ceil(time/dt))

# Nå definerer vi en vektor for tiden t,  og matriser for posisjonen r (x- og y-komponent ved tid t),
# hastigheten v (x- og y-komponent ved tid t), og akselerasjonen a (x- og y-komponent ved tid t)
# n rader, to kolonner:
# r = [x0, y0]
#     [x1, y1]
#		...
#	  [xn, yn]
t = np.zeros(n)
r = np.zeros((n,2))
# For å sjekke hvordan r ser ut:
#print(r)
v = np.zeros((n,2))
a = np.zeros((n,2))

# Nå bruker vi initialbetingelsene.
# Denne litt kryptiske notasjonen betyr:
# "0": rad 0 -> første rad
# ":": "slice notation", her betyr det bare at vi tar alle kolonner i rad 0
# som er x0, y0 for posisjonen of vx0, vy0 for hastigheten.
r[0, :] = r0 # Startposisjonen til r-vektoren
t[0] = 0	# Denne er forsåvidt satt allerede - vi har jo fylt hele vektoren med nuller da vi definerte den!
v[0, :] = v0	# Initialhastighet
a[0, :] = 0	# initial-akselerasjon

# Nå er vi klare for å kjøre ei løkke og regne ut alt vi vil ha:
for i in range (0,n-1):
	# Først regner vi ut normen (absoluttverdien) til r-vektoren for hvert steg:
	r_norm = np.linalg.norm(r[i, :])
	a[i, :] = -gammaM*r[i, :]/r_norm**3 # akselerasjonen til kometen
	v[i+1, :] = v[i, :] + a[i, :]*dt # hastigheten
	# Vi bruker Euler-Cromer-metoden for å beregne posisjonsvektoren
	r[i+1, :] = r[i, :] + v[i+1, :]*dt
	t[i+1] = t[i] + dt # her inkrementerer vi tiden

print(a)

"""
# Funksjonen subplots er veldig kjekk!
# Se https://matplotlib.org/api/_as_gen/matplotlib.pyplot.subplots.html
fig, ax = plt.subplots(nrows=3, figsize=(6, 7))
# r[:i,0]: Første kolonne (0) som da er x-komponenten, for alle tider 0->i
# r[:i,1]: Andre kolonne (1) som da er y-komponenten, for alle tider 0->i
# Husk at vi har laget en matrise med n rader, en for hver tid,
# og 2 kolonner, en for x-komponenten og en for y-komponenten
ax[0].plot(r[:i, 0], r[:i, 1])
ax[0].set_xlabel("Posisjon x [m]")
ax[0].set_ylabel("Posisjon y [m]")
ax[0].axis('equal') # For å ha like akser
# Her plotter vi de to hastighetskomponentene for alle tider
ax[1].plot(t[:i], v[:i, 0])
ax[1].plot(t[:i], v[:i, 1])
ax[1].set_xlabel('Tid t [s]')
ax[1].set_ylabel('v$_x$, v$_y$ [m/s]')
#ax[1].set_title('Hastighetskomponenter v$_x$ og v$_y$')
# Putte på en boks med info om hva grafene er for noe
ax[1].legend(["v$_x$", "v$_y$"])

# Her plotter vi de to akselerasjonskomponentene for alle tider
ax[2].plot(t[:i], a[:i, 0])
ax[2].plot(t[:i], a[:i, 1])
ax[2].set_xlabel('Tid t [s]')
ax[2].set_ylabel('a$_x$, a$_y$ [m/s$^2$]')
#ax[2].set_title('Akselerasjonskomponenter a$_x$ og a$_y$')
# Putte på en boks med info om hva grafene er for noe
ax[2].legend(["a$_x$", "a$_y$"])
# tight_layout() sørger for at ting ikke blir skrevet oppå hverandre.
# tight_layout() funker kanskje ikke for eldre Python 3-versjoner - isåfall, kommenter den ut
fig.tight_layout()
plt.show()
"""
