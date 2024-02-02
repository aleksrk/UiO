# FYS-MEK 1110
# Dette er eksempelet med en ping-pong-ball som spretter på gulvet,  
# fra Andreas Goergens forelesning den 4. februar 2020.
# Vi ser *ikke* bort fra luftmotstand her, noe som fører til
# at vi får to koblede diff-ligninger for akselerasjonen i x- og y-retning.
# I tillegg modellerer vi normalkraften fra gulvet på ballen som en fjærkraft.
# Vi har følgende krefter som virker (alle er vektorer):
# Luftmotstand F_D = -D |v| v
# Normalkraft N = k(R-y(t)), y(t) < R hvor R = ballens radius,
# eller       N = 0, y(t) >= R
# Gravitasjon G = -mg
# Vi ser her bort fra friksjon mellom gulv og ball.
# Konstanter er tatt fra Andreas' pingpong.py
# Cecilie, 11 Feb 2020
# Last update: 30 Jan 2021
# a.c.larsen@fys.uio.no

# Importer nyttige Python-bibliotek
import numpy as np
import matplotlib.pyplot as plt

# Definerer konstanter og variabler vi vil trenge
# Jeg har stort sett brukt de konstantene fra pingpong.py, 
# det samsvarer ikke helt med tallene vist i Matlab-koden fra forelesnings-sliden.
g = 9.81 	# tyngdeakselerasjon i m/s^2
m = 0.0027	# masse til ballen i kg
v_T = 5.0	# terminalhastigheten i m/s.
D = m*g/(v_T**2)	# luftmotstandskoeffisienten i kg/m
R = 0.02	# radius til ballen i m (2 cm)
k = 100.0	# fjærkonstanten i N/m
Fnet = 0.	# netto kraft på ballen. Den skal vi regne ut seinere i ei løkke
F_D = 0.	# luftmotstanden på ballen. Den skal vi også regne ut seinere i ei løkke. Antar turbulente forhold
F_N = 0.	# normalkraften på ballen. Den skal vi også regne ut seinere i ei løkke. Modelert som en fjærkraft
F_G = 0.	# gravitasjonskraften på ballen. Den skal vi også regne ut seinere i ei løkke.
x0 = 0.		# initiell posisjon i x-retning, definert til å være i null m
y0 = 0.8	# initiell høyde (y-retning), kalt h på forelesning  i m. Vi kan teste forskjellige start-høyder om vi vil
r0 = np.array([x0, y0])	# initiell r-vektor, med sin x- og y-komponent
# Her lager vi start-hastigheten ved t0 = 0 s
v0 = np.array([1.5, -0.5])  
time = 5.	# maximum tid vi ser på i sekunder 
dt = 0.001	# tidssteg i sekunder - pass på at det er lite nok!

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
	# Først regner vi ut normalkraften. Husk at r[i,1] er y-komponenten til posisjonen ved rad ("tid") i
	if r[i, 1]<R:
		F_N = k*(R-r[i, 1])*np.array([0, 1]) # Enhetsvektoren i y-retning = np.array([0,1])
	else: 
		F_N = [0, 0]
	F_D = -D*np.linalg.norm(v[i, :])*v[i, :]
	F_G = -m*g*np.array([0, 1]) # Enhetsvektoren i y-retning = np.array([0,1])
	Fnet = F_N + F_D + F_G 	# Nettokraften. Fortegnene er satt i linjene over, der F_D pg F_G er regnet ut.
	#Fnet = F_N + F_G 	# Nettokraften uten luftmotstand
	a[i, :] = Fnet/m 	    # akselerasjonen
	v[i+1, :] = v[i, :] + a[i, :]*dt # hastigheten
	# Vi bruker Euler-Cromer-metoden for å beregne posisjonsvektoren
	r[i+1, :] = r[i, :] + v[i+1, :]*dt 
	t[i+1] = t[i] + dt # her inkrementerer vi tiden


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
ax[2].plot(t[:i], a[:i, 1]) #,"." for markers
ax[2].set_xlabel('Tid t [s]')
ax[2].set_ylabel('a$_x$, a$_y$ [m/s$^2$]')
#ax[2].set_title('Akselerasjonskomponenter a$_x$ og a$_y$')
# Putte på en boks med info om hva grafene er for noe
ax[2].legend(["a$_x$", "a$_y$"])
# tight_layout() sørger for at ting ikke blir skrevet oppå hverandre.
# tight_layout() funker kanskje ikke for eldre Python 3-versjoner - isåfall, kommenter den ut
fig.tight_layout()
plt.show()




