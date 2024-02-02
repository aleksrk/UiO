#Problem 4.6 Primality checker

def primeChecker(n):
    num = 0
    for x in range(2, n):
        if (n % x) == 0:
            return print("false")
        else:
            return print("true")

primeChecker(7)
primeChecker(6)
num = 2
