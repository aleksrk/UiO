import matplotlib.pyplot as plt
import numpy as np

class Diff:
    #constructor taking f as an argument
    def __init__(self,f):
        self.f=f

    #method for first approximation
    def diff1(self,x,h):
        f = self.f
        self.x=x
        self.h=float(h)
        return (f(x+h)-f(x))/h

    #method for second approximation
    def diff2(self,x,h):
        f = self.f
        self.x=x
        self.h=float(h)
        return (f(x+h)-f(x-h))/(2*h)

    #method for third approximation
    def diff3(self,x,h):
        f = self.f
        self.x=x
        self.h=float(h)
        a=-f(x+(2*h))+8*f(x+h)-8*f(x-h)+f(x-(2*h)) #teller
        b=(12*h) #nevner
        return a/b

h_val=[0.9,0.6,0.3,0.1] #h values
x=np.linspace(-1,1,500) #x interval


func=(lambda x: np.sin(2*np.pi*x))#f(x) sin(2pix)
f=Diff(func)#creating instance of class using f(x)

func2=(lambda x: 2*np.pi*np.cos(2*np.pi*x))#function for exact (2picos(2pi*x))

for h in h_val:
    Diff1=f.diff1(x,h) #first diff method
    plt.plot(x, Diff1)
    Diff2=f.diff2(x,h) #second diff method
    plt.plot(x, Diff2)
    Diff3=f.diff3(x,h) #third diff method
    plt.plot(x, Diff3)

#plot the methods
plt.figure(3)
plt.plot(x, Diff1)
plt.figure(1)
plt.plot(x, Diff2)
plt.figure(2)
plt.plot(x, Diff3)

func2 = func2(x)
print(func2)
plt.plot(x, func2) #Exact solution
plt.show()
