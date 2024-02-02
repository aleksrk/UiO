#1.2
def count(dna, base):
    i = 0
    for j in range(len(dna)):
        if dna[j] == base:
            i += 1
    return i
ba = 'sssssss'
print(count('GATA', ba))
#1.3
"""
a = [[4,5], [0,1], 2, [2,0.5], 4, 3, [5,6,7]]
b =[]
for e in a:
    s = 0
    for number in e:
        s += number
    b.append(s)

print(b)
"""
#1.4
import numpy as np


def euler(rhs, u0, T, n=100):
    t = np.linspace(0, T, n+1)
    dt = T/n
    u = np.zeros_like(t)
    u[0] = u0
    for i in range(1, n+1):
        u[i] = u[i-1] + dt*rhs(u[i-1], t[i-1])
    return u,t

u, t = euler(lambda y, t: -0.5*y, 1.0, 5)
print(u, t)

#1.5

stars_data = {}


with open('stars.txt') as infile:
    infile.readline()

    for line in infile:
        w = line.split()
        data = {'dist': w[1], 'bright':w[2], 'lum':w[3]}
        stars_data[w[0]] = data



print(stars_data['Sirius_A'])

#1.8
def bisection(f, a, b, eps=1e-5):
    fa = f(a)
    if fa*f(b) > 0:
        print(f'No unique root in [{a},{b}]')
        return None

    while b-a > eps:
        m = (a + b)/2.0
        fm = f(m)
        if fa*fm <= 0:
            b = m  # root is in left half of [a,b]
        else:
            a = m  # root is in right half of [a,b]
            fa = fm
    return m

x = bisection(lambda x: x**3+2*x-1,-10,10)
print(x)

#1.9

def f(arg):
    n = len(arg)

    for i in range(n):
        for j in range(n-i-1):
            if arg[j] > arg[j+1] :
                arg[j], arg[j+1] = arg[j+1], arg[j]

    return arg

list = [0,3,7,1,9,2]
print(f(list))
