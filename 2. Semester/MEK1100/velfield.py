import numpy as np

def velfield(n):
    I = np.linspace(-1,1,n)
    x, y = np.meshgrid(I, I, indexing='ij')
    u = np.cos(x)*np.sin(y)
    v = -np.sin(x)*np.cos(y)

    return x, y, u, v
