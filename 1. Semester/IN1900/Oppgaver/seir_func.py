import numpy as np
from ODESolver import RungeKutta4
import matplotlib.pyplot as plt

def SEIR_model(u,t):
    beta = 0.5; r_ia = 0.1; r_e2=1.25;
    lmbda_1=0.33; lmbda_2=0.5; p_a=0.4; mu=0.2;

    S, E1, E2, I, Ia, R = u
    N = sum(u)KosKo
    dS  = -beta*S*I/N - r_ia*beta*S*Ia/N - r_e2*beta*S*E2/N
    dE1 = beta*S*I/N + r_ia*beta*S*Ia/N + r_e2*beta*S*E2/N - lmbda_1*E1
    dE2 = lmbda_1*(1-p_a)*E1 - lmbda_2*E2
    dI  = lmbda_2*E2 - mu*I
    dIa = lmbda_1*p_a*E1 - mu*Ia
    dR  = mu*(I + Ia)
    return [dS, dE1, dE2, dI, dIa, dR]

def test_SEIR_model():
    t = np.zeros(0)
    u = [1,1,1,1,1,1]
    computed = SEIR_model(u, t)
    expected = [-0.19583333333333333, -0.13416666666666668, -0.302, 0.3, -0.068, 0.4]
    tol = 1e-10
    assert np.allclose(computed, expected, tol), "faulty SEIR_model"

def solve_SEIR(T, dt, S_0, E2_0):
    time = np.arange(0, T, dt)
    initials = [S_0, 0, E2_0, 0, 0, 0]

    solver = RungeKutta4(SEIR_model)
    solver.set_initial_condition(initials)
    u, t = solver.solve(time)
    return u, t

def plot_SEIR(u,t):
    S = u[:,0]; E1 = u[:,1]; E2 = u[:,2];
    I = u[:,3]; Ia = u[:,4]; R = u[:,5]

    plt.plot(t, S, label='S')
    plt.plot(t, I, label='I')
    plt.plot(t, Ia, label='Ia')
    plt.plot(t, R, label='R')
    """
    d = {'S_val' : 'S', 'I_val' : 'I', 'Ia_val' : 'Ia', 'R_val' : 'R'}
    for category in d:
        plt.plot(t, category, title = 'd[category]' )
    """
    plt.legend()
    plt.show()

T = 100
dt = 1.0
S_0 = 5e6
E2_0 = 100

u, t = solve_SEIR(T, dt, S_0, E2_0)
plot_SEIR(u, t)
