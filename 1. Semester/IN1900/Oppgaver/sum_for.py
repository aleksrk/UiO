#Problem 3.4 Errors in Simulation
"""
All three errors exist in the loop statement itself,
i is defined in the start of the loop but never used, should be switched for k which is currently an undefined variable
the range(M) statement will result in the program running from k=0 to k=2, and never computing k=3
there is a paranthesis missing in the satement for s, causing the program to read k^2/2 instead of 1/2k^2
"""
s = 0
M = 3

for i in range(M):
    s += 1/2*k**2
    print (k)
    print (s)
    print(i)

print(s)

"""
kvale@Aleksanders-MBP Oppgaver % python3 sum_for.py
Traceback (most recent call last):
  File "sum_for.py", line 12, in <module>
    s += 1/2*k**2
NameError: name 'k' is not defined
"""
