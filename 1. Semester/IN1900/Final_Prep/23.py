from math import factorial

def cos_approx(x,n):

    if x == type('float'):
        sum = 0
        for i in len(n+1):
            sum += -1*i * (x**(2*i)/ math.factorial(2*i))
        return sum

    else:
        sum = []
        for j in x:

            for i in len(n+1):
                partsum += -1*i * (j**(2*i)/ math.factorial(2*i))

            sum.append(partsum)
        return sum
