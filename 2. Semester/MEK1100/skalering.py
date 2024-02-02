import numpy as np
import matplotlib.pyplot as plt

yfunc = lambda t, thetha: t*np.tan(thetha)*(1 - t)
t = np.linspace(0,1)
x = t
thetha = [6, 4, 3]

for i in thetha:
    plt.plot(x, yfunc(x, np.pi/i), label='$\pi/$'+str(i))

plt.xlabel('x*')
plt.ylabel('y*')
plt.legend()
plt.show()
