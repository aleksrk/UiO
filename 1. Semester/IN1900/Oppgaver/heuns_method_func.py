import numpy as np
import matplotlib.pyplot as plt

"""
Example used is the equation u' = u, U0 = 1 and T = 2
"""
def heuns_method(f, U0, T, N):
    t = np.zeros(N+1)
    u = np.zeros(N+1) # u[n] is the solution at time t[n]
    u[0] = U0
    dt = T/N
    for n in range(N):
        t[n+1] = t[n] + dt
        k1 = f(u[n], t[n])
        k2 = f(u[n] + dt*k1, t[n]+dt)
        u[n+1] = u[n] + dt*(k1/2 + k2/2)

    return u, t

def test_heuns_against_hand_calculations():
    u_test, t_test = heuns_method(f, 1, 2, 2)
    expected = 2.5
    computed = u_test[1]
    tol = 1E-14
    success = abs(expected - computed) < tol
    msg = 'expected != computed, somethings fishy'
    assert success, msg

def plot_func(N):
    u2, t2 = heuns_method(f, 1, 2, N)
    plt.plot(t2, u2, label = f'dt = {(2 / N):.2f}')

f = lambda u, t: u # general definition of differential equation u' = u

if __name__ == '__main__':
    """ If block to check if the program is being called directly
    this will prevent pytest from plotting to the screen
    I spent two hours figuring this out please send immediate assistance,
    alternatively a pack of bamsemums
    """
    nvals = (1,2,3,4,5)
    for n in nvals:
        plot_func(n)

    f_exact = lambda t: np.exp(t)
    t_exact = np.linspace(0,2,10000)
    u_exact = f_exact(t_exact)

    plt.plot(t_exact, u_exact, label='exact solution')
    plt.legend()
    plt.show()

"""
kvale@Aleksanders-MBP Oppgaver % pytest heuns_method_func.py
============================= test session starts ==============================
platform darwin -- Python 3.7.3, pytest-6.0.2, py-1.9.0, pluggy-0.13.1
rootdir: /Users/kvale/Documents/UiO/IN1900/Oppgaver
collected 1 item

heuns_method_func.py .                                                   [100%]

============================== 1 passed in 2.19s ===============================
"""

"""
kvale@Aleksanders-MBP Oppgaver % python3 heuns_method_func.py
"""
