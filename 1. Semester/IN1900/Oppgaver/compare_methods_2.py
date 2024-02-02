"Problem E.8. Compare numerical methods for solving ODEs"
import numpy as np
import matplotlib.pyplot as plt

class ODESolver:
    def __init__(self, f):
        self.f = f

    def advance(self):
        """Advance solution one time step."""
        raise NotImplementedError # implement in subclass

    def set_initial_condition(self, U0):
        self.U0 = float(U0)

    def solve(self, time_points):
        self.t = np.asarray(time_points)
        N = len(self.t)
        self.u = np.zeros(N)
        # Assume that self.t[0] corresponds to self.U0
        self.u[0] = self.U0
        # Time loop

        for n in range(N-1):
            self.n = n
            self.u[n+1] = self.advance()
        return self.u, self.t

#Subclass Midpoint
class Midpoint(ODESolver):
    def advance(self):
        u, f, n, t = self.u, self.f, self.n, self.t
        dt = t[n+1] - t[n]
        dt2 = dt/2.0
        k1 = f(u[n], t)
        k2 = f(u[n] + dt2*k1, t[n] + dt2)
        unew = u[n] + dt*k2
        return unew

#Subclass Heuns Method/Explicit Trapizoidal
class Heuns(ODESolver):

    def advance(self):
        u, f, n, t = self.u, self.f, self.n, self.t

        dt = t[n+1] - t[n]
        k1 = f(u[n], t[n])
        k2 = f(u[n] + dt*k1, t[n]+dt)
        unew = u[n] + dt*(k1/2 + k2/2)
        return unew

#Subclass for 4th order Runge-Kutta
class RungeKutta4(ODESolver):
    def advance(self):
        u, f, n, t = self.u, self.f, self.n, self.t
        dt = t[n+1] - t[n]
        dt2 = dt/2.0
        k1 = f(u[n], t)
        k2 = f(u[n] + dt2*k1, t[n] + dt2)
        k3 = f(u[n] + dt2*k2, t[n] + dt2)
        k4 = f(u[n] + dt*k3, t[n] + dt)
        unew = u[n] + (dt/6.0)*(k1 + 2*k2 + 2*k3 + k4)
        return unew

"1b) Test your implementation"

t=np.linspace(0,8*np.pi,100)
analytical=lambda t: t*np.sin(t)+2*np.cos(t) #Analytical solution to ODE
analytical=analytical(t)
plt.plot(t,analytical, label='Analytical', color='black')

ODE=lambda u, t: t*np.cos(t)-np.sin(t) #f(u,t)
U0=2 #initial condition u(0)

#Compute numerically with exact
MP=Midpoint(ODE) #input function in MidPoint
MP.set_initial_condition(U0) #set init condition for MidPoint

Heun=Heuns(ODE) #input function in MidPoint
Heun.set_initial_condition(U0) #set init condition for MidPoint

RK4=RungeKutta4(ODE) #input function in MidPoint
RK4.set_initial_condition(U0) #set init condition for MidPoint

#plot with dictionary comprehension and for loop
timesteps=(20,25,50,150)
curve = {time: MP.solve(np.linspace(0,8*np.pi,time)) for time in timesteps}
for time, (u1,t1) in curve.items():
    plt.plot(t1,u1,'--',label=f'n = {str(time)}')
    plt.suptitle('Midpoint Method')
curve2 = {time: Heun.solve(np.linspace(0,8*np.pi,time)) for time in timesteps}
for time, (u2,t2) in curve2.items():
    plt.plot(t2,u2,'--',label=f'n = {str(time)}')
    plt.suptitle('Heuns Method')

curve3 = {time: RK4.solve(np.linspace(0,8*np.pi,time)) for time in timesteps}
for time, (u3,t3) in curve3.items():
    plt.plot(t3,u3,'--',label=f'n = {str(time)}')
    plt.suptitle('RK4 Method')

#plot shit
plt.grid()
plt.legend()
plt.figure(dpi=300)
plt.show()



#kjøreeksempel
"""
In [1]: runfile('C:/Users/David/Desktop/IN1900/46/compare_methods.py', wdir='C:/Users/David/Desktop/IN1900/46')

Figures now render in the Plots pane by default. To make them also appear inline
in the Console, uncheck "Mute Inline Plotting" under the Plots pane options menu.

"""
