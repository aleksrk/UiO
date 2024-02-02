# Problem 4.1 Implement a function for population growth
from math import e

def population(t, k, B, C):
    return (B / ( 1 + C * e**( (-k) * t)))

n = 12
t = [(48 / n)*i for i in range(0, n+1)]
N = []

for i in range(len(t)):
    N.append(population(t[i], 0.2, 50000, 9))
    print(int(t[i]), int(N[i]))

"""
kvale@Aleksanders-MBP Oppgaver % python3 pop_func.py
0 5000
4 9912
8 17748
12 27526
16 36580
20 42924
24 46551
28 48389
32 49263
36 49666
40 49849
44 49932
48 49969

"""
