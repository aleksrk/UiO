import matplotlib.pyplot as plt
import scipy.io as sio
import numpy as np


def seperate(matrix):

    ma = np.copy(matrix.transpose())
    mw = np.copy(matrix.transpose())
    for i in range(len(ma)):
        for j in range(len(ma[i])):
            if y[j][0] < yit[0][i]:
                ma[i][j] = float('nan')
    for i in range(len(mw)):
        for j in range(len(mw[i])):
            if y[j][0] > yit[0][i]:
                mw[i][j] = float('nan')
    return ma.transpose(), mw.transpose()

data = sio.loadmat('data.mat')
x = data.get('x')
y = data.get('y')
u = data.get('u')
v = data.get('v')
xit = data.get('xit')
yit = data.get('yit')

u1, u2 = seperate(u)
v1, v2 = seperate(v)

plt.contourf(x, y, np.sqrt(u1**2 + v1**2), 100)
plt.colorbar(label='Gass')
plt.show()
plt.contourf(x, y, np.sqrt(u2**2 + v2**2), 100, cmap='ocean')
plt.colorbar(label='Væske')
plt.plot(xit, yit, "r*")
plt.show()
