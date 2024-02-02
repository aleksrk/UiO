# Problem 4.5 Half-wave rectifier
from math import sin, pi

def f(x):
    if sin(x) > 0:
        return sin(x)
    else:
        return 0

def test_halfwave():
    def test_f(x):
        sin = f(x)
        return sin

    assert (test_f(pi/2) == sin(pi/2)), "error, expected does not equal computed value"
    assert (test_f(3*pi/2) == 0), "error, expected does not equal computed value"

"""
kvale@Aleksanders-MBP Oppgaver % pytest half_wave.py
============================= test session starts ==============================
platform darwin -- Python 3.7.3, pytest-6.0.2, py-1.9.0, pluggy-0.13.1
rootdir: /Users/kvale/Documents/UiO/IN1900/Oppgaver
collected 1 item

half_wave.py .                                                           [100%]

============================== 1 passed in 0.01s ===============================
"""
