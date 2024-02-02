# Problem 2.3 Population growth
from math import e
B = 50000 # carrying capacity
C = B/5000 - 1 # constant
t = 24 # time in hours
k = 0.2 # constant

final_population = B / ( 1 + C *  e**( (-k) * t )  )
print(f"The population after {t} hours is {final_population:.2f}")

"""
kvale@Aleksanders-MacBook-Pro Oppgaver % python3 population.py
The population after 24 hours is 46552.00
"""

















































































#du er en fitte
