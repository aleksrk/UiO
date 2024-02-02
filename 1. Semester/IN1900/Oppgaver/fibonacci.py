#Problem A.2 Solve a difference equation numerically

x = [1,1]

for i in range(2,15):
    x.append(x[i-1] + x[i-2])

print(x)

"""
Kjøreeksempel:
kvale@Aleksanders-MBP Oppgaver % python3 fibonacci.py
[1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610]
"""
