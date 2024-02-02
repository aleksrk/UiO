import numpy as np
import matplotlib.pyplot as plt
from simple_ODE_class_ODESolver.py import ForwardEuler

class Cooling():

    def __init__(self, h, Ts):
        self.h = h
        self.Ts = Ts

    def __call__(self, T, t):
        return -self.h*(T - self.Ts)

def estimate_h(t1, Ts, T0, T1):
    return (T1 - T0) / (t1 * (Ts - T0))
"""
def test_Cooling():
    h =
    cool = Cooling()
    tol = 1E-14
    success = abs(expected - computed) < tol
    msg = f"success baby"
    assert success, msg
"""



instance = Cooling()
