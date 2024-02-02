import matplotlib.pyplot as plt
import scipy.io as sio
import numpy as np

def rektangel_tegn(x1, y1, x2, y2):

    plt.plot([x1, x1+(x2-x1)], [y1, y1], color = 'r')
    plt.plot([x1+(x2-x1), x1+(x2-x1)], [y1, y2], color = 'g')
    plt.plot([x1, x1+(x2-x1)], [y1+(y2-y1), y1+(y2-y1)], color = 'b')
    plt.plot([x1, x1], [y1, y1+(y2-y1)], color = 'k')

data = sio.loadmat('data.mat')
x = data.get('x')
y = data.get('y')
u = data.get('u')
v = data.get('v')
xit = data.get('xit')
yit = data.get('yit')

rectangles = [[x[0][34], y[159][0], x[0][69], y[169][0]], [x[0][34], y[84][0],x[0][69], y[99][0]], [x[0][34], y[49][0],x[0][69],y[59][0]]]
plt.quiver(x[::20, ::20], y[::20, ::20], u[::20, ::20], v[::20, ::20], pivot = 'middle', cmap='ocean')
plt.Rectangle((x[160][35], y[160][35]), 35, 10,linewidth = 1, edgecolor='r')
plt.plot()

for i in rectangles:
    rektangel_tegn(i[0], i[1], i[2], i[3])
    plt.plot(xit, yit, "r*")

plt.show()
