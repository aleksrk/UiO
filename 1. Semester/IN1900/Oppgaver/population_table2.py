# Problem 3.8 Nested Lists
from math import e
B = 50000 # carrying capacity
C = B/5000 - 1 # constant
k = 0.2 # constant
n = 12

t = [(48 / n)*i for i in range(0, n+1)]
N = []

for i in range(len(t)):
    N.append(B / ( 1 + C * e**( (-k) * t[i])))

tN1 = [t, N]

for i in range(len(t)):
    print(int(tN1[0][i]), int(tN1[1][i]))

tN2 = []

for i in range(len(t)):
    tN2.append([t[i], N[i]])

for t, N in tN2:
    print(int(t), int(N))

"""
kvale@Aleksanders-MBP Oppgaver % python3 population_table2.py
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
