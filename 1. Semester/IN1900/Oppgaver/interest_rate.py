# Problem 2.2
r = 5.0 # interest rate in %
P = 1000 # initial amount
n = 3 # amount of time in years

final_amount = 1000 * (1 + r / 100)**n
print(f"{P} Euros will grow to {final_amount} in {n} years.")

"""
kvale@Aleksanders-MacBook-Pro Oppgaver % python3 interest_rate.py
1000 Euros will grow to 1157.6250000000002 in 3 years.
"""
