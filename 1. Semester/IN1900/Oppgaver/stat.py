# Problem 4.8 Simple Statistical Functions
import numpy as np
from math import sqrt

x_test_values = [0.699, 0.703, 0.698, 0.688, 0.701]

def mean(x_list):
    sum = 0
    for x_i in x_list:
        sum += x_i
    return sum/len(x_list)

def test_mean():
    expected = np.mean(x_test_values)
    computed = mean(x_test_values)
    assert (expected == computed), f"{expected} != {computed}"

def standard_deviation(x_list):
    std = []
    xbar = mean(x_list)
    for x_i in x_list:
        std.append((x_i - xbar)**2)
    stdsquared = mean(std)
    return sqrt(stdsquared)

def test_standard_deviation():
    expected = np.std(x_test_values)
    computed = standard_deviation(x_test_values)
    assert (expected == computed), f"{expected} != {computed}"

"""
kvale@Aleksanders-MBP Oppgaver % pytest stat.py
============================= test session starts ==============================
platform darwin -- Python 3.7.3, pytest-6.0.2, py-1.9.0, pluggy-0.13.1
rootdir: /Users/kvale/Documents/UiO/IN1900/Oppgaver
collected 2 items

stat.py ..                                                              [100%]

============================== 2 passed in 0.10s ===============================
"""
