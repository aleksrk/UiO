import numpy as np

def log_approx(x,n):

    if isinstance(x, float):
        sum = 0
        for k in range(1, n+1):
            sum += (-1)**(k+1)*((x**k) / k)
        return sum
    else:
        sum = []
        for j in x:
            partsum = 0

            for k in range(1, n+1):
                partsum += ((-1)**(k+1))*((j**k) / k)

            sum.append(partsum)
        return sum

x = 2.0
x2 = np.asarray([10.0, 5.0, 6.0])
n = 9

print(log_approx(x,n))
print(log_approx(x2,n))
