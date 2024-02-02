import matplotlib.pyplot as plt
import numpy as np

def readfile(filename):
    delta_x = np.zeros(19)
    abs_error = np.zeros(19)
    n = np.zeros(19)
    i = 0
    infile = open(filename, 'r')
    for line in infile:
        words = line.split(',')
        delta = words[0].split()
        abs = words[3].split()
        n_temp = words[5].split('=')
        delta_x[i] = delta[1]
        abs_error[i] = abs[1]
        n[i] = n_temp[1].replace('\n', '')
        i += 1

    return delta_x, abs_error, n


delta_x, abs_error, n = readfile('derivativeofsin.txt')


plt.plot(n, delta_x, label='Delta_x')
plt.plot(n, abs_error, label='Abs_error')
plt.semilogy()
plt.legend()
plt.show()

"""
kvale@Aleksanders-MBP Oppgaver % python3 plot_round_off_error.py

the abs_error skyrockets as f(x-delta_x) and f(delta_x) gets closer,
then you will have the program take a number and negate an almost identical Number
which opens room for substantial round off errors as you increase n
"""
