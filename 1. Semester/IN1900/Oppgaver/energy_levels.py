#Problem 2.6(langtangen) Compute energy levels in an atom
from math import e

m_e = 9.1094 * 1e-31
ec = 1.6022 * 1e-19
ep = 8.852 * 1e-12
h = 6.6261 * 1e-34
print(m_e)

for n in range(1, 21):
    e_n = -( (m_e * e**4) / (8 * ep**2 * h**2) ) * (1 / n**2)
    print(f"The energy level of {n} is {e_n}")
