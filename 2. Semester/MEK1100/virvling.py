import matplotlib.pyplot as plt
import scipy.io as sio
import numpy as np

def rektangel_tegn(x1, y1, x2, y2):

    plt.plot([x1, x1+(x2-x1)], [y1, y1], color = 'r')
    plt.plot([x1+(x2-x1), x1+(x2-x1)], [y1, y2], color = 'g')
    plt.plot([x1, x1+(x2-x1)], [y1+(y2-y1), y1+(y2-y1)], color = 'b')
    plt.plot([x1, x1], [y1, y1+(y2-y1)], color = 'k')

def seperate(matrix):

    ma = np.copy(matrix.transpose())
    mw = np.copy(matrix.transpose())
    for i in range(len(ma)):
        for j in range(len(ma[i])):
            if y[j][0] < yit[0][i]:
                ma[i][j] = float('nan')
    for i in range(len(mw)):
        for j in range(len(mw[i])):
            if y[j][0] > yit[0][i] or mw[i][j] > 1000 or mw[i][j] < -850:
                mw[i][j] = float('nan')
    return ma.transpose(), mw.transpose()

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
rectangles = [[x[0][34], y[159][0], x[0][69], y[169][0]], [x[0][34], y[84][0],x[0][69], y[99][0]], [x[0][34], y[49][0],x[0][69],y[59][0]]]

for i in rectangles:
    rektangel_tegn(i[0], i[1], i[2], i[3])

divx_lower, divx_upper = seperate(dvdx)
divy_lower, divy_upper = seperate(dudy)
div_lower = divx_lower - divy_lower
div_upper = divx_upper - divy_upper


plt.contourf(x, y, div_upper, 1000)
plt.colorbar(label='Gass')
plt.contourf(x, y, div_lower, 1000, cmap='ocean')
plt.colorbar(label='Væske')
plt.streamplot(x,y,u,v, cmap='jet', color = div_lower)
plt.streamplot(x,y,u,v, cmap='bwr', color = div_upper)

plt.plot(xit, yit, "r*")
plt.show()
