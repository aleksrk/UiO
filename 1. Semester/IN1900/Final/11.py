import numpy as np

def forward(f, x, h):
    return (f(x+h) - f(x)) / h


func = lambda t: np.cos(t)
x = 0
h = 0.001

answer = forward(func, x, h)

print(f'The estimated value of Cos(x) in the point {x} with an h of {h} is {answer}')
