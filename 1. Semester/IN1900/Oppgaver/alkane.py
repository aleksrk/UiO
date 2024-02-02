# Problem 3.11 Molar Masses of Alkanes
n = 2 # initial number of Carbon atoms
Mc = 12.011 #g/Mol
Mh = 1.0079 #g/Mol

while n <= 9:
    m = 2*n + 2
    Cm = n*Mc + m*Mh
    print(f'M(C{n}H{m}) = {Cm:.3f} g/mol')
    n += 1

"""
kvale@Aleksanders-MBP Oppgaver % python3 alkane.py
M(C2H6) = 30.069 g/mol
M(C3H8) = 44.096 g/mol
M(C4H10) = 58.123 g/mol
M(C5H12) = 72.150 g/mol
M(C6H14) = 86.177 g/mol
M(C7H16) = 100.203 g/mol
M(C8H18) = 114.230 g/mol
M(C9H20) = 128.257 g/mol
"""
