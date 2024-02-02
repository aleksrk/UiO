import matplotlib.pyplot as plt
from streamfun import streamfun

def strlin(n):
    '''Plotter kontourlinjer med n punkter i hver retning'''
    X, Y, psi = streamfun(n)
    plt.contour(X, Y, psi, 10)

plt.subplot(1,2,1)
plt.title('n=5')
strlin(5)
plt.subplot(1,2,2)
plt.title('n=30')
strlin(30)
plt.axis('equal')
plt.show()
