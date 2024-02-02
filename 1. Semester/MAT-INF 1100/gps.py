# Oppgave 1
import matplotlib.pyplot as plt
import numpy as np

def a(t, v):
    a = np.zeros(len(t))
    for i in range(1, len(t)):
        a[i] = ( (v[i]-v[i-1]) / (t[i] - t[i-1]) )

    return a

def s(t, v):
    d = np.zeros(len(t))
    for i in range(1, len(t)):
        d[i] = (d[i-1] + (v[i]+v[i-1])/2 * ( t[i] -  t[i-1] ))
    return d


t = []
v = []
infile = open('running.txt','r')
for line in infile:
    tnext, vnext = line.strip().split(',')
    t.append(float(tnext))
    v.append(float(vnext))
infile.close()

plt.subplot(2,1,1)
plt.plot(t, a(t,v), label='Akselerasjon over tid')
plt.xlabel('Tid')
plt.ylabel('Akselerasjon')
plt.legend()

plt.subplot(2,1,2)
plt.plot(t, s(t,v), label='Strekning over tid')
plt.xlabel('Tid')
plt.ylabel('Strekning')
plt.legend()
plt.show()

print(s(t,v)[-1])

"""
Løpeturen var 20659 meter lang.

"""
