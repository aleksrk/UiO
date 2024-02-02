import matplotlib.pyplot as plt
import scipy.io as sio
import numpy as np

def matrisetest(matrise, x=194, y=201, tot=38994):

    length = np.shape(matrise)
    wanted_length = (y, x)
    if length != wanted_length:
        print('Feil matrisestørrelse')

def vektortest(vektor, length=194):

    for i in vektor:
        if length != len(i):
            print('Feil vektorstørrelse')

data = sio.loadmat('data.mat')
x = data.get('x')
y = data.get('y')
u = data.get('u')
v = data.get('v')
xit = data.get('xit')
yit = data.get('yit')

matrisetest(x)
matrisetest(y)
matrisetest(u)
matrisetest(v)
vektortest(xit)
vektortest(yit)

for i in range(193):
    delta_x = x[0][i+1]-x[0][i]
    if abs(delta_x-0.5) > 1.0E-6:
        print('Intervallet i x retning er ikke riktig')

for i in range(200):
    delta_y  = y[i+1][0]-y[i][0]
    if abs(delta_y-0.5) > 1.0E-6:
        print('Intervallet i y retning er ikke riktig')

if (abs(y[-1][0]) + abs(y[0][0])) < (100-1.0E6):
    print('Y-koordinatene spenner ikke ut hele diameteren til røret')
