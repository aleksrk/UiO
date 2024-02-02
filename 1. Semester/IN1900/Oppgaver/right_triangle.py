# Problem 8.2 Right Triangle Class
import matplotlib.pyplot as plt
import numpy as np

class RightTriangle:

    def __init__(self, a, b):
        if a and b > 0:
            self.a = a
            self.b = b
            self.hyp = np.sqrt(a**2 + b**2)
        else:
            raise ValueError

    def plot_triangle(self):
        acorner = [self.a, 0]
        bcorner = [0, self.b]
        plt.axis("equal")
        plt.plot(acorner, [0,0])
        plt.plot([0,0], bcorner)
        plt.plot(acorner, bcorner)
        plt.show()

triangle1 = RightTriangle(1,1)
triangle2 = RightTriangle(3,4)

print(triangle1.hyp)
print(triangle2.hyp)
triangle2.plot_triangle()

"""
kvale@Aleksanders-MBP Oppgaver % python3 right_triangle.py
1.4142135623730951
5.0
"""

def test_RightTriangle():
    success = False
    try:
        triangle3 = RightTriangle(1,-1)
    except ValueError:
        success = True
    assert success

"""
kvale@Aleksanders-MBP Oppgaver % pytest right_triangle.py
============================= test session starts ==============================
platform darwin -- Python 3.7.3, pytest-6.0.2, py-1.9.0, pluggy-0.13.1
rootdir: /Users/kvale/Documents/UiO/IN1900/Oppgaver
collected 1 item

right_triangle.py .                                                      [100%]

============================== 1 passed in 3.54s ===============================
"""
