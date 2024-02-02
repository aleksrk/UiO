#Problem 6.14 Plotting Graphs
import matplotlib.pyplot as plt
import numpy as np

def plot_line(p1, p2):
    x = [p1[0], p2[0]]
    y = [p1[1], p2[1]]

    return plt.plot(x, y)

plt.figure(1)
plot_line([1,1], [3,1])
plot_line([1,3], [1,9])

def complete_graph(points):
    for i in points:
        x = i
        for i in points:
            plot_line(x,i)

square = [(0,0),(1,0),(0,1),(1,1)]
a = (np.sqrt(2) / 2)
circle = [(1,0),(a,a),(0,1),(-a,a),(-1,0),(-a,-a),(0,-1),(a,-a)]
plt.figure(2)
complete_graph(square)
plt.figure(3)
complete_graph(circle)

plt.show()

"""
Kjøreeksempel:
kvale@Aleksanders-MBP Oppgaver % python3 graph1.py
"""
