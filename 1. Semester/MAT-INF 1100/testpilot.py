#Oppgave 3.
from random import random

antfeil = 0; N = 100000

for i in range(N):
    x = random(); y = random(); z = random()
    res1 = (x + y)*z
    res2 = x*z + y*z
    if res1 != res2:
        antfeil += 1
        x0 = x; y0 = y; z0 = z
        ikkelik1 = res1
        ikkelik2 = res2

print (100.*antfeil/N)
print (x0, y0, z0, ikkelik1 - ikkelik2)
"""
Kjøreeksempel:
30.859
0.6087077776638925  0.9204274878392227  0.06851310883531125  -1.3877787807814457e-17
"""
#Oppgave 3a

"""
Her har vi en for løkke som itererer 100000 ganger
Inni løkken blir det tildelt tilfeldige tall til verdiene av x, y og z
Deretter gjøres to like utregninger på forskjellige måter(den ene er faktorisert)
Systemet tester deretter om disse to utregningene eksakt samme verdier
Dersom det ikke gir samme verdi blir antfeil variablen endret til seg selv + 1
Dvs. at den teller oppover hver gang verdiene ikke er like
Slutten av programmet printer først antall feil i prosent
Deretter printes verdiene i løkken fra den siste iterasjonen hvor verdiene ikke var lik hverandre

Verdiene forteller oss at programmet har bommet på totalt 30859 iterasjoner
Deretter lister den x, y og z verdiene ved siste bom, samt viser verdien av forskjellen mellom de to utregningene
Vi ser at forskjellen er veldig liten, og kan anta at dette skyldes avrundingsfeil av datamaskinen
"""

#Oppgave 3b

antfeil = 0; N = 100000

for i in range(N):
    x = random(); y = random(); z = random()
    res1 = (x + y)*(y + z)
    res2 = x*y + y*y + x*z + y*z
    if res1 != res2:
        antfeil += 1
        x0 = x; y0 = y; z0 = z
        ikkelik1 = res1
        ikkelik2 = res2

print (100.*antfeil/N)
print (x0, y0, z0, ikkelik1 - ikkelik2)
"""
Kjøreeksempel
41.449
0.9390927537484502 0.11459956929701798 0.691321080488163 1.1102230246251565e-16
"""

"""
Antall feil blir vesentlig høyere ved bruk av ny formel
Siden vi har konstantert at feil skyldes avrundingsfeil fra datamaskinen
kan vi også anta at flere beregninger per ledd gir mer grunnlag for avrundingsfeil
Dvs. at ettersom det nye programmet gjør flere beregninger,
Så har vi større sannsynlighet for at det blir avrundingsfeil
"""
