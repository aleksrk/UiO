# FYS-MEK 1110
# Dette er slagskip-eksempelet fra Andreas Goergens 
# forelesning den 3. februar 2020
# Et krigsskip (eller slagskip) skyter med kanon som har en konstant initialhastighet v0.
# Vi skal bestemme bevegelsesligningene til kanonkula, gitt en vinkel (retning) paa kanonkula
# relativt til havoverflaten. Vi ser bort fra luftmotstand.
# Vi har foelgende uttrykk:
# Posisjon i x-retning: x(t) = v0t cos alpha
# Posisjon i y-retning: y(t) = v0t sin alpha - 0.5 gt**2
# Eksempelet fra forelesningen var programmert i Matlab.
# Denne versjonen regner ut for gradene alpha = 10...80 grader i steg paa 5 grader.
# Cecilie, 29 Jan 2021
# Last update: 29 Jan 2021
# a.c.larsen@fys.uio.no

# Importer nyttige Python-bibliotek
import numpy as np
import matplotlib.pyplot as plt

# Definer konstanter og variabler vi vil trenge
g = 9.81 	# tyngdeakselerasjon i m/s^2
m = 0.20		# masse til kula i kg
Fnet = 0.	# netto kraft paa kula. Den skal vi regne ut seinere i ei loekke
x0 = 0.		# initiell posisjon i x-retning, definert til aa vaere i null m
y0 = 0.		# initiell hoeyde (y-retning), kalt h paa forelesning  i m. 
v0norm = 20.0; # Her har vi satt absoluttverdien til hastighetsvektoren ved t=0, dvs |v0| = v0norm = sqrt(vx0**2 + vy0**2)
time = 10.	# maximum tid vi ser paa i sekunder
dt = 0.001	# tidssteg i sekunder

# Her bruker vi numpys ceil-funksjon for aa bestemme antall elementer vi vil ha i vektorene og matrisene.
# "The ceil of a scalar b is the smallest integer i such that i>= b"
# https://www.geeksforgeeks.org/numpy-ceil-python/
n = int(np.ceil(time/dt))

# Naa definerer vi en vektor for tiden t,  og matriser for posisjonen r (x- og y-komponent ved tid t), 
# hastigheten v (x- og y-komponent ved tid t), og akselerasjonen a (x- og y-komponent ved tid t)
# for en gitt vinkel alpha_grader
# n rader, to kolonner, alpha_grader:
# r = [x0, y0, alpha_grader] (n=0)
#     [x1, y1, alpha_grader] (n=1)
#		...
#	  [xn, yn, alpha_grader] (n=n)

t = np.zeros(n)
r = np.zeros((n,3))
print(r)
v = np.zeros((n,3))
a = np.zeros((n,3))

# La oss ha ting klare for plotte det vi skal regne ut. 
# Funksjonen subplots er veldig kjekk!
# Se https://matplotlib.org/api/_as_gen/matplotlib.pyplot.subplots.html
fig, ax = plt.subplots(nrows=2, figsize=(12, 8))
ax[0].set_xlabel("Posisjon x [m]")
ax[0].set_ylabel("Posisjon y [m]")
ax[1].set_xlabel('Tid t [s]')
ax[1].set_ylabel('Hastighet [m/s]')

# Vinkelen alpha er naa en variabel, vi skal regne ut banen for mange vinker (relativt til havoverflaten).
alpha_grader = [10, 20, 30, 35, 40, 45, 50, 55, 60, 70, 80]
j = 0 #telle-indeks for vinkelen alpha
print(len(alpha_grader))
while j < len(alpha_grader):
	alpha = np.radians(alpha_grader[j]) # vinkelen i radianer. Denne numpy-funksjonen gjoer om grader til radianer
	r0 = np.array([x0,y0,alpha_grader[j]])	# initiell r-vektor, med sin x- og y-komponent, for en gitt vinkel 
	# Her lager vi start-hastigheten, med sin startkomponent v0x = v0norm*cos(alpha) 
	# og y-komponent v0y =v0norm*sin(alpha) (husk at t0 = 0 s)
	v0 = np.array([v0norm*np.cos(alpha),v0norm*np.sin(alpha),alpha_grader[j]])  

	# Naa bruker vi initialbetingelsene. 
	# Denne litt kryptiske notasjonen betyr:
	# "0": rad 0 -> foerste rad
	# ":": "slice notation", her betyr det bare at vi tar alle kolonner i rad 0
	# som er x0, y0, alpha_grader for posisjonen og vx0, vy0, alpha_grader for hastigheten.
	r[0,:] = r0 # Startposisjonen til r-vektoren
	t[0] = 0	# Denne er forsaavidt satt allerede - vi har jo fylt hele vektoren med nuller da vi definerte den!
	v[0,:] = v0	# Initialhastighet
	a[0,:] = [1.,2.,alpha_grader[j]]	# initial-akselerasjon
	
	# Naa er vi klare for aa kjoere ei loekke og regne ut alt vi vil ha:
	i = 0 # start-indeks
	# Vi maa ha foelgende betingelser: 
	# 1. Posisjonen i y-retning skal vaere stoerre enn eller lik null, ellers er kula under vann (vi har definert havoverflaten som y = 0m)
	# 2. Telleindeksen maa ikke bli for stor, vi maa holde oss innenfor stoerrelsen til vektorene og matrisene
	while r[i,1]>=0.0 and i<n:
		Fnet = -m*g*np.array([0,1,alpha_grader[j]]) # Enhetsvektoren i y-retning = np.array([0,1])
		a[i,:] = Fnet/m 	# akselerasjonen
		v[i+1,:] = v[i,:] + a[i,:]*dt # hastigheten
		# Vi bruker Euler-Cromer-metoden for aa beregne posisjonsvektoren
		r[i+1,:] = r[i,:] + v[i+1,:]*dt 
		t[i+1] = t[i] + dt # her inkrementerer vi tiden
		i += 1 # husk aa inkrementere loekke-indeksen for akselerasjon, hastighet, posisjon!

	# Her legger vi til alle grafene vi regner ut.
	# r[:i,0]: Foerste kolonne (0) som da er x-komponenten, for alle tider 0->i, for vinkel alpha_grader
	# r[:i,1]: Andre kolonne (1) som da er y-komponenten, for alle tider 0->i
	# Husk at vi har laget en matrise med n rader, en for hver tid, 
	# og 3 kolonner, en for x-komponenten og en for y-komponenten, og en for alpha_grader
	line, = ax[0].plot(r[:i, 0], r[:i, 1])
	line.set_label("%s grader" % alpha_grader[j])
	ax[0].legend()
	
	# Her plotter vi de to hastighetskomponentene for alle tider
	ax[1].plot(t[:i], v[:i, 0])
	ax[1].plot(t[:i], v[:i, 1])

	j += 1 #husk aa inkrementere loekkeindeksen for vinkelen!

#ax[1].set_title('Hastighetskomponenter v$_x$ og v$_y$ [m/s]')

# tight_layout() soerger for at ting ikke blir skrevet oppaa hverandre.
# tight_layout() funker kanskje ikke for eldre Python 3-versjoner - isaafall, kommenter den ut
fig.tight_layout()

plt.show()






