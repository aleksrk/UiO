import matplotlib.pyplot as plt
import scipy.io as sio
import numpy as np

def kurveintegral(r):
    x1, y1 = r[0], r[1]
    x2, y2 = r[2], r[3]
    s1, s2, s3, s4 = 0, 0, 0, 0
    for i in range(x1, x2+1):
        s1 += u[y1][i] * 0.5
        s2 -= u[y2][i] * 0.5
    for i in range(y1, y2+1):
        s3 += v[i][x2] * 0.5
        s4 -= v[i][x1] * 0.5

    return s1, s2, s3, s4

def flateintegral(r):
    x1, y1 = r[0], r[1]
    x2, y2 = r[2], r[3]
    sum = 0
    for i in range(y1, y2+1):
        for j in range(x1, x2+1):
            sum += curl[i][j]*dx*dy
    return sum

def fluks(r):
    x1, y1 = r[0], r[1]
    x2, y2 = r[2], r[3]
    s1, s2, s3, s4 = 0, 0, 0, 0
    for i in range(x1, x2+1):
        s1 -= v[y1][i] * 0.5
        s2 += v[y2][i] * 0.5
    for i in range(y1, y2+1):
        s3 += u[i][x2] * 0.5
        s4 -= u[i][x1] * 0.5

    return s1, s2, s3, s4

data = sio.loadmat('data.mat')

x = data.get('x')
y = data.get('y')
u = data.get('u')
v = data.get('v')
xit = data.get('xit')
yit = data.get('yit')

dx, dy = 0.5, 0.5
dvdx = np.gradient(v, dx, axis=1)
dudy = np.gradient(u, dy, axis=0)
curl = dvdx - dudy
rektangler = [[34, 159, 69, 169], [34, 84, 69, 99], [34, 49, 69, 59]]

print('Kurveintegral')

for i in range(3):
    s1, s2, s3, s4 = kurveintegral(rektangler[i])
    sum = s1 + s2 + s3 + s4
    print(sum, s1, s2, s3, s4)

print('\nFlateintegral')

for i in range(3):
    sum = flateintegral(rektangler[i])
    print(sum)

print('\nFluks')

for i in range(3):
    s1, s2, s3, s4 = fluks(rektangler[i])
    sumfluks = s1 + s2 + s3 + s4
    print(sumfluks, s1, s2, s3, s4)

"""
kvale@Battlestation MEK1100 % python3 sirkulasjon.py
Kurveintegral
2695.5140926958125 70100.52387861427 -68332.85609978675 266.2735761585869 661.5727377096991
-60976.60016211557 198.47559740489203 -61243.46477849595 300.2166102701169 -231.82759129461652
9.521016433027981 5133.347850903836 -5410.039721925995 207.91001043390142 78.30287702128548

Flateintegral
2621.55869628381
-61482.540989332876
-12.214333864213106

Fluks
104.8526049082102 1556.8679439413959 -2059.6771847938708 21664.567474322168 -21056.905628561482
-6476.93918209796 -5187.564033067891 -4074.0522144394345 14782.532896182345 -11997.85583077298
-124.56866604496213 -195.5701479258336 284.9436464350764 1536.8217966413547 -1750.7639611955597
"""
