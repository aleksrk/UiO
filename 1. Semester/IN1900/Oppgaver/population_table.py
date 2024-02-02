# Problem 3.7 Table showing population growth
from math import e
B = 50000 # carrying capacity
C = B/5000 - 1 # constant
k = 0.2 # constant

n = 12
t = []
N = []

for i in range(n + 1):
    t.append(int(48/n*i))
    N.append(int(B / ( 1 + C * e**( (-k) * t[i]))))

for i in range(len(t)):
    print(t, N)

"""
kvale@Aleksanders-MBP Oppgaver % python3 population_table.py
0        5000
4        9912
8       17748
12       27526
16       36580
20       42924
24       46551
28       48389
32       49263
36       49666
40       49849
44       49932
48       49969
"""
