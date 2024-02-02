#Problem 3.5 Sum as a while loop

s = 0
M = 3
for k in range(1, M+1):
    s += 1/(2*k)**2

print(s)

s2 = 0
k = 0
while k <= 2:
    k += 1
    s2 += 1/(2*k)**2

print(s2)

if s2 == s:
    print("s2 is equal to s")

"""
kvale@Aleksanders-MBP Oppgaver % python3 sum_while.py
0.3402777777777778
0.3402777777777778
s2 is equal to s
"""
